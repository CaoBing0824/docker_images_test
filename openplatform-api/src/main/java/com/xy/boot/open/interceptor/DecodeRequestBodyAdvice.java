package com.xy.boot.open.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xy.boot.common.enums.HttpCodeEnum;
import com.xy.boot.common.exception.XyException;
import com.xy.boot.common.util.ReturnDTOUtil;
import com.xy.boot.common.util.StringUtils;
import com.xy.boot.open.constant.HttpConstant;
import com.xy.boot.open.entity.TAgentExtInfo;
import com.xy.boot.open.service.IAgentExtInfoService;
import com.xy.boot.open.service.helper.XyHttpSecurityHelper;
import com.xy.boot.open.util.Base64;
import com.xy.boot.open.util.MyAESutils;
import com.xy.boot.open.util.XyAgentCodeHolder;
import com.xy.boot.open.util.ZipUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.Charsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.TreeMap;

@Slf4j
@Component
@ControllerAdvice(basePackages = "com.xy.boot.open.controller")
public class DecodeRequestBodyAdvice implements RequestBodyAdvice {

    @Autowired
    private XyAgentCodeHolder xyAgentCodeHolder;

    @Autowired
    private IAgentExtInfoService iAgentExtInfoService;

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return methodParameter.getParameterAnnotation(RequestBody.class) != null;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage request, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage request, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException, XyException {

        String xyCode = xyAgentCodeHolder.getAgentCode();
        EntityWrapper<TAgentExtInfo> gentExtInfo = new EntityWrapper<>();
        gentExtInfo.eq(TAgentExtInfo.TB_XY_CODE, xyCode);
        TAgentExtInfo extInfo = iAgentExtInfoService.selectOne(gentExtInfo);
        String appSecrect = extInfo.getAppSecrect();
        String aesKey = Base64.encode(appSecrect.getBytes(Charsets.UTF_8));
        xyAgentCodeHolder.setXyAgentAppSecret(aesKey);

        InputStream is = request.getBody();
        ByteBuf buf = PooledByteBufAllocator.DEFAULT.heapBuffer();
        int ret = -1;
        int len = 0;
        while ((ret = is.read()) > 0) {
            buf.writeByte(ret);
            len++;
        }
        String body = buf.toString(0, len, Charset.defaultCharset());
        buf.release();
        try {
            byte[] aesKeyArrr = aesKey.getBytes("UTF-8");
            byte[] newAesKey = new byte[16];
            System.arraycopy(aesKeyArrr, 0, newAesKey, 0, 16);
            byte[] resultByte = MyAESutils.decryptToByte(body, aesKey);
            // 解压请求体
            resultByte = ZipUtils.uncompress(resultByte);
            String result = new String(resultByte, "utf-8");
            //校验签名
            if (!checkSignature(request.getHeaders(), aesKey, result)) {
                log.warn("签名校验不成功！");
                throw new XyException(ReturnDTOUtil.custom(HttpCodeEnum.UNAUTHORIZED.getCode(), "签名错误！"));
            }
            return new DecodedHttpInputMessage(request.getHeaders(), new ByteArrayInputStream(result.getBytes()));
        } catch (XyException e) {
            throw e;
        } catch (Exception e) {
            log.warn("接口协议错误", xyCode, e);
            throw new XyException(ReturnDTOUtil.custom(HttpCodeEnum.UNAUTHORIZED.getCode(), "接口协议错误！"));
        }
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    static class DecodedHttpInputMessage implements HttpInputMessage {
        HttpHeaders headers;
        InputStream body;

        public DecodedHttpInputMessage(HttpHeaders headers, InputStream body) {
            this.headers = headers;
            this.body = body;
        }

        @Override
        public InputStream getBody() throws IOException {
            return body;
        }

        @Override
        public HttpHeaders getHeaders() {
            return headers;
        }
    }

    public static String bytesToHex(byte[] src) {
        if (src == null || src.length <= 0) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < src.length; i++) {
            // 之所以用byte和0xff相与，是因为int是32位，与0xff相与后就舍弃前面的24位，只保留后8位
            String str = Integer.toHexString(src[i] & 0xff);
            if (str.length() < 2) { // 不足两位要补0
                stringBuilder.append(0);
            }
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }


    /**
     * 校验签名
     *
     * @param paramStr
     */
    private Boolean checkSignature(HttpHeaders headers, String appSecrect, String paramStr) {

        // 参数集合 初始化
        TreeMap paramsMaps = new TreeMap();
        String token, v, timestamp, sign, appid;
        /**
         * 验证通用请求头是否完整
         */
        token = headers.getFirst(HttpConstant.TOKEN);
        v = headers.getFirst(HttpConstant.V);
        timestamp = headers.getFirst(HttpConstant.TIMESTAMP);
        sign = headers.getFirst(HttpConstant.SIGN);
        appid = headers.getFirst(HttpConstant.APPID);
        if (StringUtils.isEmpty(token) || StringUtils.isEmpty(v) || StringUtils.isEmpty(timestamp) || StringUtils.isEmpty(sign)) {
            //通用请求头不完整
            return false;
        }

        /**
         * 验证签名是否合法
         */
        //将请求参数转加入到M1集合中
        paramsMaps = JSONObject.parseObject(paramStr, TreeMap.class);
        //将header中通用请求头整入验证参数中
        paramsMaps.put(HttpConstant.TOKEN, token);
        paramsMaps.put(HttpConstant.TIMESTAMP, timestamp);
        paramsMaps.put(HttpConstant.V, v);
        paramsMaps.put(HttpConstant.APPID, appid);
        //验证签名
        boolean isValid = XyHttpSecurityHelper.validateSignature(paramsMaps, appSecrect, sign);
        if (!isValid) {
            //校验失败
            return false;
        }
        return true;
    }


}
