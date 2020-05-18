package com.gtdev5.geetolsdk.mylibrary.beans;

import java.io.Serializable;

/**
 * Created by zl
 * 2020/05/18
 * vip信息
 */
public class Vip implements Serializable {
    /**
     * vip等级
     */
    private String viplevel;
    /**
     * 购买个数(多开类)
     */
    private int count;
    /**
     * 购买时间
     */
    private String time;
    /**
     * 是否过期
     */
    private boolean isout;
    /**
     * vip标签
     */
    private String viptag;
    /**
     * 用户注册时间
     */
    private String ctime;

    public String getViptag() {
        return viptag;
    }

    public void setViptag(String viptag) {
        this.viptag = viptag;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isIsout() {
        return isout;
    }

    public void setIsout(boolean isout) {
        this.isout = isout;
    }

    public String getViplevel() {
        return viplevel;
    }

    public void setViplevel(String viplevel) {
        this.viplevel = viplevel;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }
}
