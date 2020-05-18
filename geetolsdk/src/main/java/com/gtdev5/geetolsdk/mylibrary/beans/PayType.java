package com.gtdev5.geetolsdk.mylibrary.beans;

/**
 * Created by zl
 * 2020/05/18
 * 支付枚举类型
 */
public enum PayType {
    BOTH_ZFB_WECHAT,//支付宝和微信都有
    ONLY_ZFB,//只有支付宝
    ONLY_WECHAT,//只有微信
    NO_PAY//没有支付方式
}
