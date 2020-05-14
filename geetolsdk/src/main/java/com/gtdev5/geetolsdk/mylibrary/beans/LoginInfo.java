package com.gtdev5.geetolsdk.mylibrary.beans;

import java.io.Serializable;

/**
 * Created by zl
 * 2020/05/14
 * 登录信息返回参数
 */
public class LoginInfo implements Serializable {
    /**
     * 用户id
     */
    private String user_id;
    /**
     * 用户相关接口需要验证的一个参数
     */
    private String ukey;
    /**
     * 用户头像
     */
    private String headimg;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 是否绑定微信 0：未绑定 1：绑定
     */
    private String wechat;
    /**
     * 用户名
     */
    private String name;
    /**
     * 手机号
     */
    private String tel;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUkey() {
        return ukey;
    }

    public void setUkey(String ukey) {
        this.ukey = ukey;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}

