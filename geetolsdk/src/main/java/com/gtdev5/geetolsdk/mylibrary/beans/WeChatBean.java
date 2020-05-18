package com.gtdev5.geetolsdk.mylibrary.beans;

/**
 * Created by zl
 * 2020/05/18
 * 微信返回参数
 */
public class WeChatBean extends ResultBean {
    /**
     * 微信appid
     */
    private String appid;
    /**
     * 金额
     */
    private String amount;
    /**
     * 时间戳
     */
    private String timestramp;
    /**
     * 随机数
     */
    private String nonce_str;
    /**
     * 固定值 sign=WXPay
     */
    private String package_str;
    /**
     * 签名
     */
    private String sign;
    /**
     * 签名字符串
     */
    private String sign_str;
    /**
     * 可将该数值生成二维码展示出来进行扫码支付
     */
    private String qrcode;
    /**
     * 为拉起微信支付收银台的中间页面，可通过访问该url来拉起微信客户端，完成支付,mweb_url的有效期为5分钟。
     */
    private String mweburl;
    /**
     * 微信商户号
     */
    private String partnerId;
    /**
     * 预支付单id
     */
    private String prepayid;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTimestramp() {
        return timestramp;
    }

    public void setTimestramp(String timestramp) {
        this.timestramp = timestramp;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getPackage_str() {
        return package_str;
    }

    public void setPackage_str(String package_str) {
        this.package_str = package_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSign_str() {
        return sign_str;
    }

    public void setSign_str(String sign_str) {
        this.sign_str = sign_str;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getMweburl() {
        return mweburl;
    }

    public void setMweburl(String mweburl) {
        this.mweburl = mweburl;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }
}
