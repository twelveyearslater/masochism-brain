package com.boot.entity;

import java.util.Date;

/**
 * Created by eleven on 2018/12/18.
 */
public class PswMsg {

    //id
    private Integer id;
    // 用户id
    private Integer userId;
    // 平台用户名
    private String username;
    // 平台密码
    private String password;
    // 平台手机号
    private String phone;
    // 平台邮箱
    private String email;
    // 平台认证信息
    private String idCard;
    // 平台id
    private String platformId;
    // 创建时间
    private Date createTime;
    // 上次操作时间
    private Date lastModifyTime;
    // 密码状态
    private Integer status;
    // 备注
    private String remark;
    //平台名称
    private String platformName;
    //平台图标路径
    private String iconUrl;
    // 修改次数
    private Integer editCount;
    // 查看次数
    private Integer lookCount;

    public PswMsg(){}

    public PswMsg(Integer userId, String[] pswArr){
        this.userId = userId;
        this.username = pswArr[0];
        this.password = pswArr[1];
        this.remark = pswArr[2];
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Integer getEditCount() {
        return editCount;
    }

    public void setEditCount(Integer editCount) {
        this.editCount = editCount;
    }

    public Integer getLookCount() {
        return lookCount;
    }

    public void setLookCount(Integer lookCount) {
        this.lookCount = lookCount;
    }
}
