package com.gtdev5.geetolsdk.mylibrary.beans;

import java.io.Serializable;

/**
 * Created by zl
 * 2020/05/14
 * 商品支付信息返回参数
 */
public class Gds implements Serializable {
    /**
     * 商品值
     */
    private String value;
    /**
     * 商品标题
     */
    private String name;
    /**
     * 支付宝价格
     */
    private String price;
    /**
     * 支付id
     */
    private int gid;
    /**
     * 商品介绍
     */
    private String remark;
    /**
     * 商品原价
     */
    private String original;
    /**
     * 商品背景
     */
    private String bg1;
    /**
     * 商品图标
     */
    private String bg2;
    /**
     * 微信价格
     */
    private String xwprice;
    /**
     * 支付方式：[1]微信[2]支付宝[3]小程序[4]H5
     */
    private String payway;

    public String getXwprice() {
        return xwprice;
    }

    public void setXwprice(String xwprice) {
        this.xwprice = xwprice;
    }

    public String getPayway() {
        return payway;
    }

    public void setPayway(String payway) {
        this.payway = payway;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    private boolean select;

    public String getBg1() {
        return bg1;
    }

    public void setBg1(String bg1) {
        this.bg1 = bg1;
    }

    public String getBg2() {
        return bg2;
    }

    public void setBg2(String bg2) {
        this.bg2 = bg2;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }
}