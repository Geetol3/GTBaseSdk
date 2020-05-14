package com.gtdev5.geetolsdk.mylibrary.beans;

import java.io.Serializable;

/**
 * Created by zl
 * 2020/05/14
 * 公共返回参数
 */
public class ResultBean implements Serializable {
    private boolean issucc;            //调用是否成功
    private String msg;
    private String code;

    public boolean isIssucc() {
        return issucc;
    }

    public void setIssucc(boolean issucc) {
        this.issucc = issucc;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
