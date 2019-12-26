package com.xy.boot.open.util;

import com.xy.boot.common.exception.XyException;
import com.xy.boot.common.util.StringUtils;
import com.xy.boot.open.constant.BaseConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;
import java.util.*;

/**
 * 敏感词检查工具
 *
 * @author maisenlin@mfexcel.com
 * @since 2019-05-28
 */
@Slf4j
@Component
public class SensitiveWordUtil {

    private static final String ENCODING = "UTF-8"; //字符编码

    public static final int MIN_MATCH_TYPE = 1;      //最小匹配规则:匹配即返回
    public static final int MAX_MATCH_TYPE = 2;      //最大匹配规则:穷尽

    private HashMap sensitiveWordMap;

    @Value("${sensitiveWordList}")
    private  String filePaths;

    @Value("${sensitiveWordList}")
    private Resource sensitiveResource;

    //@Value("${sensitiveWordRedisSetName}")
    //private String redisSetName;

    @PostConstruct
    public void init(){
        log.info("敏感词库路径 = {}" , filePaths);
        //log.info("从redis读取敏感词, key = {}" , redisSetName);
        initKeyWord();

    }

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private int oldSetSize = 0;
    /**
     * 检查敏感词
     * @return 检查结果信息
     */
    public String validSensitiveWord(String targetTxt){
        initKeyWord();
        StringBuilder msg = new StringBuilder("");
        Set<String> result = getSensitiveWord(targetTxt, MAX_MATCH_TYPE);
        if( !result.isEmpty() ){
            msg.append("存在敏感词：");
            Iterator iter = result.iterator();
            while( iter.hasNext() ){
                msg.append(BaseConstant.DOUBLEQUOT).append(iter.next().toString()).append(BaseConstant.DOUBLEQUOT).append(BaseConstant.ZH_COMMA);
            }
        }
        return msg.toString();
    }

    /**
     * 获取文字中的敏感词
     * @param txt 文字
     * @param matchType 匹配规则;1：最小匹配规则，2：最大匹配规则
     * @return
     */
    public Set<String> getSensitiveWord(String txt , int matchType){
        Set<String> sensitiveWordList = new HashSet<String>();
        // 去掉所有空格
        txt = txt.replaceAll(" ", "");
        for(int i = 0 ; i < txt.length() ; i++){
            //判断是否包含敏感字符
            int length = checkSensitiveWord(txt, i, matchType);
            //存在,加入list中
            if(length > 0){
                sensitiveWordList.add(txt.substring(i, i+length));
                //减1的原因，是因为for会自增
                i = i + length - 1;
            }
        }
        return sensitiveWordList;
    }

    /**
     * 判断文字是否包含敏感字符

     * @param targetTxt  文字
     * @param matchType  匹配规则;1：最小匹配规则，2：最大匹配规则
     * @return 若包含返回true，否则返回false
     */
    public boolean isContaintSensitiveWord(String targetTxt, int matchType){
        boolean existSensitiveWord = false;
        int txtLen = targetTxt.length();
        for(int i = 0 ; i < txtLen ; i++){
            int matchFlag = checkSensitiveWord(targetTxt, i, matchType); //判断是否包含敏感字符
            if(matchFlag > 0){    //大于0存在，返回true
                existSensitiveWord = true;
            }
        }
        return existSensitiveWord;
    }


    /**
     * 替换敏感字字符

     * @param txt
     * @param matchType
     * @param replaceChar 替换字符，默认*
     */
    public String replaceSensitiveWord(String txt,int matchType,String replaceChar){
        String resultTxt = txt;
        Set<String> set = getSensitiveWord(txt, matchType);
        //获取所有的敏感词
        Iterator<String> iterator = set.iterator();
        String word = null;
        String replaceString = null;
        while (iterator.hasNext()) {
            word = iterator.next();
            replaceString = getReplaceChars(replaceChar, word.length());
            resultTxt = resultTxt.replaceAll(word, replaceString);
        }
        return resultTxt;
    }

    /**
     * 获取替换字符串

     * @param replaceChar
     * @param length
     * @return
     */
    private String getReplaceChars(String replaceChar,int length){
        String resultReplace = replaceChar;
        for(int i = 1 ; i < length ; i++){
            resultReplace += replaceChar;
        }
        return resultReplace;
    }

    /**
     * 检查文字中是否包含敏感字符，检查规则如下：
     * @param targetTxt 待检测文本
     * @param beginIndex 开始位置
     * @param matchType 匹配规则;1：最小匹配规则，2：最大匹配规则
     * @return，如果存在，则返回敏感词字符的长度，不存在返回0
     */
    public  int checkSensitiveWord(String targetTxt, int beginIndex, int matchType){
        // 敏感词结束标识位：用于敏感词只有1位的情况
        boolean  flag = false;
        // 匹配标识数默认为0
        int matchFlag = 0;
        int txtLen = targetTxt.length();
        char word = 0;
        Map nowMap = this.sensitiveWordMap;
        for (int i = beginIndex; i < txtLen ; i++){
            word = targetTxt.charAt(i);
            // 获取指定key
            nowMap = (Map) nowMap.get(word);
            //存在，则判断是否为最后一个
            if(nowMap != null){
                //找到相应key，匹配标识+1
                matchFlag++;
                //如果为最后一个匹配规则,结束循环，返回匹配标识数
                if("1".equals(nowMap.get("isEnd"))){
                    //结束标志位为true
                    flag = true;
                    //最小规则，直接返回,最大规则还需继续查找
                    if(MIN_MATCH_TYPE == matchType){
                        break;
                    }
                }
            } else{
                // 不存在，直接返回
                break;
            }
        }
        if(matchFlag < 2 || !flag){        //长度必须大于等于1，为词
            matchFlag = 0;
        }
        return matchFlag;
    }

    /**
     * 初始化从文件载入敏感词
     * @return
     */
    private  void initKeyWord(){
        try {
            //int size = redisTemplate.opsForSet().members(redisSetName).size();
            //if( sensitiveWordMap == null || sensitiveWordMap.isEmpty() || oldSetSize != size ){
            if( sensitiveWordMap == null || sensitiveWordMap.isEmpty() ){
                    //读取敏感词库
                    //final String splitRegex = ";";
                    //String [] pathArray = filePaths.split(splitRegex);
                    //Set<String> keyWordSet = readSensitiveWordFileList(  Arrays.asList(pathArray) );

                    // 从redis获取敏感词集合
                    //Set<String> keyWordSet = redisTemplate.opsForSet().members(redisSetName);
                    // 保存集合的长度,用于判断是否更新数据
                    //oldSetSize = size;
                    Set<String> keyWordSet = readSensitiveWordFile( );
                    //将敏感词库加入到HashMap中
                    addSensitiveWordToHashMap(keyWordSet);
                    log.info("成功载入{}个敏感词", keyWordSet.size());

            }
        } catch (Exception e) {
            log.error("读取敏感词异常：", e);
        }
    }

    /**
     * 加入敏感词
     * @param keyWordSet
     */
    private  void addSensitiveWordToHashMap(Set<String> keyWordSet) {
        //初始化敏感词容器，减少扩容操作
        sensitiveWordMap = new HashMap(keyWordSet.size());
        String key = null;
        Map nowMap = null;
        Map<String, String> newWorMap = null;
        //迭代keyWordSet
        Iterator<String> iterator = keyWordSet.iterator();
        while(iterator.hasNext()){
            // 关键字
            key = iterator.next();
            nowMap = sensitiveWordMap;
            for(int i = 0 ; i < key.length() ; i++){
                char keyChar = key.charAt(i);
                //转换成char型
                Object wordMap = nowMap.get(keyChar);

                //如果存在该key，直接赋值
                if(wordMap != null){
                    nowMap = (Map) wordMap;
                }
                else{
                    //不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
                    newWorMap = new HashMap<String,String>();
                    //不是最后一个
                    newWorMap.put("isEnd", "0");
                    nowMap.put(keyChar, newWorMap);
                    nowMap = newWorMap;
                }

                if(i == key.length() - 1){
                    nowMap.put("isEnd", "1");    //最后一个
                }
            }
        }
    }

    /**
     * 读取多个文件的敏感词
     * @param filePathList 敏感词文件集合
     * @return
     */
    private  Set<String> readSensitiveWordFileList(List<String> filePathList) throws Exception {
        Set<String> keyWordSet = new HashSet<String>();
        Iterator iter = filePathList.iterator();
        while(iter.hasNext()){
            String filePath = iter.next().toString();
            if(StringUtils.isNotEmpty(filePath)){
                keyWordSet.addAll( readSensitiveWordFile( filePath ));
            }
        }
        return keyWordSet;
    }

    /**
     * 读取单个文件的敏感词
     * @param filePath
     * @return
     * @throws Exception
     */
    private  Set<String> readSensitiveWordFile(String filePath) throws Exception{
        Set<String> set = null;
        //File sensitiveword=new File("");
        // 注释原因: spring boot 打成jar后,无法通过下面的方式读取class下的资源文件
        //File file = ResourceUtils.getFile(filePath);
        //InputStreamReader read = new InputStreamReader(new FileInputStream(file), ENCODING);
       /* ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource resource = resolver.getResource(filePath);
        InputStream inputStream = resource.getInputStream();
        InputStreamReader read = new InputStreamReader(inputStream, ENCODING);*/
       /* File file = ResourceUtils.getFile(filePath);
        InputStreamReader read = new InputStreamReader(new FileInputStream(file), ENCODING);
        try{
            if(file.isFile() && file.exists()){
                set = new HashSet<String>();
                BufferedReader bufferedReader = new BufferedReader(read);
                String txt = null;
                while((txt = bufferedReader.readLine()) != null){
                    set.add(txt);
                }
                bufferedReader.close();
            } else{
                throw new XyException("敏感词库文件不存在");
            }
        } catch (Exception e){
            throw e;
        }finally {
            read.close();
        }*/
        return set;
    }

    /**
     * 读取单个文件的敏感词（使用注解）
     * @return
     * @throws Exception
     */
    private  Set<String> readSensitiveWordFile() throws Exception{
        Set<String> set = null;
        try {
            String sensitiveData = IOUtils.toString(sensitiveResource.getInputStream(), Charset.forName("UTF-8"));
            String[] sensitiveDataArray = sensitiveData.split("\r\n");
            set = new HashSet<String>(Arrays.asList(sensitiveDataArray));
        } catch (Exception e) {
            e.printStackTrace();
            throw new XyException("读取敏感词库文件出错");
        }

        return set;
    }
}
