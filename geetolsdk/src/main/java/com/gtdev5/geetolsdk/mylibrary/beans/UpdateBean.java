package com.gtdev5.geetolsdk.mylibrary.beans;

import java.util.List;

/**
 * Created by zl
 * 2020/05/18
 * 数据更新
 */
public class UpdateBean extends ResultBean {
    /**
     * vip信息
     */
    private Vip vip;
    /**
     * 图片广告信息(后台配置)
     */
    private List<Ads> ads;
    /**
     * 会员购买信息(后台配置)
     */
    private List<Gds> gds;
    /**
     * 应用内开关(后台配置)
     */
    private List<Swt> swt;
    /**
     * 客服联系方式(后台配置)
     */
    private Contract contract;
    /**
     * 帮助链接(帮助文档后台配置)
     */
    private String hpurl;
    /**
     * 微信appid
     */
    private Config config;
    /**
     * 分享链接
     */
    private String share_url;
    /**
     * 限制地址
     */
    private List<Region> region;
    /**
     * ip地址
     */
    private String ip;

    public UpdateBean() {
    }

    public Vip getVip() {
        return vip;
    }

    public void setVip(Vip vip) {
        this.vip = vip;
    }

    public List<Ads> getAds() {
        return ads;
    }

    public void setAds(List<Ads> ads) {
        this.ads = ads;
    }

    public List<Gds> getGds() {
        return gds;
    }

    public void setGds(List<Gds> gds) {
        this.gds = gds;
    }

    public List<Swt> getSwt() {
        return swt;
    }

    public void setSwt(List<Swt> swt) {
        this.swt = swt;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public String getHpurl() {
        return hpurl;
    }

    public void setHpurl(String hpurl) {
        this.hpurl = hpurl;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public List<Region> getRegion() {
        return region;
    }

    public void setRegion(List<Region> region) {
        this.region = region;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
