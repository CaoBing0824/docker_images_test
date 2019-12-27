package com.xy.boot.open.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.xy.boot.common.base.entity.BaseEntity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

/**
 * 户部正式上线通道号（户部数据库）
 *
 * @author maisenlin@mfexcel.com
 * @since 2019-06-03 12:01
 **/

@TableName("tb_public_receive_num_release")
public class TBPublicReceiveNumRelease extends BaseEntity<TBPublicReceiveNumRelease> {

    /**
     * 定义表列名的常量
     */
    public static final String TB_ID = "id";
    // 公众号名称
    public static final String TB_PUB_RECEIVE_NUM = "pub_receive_num";
    // 区域
    public static final String TB_AREA = "area";
    // 户部状态
    public static final String TB_STATU = "statu";

    //
    private Long id;

    // 公众号名称
    private String pubName;

    // 公众号接入码
    private String pubReceiveNum;

    // 添加时间
    private Date addTime;

    // 添加人
    private String addUser;

    // 版本
    private String versionCode;

    // 1:新增 2：删除
    private int editType;

    //
    private int recordType;

    // 目的
    private String numPurpose;

    // 公众号id
    private int pubInfoId;

    // 号码自己的公众号
    private int newPubId;

    // 区域
    private String area;

    // 最大长度
    private int maxLen;

    // 最小长度
    private int minLen;

    // 长度
    private int len;

    // 号码类型
    private String ntype;

    // 户部状态1.启用 2.验证通过 0.户部下线,脚本上线 3.验证中 -1.下线 -2未验证
    private int statu;

    // 签名状态;0:仅企业资料上线（模板识别不上线）;1:企业资料也模板识别都上线;
    private int statuQm;

    // 渠道来源
    private String channel;

    // 扩展字段
    private String extend;

    // 支持的渠道
    private String supportChannels;

    // 排除的渠道
    private String forbiddenChannels;

    // 分类描述
    private String description;

    // 企业规模
    private int mark1;

    // 号码类型 2 *方案  1 1方案
    private int mark2;

    // 是否高敏
    private int isGm;

    // 0:短信接入码;1:固话;
    private Integer numType;

    // 号码数量
    private long numCount;

    // 签名数量
    private long signCount;

    // 上线日期
    private Date upLineDate;

    // 1户部上线 0签名上线
    private int isAuthenticate;

    // 0未变更 1变更
    private int isChange;

    // 是否合作企业 0非合作企业非合作号码 1合作号码 2合作企业合作号码
    private Integer mark4;

    // 合作号码 0非合作企业非合作号码 1合作号码 2合作企业合作号码
    private int mark3;

    // 内嵌标识的高频号码，针对不同的渠道
    private String innerSupportChannels;

    // 手动上线时间
    private Timestamp handUpLineDate;

    // 号段唯一标识
    private Integer sectionNoId;

    // 支持的国家码
    private String itc;

    // 更新版本号
    private String lastUpdateVersion;

    //
    private String twoName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPubName() {
        return pubName;
    }

    public void setPubName(String pubName) {
        this.pubName = pubName;
    }

    public String getPubReceiveNum() {
        return pubReceiveNum;
    }

    public void setPubReceiveNum(String pubReceiveNum) {
        this.pubReceiveNum = pubReceiveNum;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getAddUser() {
        return addUser;
    }

    public void setAddUser(String addUser) {
        this.addUser = addUser;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public int getEditType() {
        return editType;
    }

    public void setEditType(int editType) {
        this.editType = editType;
    }

    public int getRecordType() {
        return recordType;
    }

    public void setRecordType(int recordType) {
        this.recordType = recordType;
    }

    public String getNumPurpose() {
        return numPurpose;
    }

    public void setNumPurpose(String numPurpose) {
        this.numPurpose = numPurpose;
    }

    public int getPubInfoId() {
        return pubInfoId;
    }

    public void setPubInfoId(int pubInfoId) {
        this.pubInfoId = pubInfoId;
    }

    public int getNewPubId() {
        return newPubId;
    }

    public void setNewPubId(int newPubId) {
        this.newPubId = newPubId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getMaxLen() {
        return maxLen;
    }

    public void setMaxLen(int maxLen) {
        this.maxLen = maxLen;
    }

    public int getMinLen() {
        return minLen;
    }

    public void setMinLen(int minLen) {
        this.minLen = minLen;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public String getNtype() {
        return ntype;
    }

    public void setNtype(String ntype) {
        this.ntype = ntype;
    }

    public int getStatu() {
        return statu;
    }

    public void setStatu(int statu) {
        this.statu = statu;
    }

    public int getStatuQm() {
        return statuQm;
    }

    public void setStatuQm(int statuQm) {
        this.statuQm = statuQm;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public String getSupportChannels() {
        return supportChannels;
    }

    public void setSupportChannels(String supportChannels) {
        this.supportChannels = supportChannels;
    }

    public String getForbiddenChannels() {
        return forbiddenChannels;
    }

    public void setForbiddenChannels(String forbiddenChannels) {
        this.forbiddenChannels = forbiddenChannels;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMark1() {
        return mark1;
    }

    public void setMark1(int mark1) {
        this.mark1 = mark1;
    }

    public int getMark2() {
        return mark2;
    }

    public void setMark2(int mark2) {
        this.mark2 = mark2;
    }

    public int getIsGm() {
        return isGm;
    }

    public void setIsGm(int isGm) {
        this.isGm = isGm;
    }

    public Integer getNumType() {
        return numType;
    }

    public void setNumType(Integer numType) {
        this.numType = numType;
    }

    public long getNumCount() {
        return numCount;
    }

    public void setNumCount(long numCount) {
        this.numCount = numCount;
    }

    public long getSignCount() {
        return signCount;
    }

    public void setSignCount(long signCount) {
        this.signCount = signCount;
    }

    public Date getUpLineDate() {
        return upLineDate;
    }

    public void setUpLineDate(Date upLineDate) {
        this.upLineDate = upLineDate;
    }

    public int getIsAuthenticate() {
        return isAuthenticate;
    }

    public void setIsAuthenticate(int isAuthenticate) {
        this.isAuthenticate = isAuthenticate;
    }

    public int getIsChange() {
        return isChange;
    }

    public void setIsChange(int isChange) {
        this.isChange = isChange;
    }

    public Integer getMark4() {
        return mark4;
    }

    public void setMark4(Integer mark4) {
        this.mark4 = mark4;
    }

    public int getMark3() {
        return mark3;
    }

    public void setMark3(int mark3) {
        this.mark3 = mark3;
    }

    public String getInnerSupportChannels() {
        return innerSupportChannels;
    }

    public void setInnerSupportChannels(String innerSupportChannels) {
        this.innerSupportChannels = innerSupportChannels;
    }

    public Timestamp getHandUpLineDate() {
        return handUpLineDate;
    }

    public void setHandUpLineDate(Timestamp handUpLineDate) {
        this.handUpLineDate = handUpLineDate;
    }

    public Integer getSectionNoId() {
        return sectionNoId;
    }

    public void setSectionNoId(Integer sectionNoId) {
        this.sectionNoId = sectionNoId;
    }

    public String getItc() {
        return itc;
    }

    public void setItc(String itc) {
        this.itc = itc;
    }

    public String getLastUpdateVersion() {
        return lastUpdateVersion;
    }

    public void setLastUpdateVersion(String lastUpdateVersion) {
        this.lastUpdateVersion = lastUpdateVersion;
    }

    public String getTwoName() {
        return twoName;
    }

    public void setTwoName(String twoName) {
        this.twoName = twoName;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, pubName, pubReceiveNum, addTime, addUser, versionCode, editType, recordType, numPurpose, pubInfoId, newPubId, area, maxLen, minLen, len, ntype, statu, statuQm, channel, extend, supportChannels, forbiddenChannels, description, mark1, mark2, isGm, numType, numCount, signCount, upLineDate, isAuthenticate, isChange, mark4, mark3, innerSupportChannels, handUpLineDate, sectionNoId, itc, lastUpdateVersion, twoName);
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
