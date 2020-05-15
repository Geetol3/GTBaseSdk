package com.gtdev5.geetolsdk.mylibrary.beans;

import java.io.Serializable;

/**
 * Created by zl
 * 2020/05/14
 * 公共返回参数
 */
public class ResultBean implements Serializable {
    /**
     * 是否调用成功
     */
    private boolean issucc;
    /**
     * 错误提示
     */
    private String msg;
    /**
     * 错误代码
     */
    private String code;
    /**
     * 调用时长
     */
    private long total;

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

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
