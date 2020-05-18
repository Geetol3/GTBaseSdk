package com.gtdev5.geetolsdk.mylibrary.beans;

import android.text.TextUtils;

import java.util.Map;

/**
 * Created by zl
 * 2020/05/18
 * 支付宝支付结果回调
 */
public class PayResult {
    /**
     * 支付回调状态码
     * 9000：支付成功 8000：正在处理中 4000：订单支付失败 5000：重复请求 6001：已取消支付
     * 6002：网络连接出错 6004：正在处理中
     */
    private String resultStatus;
    /**
     * 回调结果
     */
    private String result;
    private String memo;

    public PayResult(Map<String, String> rawResult) {
        if (rawResult == null) {
            return;
        }
        for (String key : rawResult.keySet()) {
            if (TextUtils.equals(key, "resultStatus")) {
                resultStatus = rawResult.get(key);
            } else if (TextUtils.equals(key, "result")) {
                result = rawResult.get(key);
            } else if (TextUtils.equals(key, "memo")) {
                memo = rawResult.get(key);
            }
        }
    }

    @Override
    public String toString() {
        return "resultStatus={" + resultStatus + "};memo={" + memo
                + "};result={" + result + "}";
    }

    public String getResultStatus() {
        return resultStatus;
    }

    public String getMemo() {
        return memo;
    }

    public String getResult() {
        return result;
    }
}
