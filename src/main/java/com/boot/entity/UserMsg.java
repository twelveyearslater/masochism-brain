package com.boot.entity;

import java.util.Date;

/**
 * Created by eleven on 2018/12/18.
 */
public class UserMsg {

    // 用户id
    private Integer id;
    // 邮箱
    private String email;
    // 手机号
    private String phone;
    // 微信号
    private String weChatId;
    // 身份证号
    private String idCard;
    // 用户名
    private String username;
    // 密码
    private String password;
    // 盐值
    private String salt;
    // 密码失效日期
    private Date invalidate;
    // 密码错误次数
    private Integer errorCount;
    // 昵称
    private String nickname;
    // 加密序列
    private String encodeQueue;
    // 注册时间
    private Date signTime;
    // 上次登录时间
    private Date lastLoginTime;
    // 上次登录IP
    private String lastLoginIp;
    //在线时长
    private float onlineHour;
    // 状态
    private Integer status;
    // 解密方式
    private Integer decodeType;
    // 解密秘钥
    private String decodeQueue;
    // 性别
    private Integer gender;
    // 个性签名
    private String signature;
    // 绑定微博
    private String sina;
    // 所在地
    private String liveRegion;
    // 家乡
    private String hometown;
    // 真实姓名
    private String realName;
    // 生日
    private String birthday;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWeChatId() {
        return weChatId;
    }

    public void setWeChatId(String weChatId) {
        this.weChatId = weChatId;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getInvalidate() {
        return invalidate;
    }

    public void setInvalidate(Date invalidate) {
        this.invalidate = invalidate;
    }

    public Integer getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEncodeQueue() {
        return encodeQueue;
    }

    public void setEncodeQueue(String encodeQueue) {
        this.encodeQueue = encodeQueue;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public float getOnlineHour() {
        return onlineHour;
    }

    public void setOnlineHour(float onlineHour) {
        this.onlineHour = onlineHour;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDecodeType() {
        return decodeType;
    }

    public void setDecodeType(Integer decodeType) {
        this.decodeType = decodeType;
    }

    public String getDecodeQueue() {
        return decodeQueue;
    }

    public void setDecodeQueue(String decodeQueue) {
        this.decodeQueue = decodeQueue;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSina() {
        return sina;
    }

    public void setSina(String sina) {
        this.sina = sina;
    }

    public String getLiveRegion() {
        return liveRegion;
    }

    public void setLiveRegion(String liveRegion) {
        this.liveRegion = liveRegion;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
