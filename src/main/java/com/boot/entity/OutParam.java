package com.boot.entity;

public class OutParam {

    private int success = 1;    // 0 为失败 1 为成功
    private String errCode;     // "01" 系统错误 "02" 没有权限 "03"
    private String msg;
    private Object data;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
