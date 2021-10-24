package com.boot.entity;

public class RequestRecord {

    // 请求的唯一标示
    public String id;
    // 被请求的类名
    public String classNanme;
    // 被请求的方法
    public String method;
    // 请求结果状态
    public int status;
    // 错误信息
    public String errMsg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassNanme() {
        return classNanme;
    }

    public void setClassNanme(String classNanme) {
        this.classNanme = classNanme;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
