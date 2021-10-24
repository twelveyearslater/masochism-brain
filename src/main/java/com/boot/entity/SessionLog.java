package com.boot.entity;

import java.util.Date;

public class SessionLog {
    // 日志ID（数据库自增）
    public int id;
    // 操作IP
    public String ip;
    // 操作人
    public String operator;
    // 操作时间
    public Date date;
    // 操作类型 0:登陆 1:退出
    public int type;
    // 操作内容
    public String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
