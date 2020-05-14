package com.gtdev5.geetolsdk.mylibrary.beans;

/**
 * Created by zl
 * 2020/05/14
 * 软件更新返回参数
 */
public class GetNewBean extends ResultBean {
    /**
     * 是否有新版本
     */
    private boolean hasnew;
    /**
     * 下载地址
     */
    private String downurl;
    /**
     * 更新日志
     */
    private String log;
    /**
     * 版本号
     */
    private int vercode;
    /**
     * 版本名称
     */
    private String vername;

    public boolean isHasnew() {
        return hasnew;
    }

    public void setHasnew(boolean hasnew) {
        this.hasnew = hasnew;
    }

    public String getDownurl() {
        return downurl;
    }

    public void setDownurl(String downurl) {
        this.downurl = downurl;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public int getVercode() {
        return vercode;
    }

    public void setVercode(int vercode) {
        this.vercode = vercode;
    }

    public String getVername() {
        return vername;
    }

    public void setVername(String vername) {
        this.vername = vername;
    }
}
