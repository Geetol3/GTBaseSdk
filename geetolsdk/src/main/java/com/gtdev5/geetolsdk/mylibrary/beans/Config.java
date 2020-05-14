package com.gtdev5.geetolsdk.mylibrary.beans;

import java.io.Serializable;

/**
 * Created by zl
 * 2020/05/14
 * 微信返回参数
 */
public class Config implements Serializable {
    /**
     * 微信id
     */
    private String wxid;

    public String getWxid() {
        return wxid;
    }

    public void setWxid(String wxid) {
        this.wxid = wxid;
    }
}
