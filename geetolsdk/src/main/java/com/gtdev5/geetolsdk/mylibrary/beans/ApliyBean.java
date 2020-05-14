package com.gtdev5.geetolsdk.mylibrary.beans;

/**
 * Created by zl
 * 2020/05/14
 * 支付宝返回参数
 */
public class ApliyBean extends ResultBean {
    /**
     * id
     */
    private String appid;
    /**
     * 金额
     */
    private float amount;
    /**
     * 支付宝调起支付字符串
     */
    private String package_str;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getPackage_str() {
        return package_str;
    }

    public void setPackage_str(String package_str) {
        this.package_str = package_str;
    }
}
