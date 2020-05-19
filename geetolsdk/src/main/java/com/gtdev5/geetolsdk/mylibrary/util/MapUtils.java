package com.gtdev5.geetolsdk.mylibrary.util;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zl
 * 2020/05/19
 * 接口传参
 */
public class MapUtils {
    private static Context mContext;

    /**
     * 在Application里面初始化，就能全局调用
     *
     * @param context
     */
    public static void init(Context context) {
        if (mContext == null) {
            mContext = context;
        }
    }

    /**
     * 通用Map
     * (无参的方法通用调取)
     */
    public static Map<String, String> getCurrencyMap() {
        Map<String, String> map = new HashMap<>();
        map.put("appid", CPResourceUtils.getString("appid"));
        map.put("sign", null);
        map.put("device", DeviceUtils.getSpDeviceId());
        // 2019.11.11新增(可为空)
        map.put("user_id", Utils.getUserId());
        map.put("user_key", Utils.getUserKey());
        return map;
    }

    /**
     * 注册
     */
    public static Map<String, String> getRegistMap() {
        Map<String, String> map = new HashMap<>();
        map.putAll(getCurrencyMap());
        map.put("mac", MacUtils.getMacAddr(mContext));
        map.put("brand", SystemUtils.getDeviceBrand());
        map.put("model", SystemUtils.getSystemModel());
        map.put("widthpix", SystemUtils.getWith(mContext) + "");
        map.put("heightpix", SystemUtils.getHeight(mContext) + "");
        map.put("vercode", SystemUtils.getSystemVersion());
        map.put("agent", SystemUtils.getChannelInfo(mContext) + "");
        return map;
    }

    /**
     * 版本更新
     */
    public static Map<String, String> getNewMap() {
        Map<String, String> map = new HashMap<>();
        map.putAll(getCurrencyMap());
        map.put("code", Utils.getVersion(mContext));
        return map;
    }

    /**
     * 订单详情
     *
     * @param type   订单类型    1:支付    2:打赏
     * @param pid    商品ID
     * @param amount 打赏订单必填,支付可不填
     * @param pway   支付类型    1:微信    2:支付宝
     */
    public static Map<String, String> getOrder(int type, int pid, float amount, int pway) {
        Map<String, String> map = new HashMap<>();
        map.putAll(getCurrencyMap());
        map.put("type", String.valueOf(type));
        map.put("pid", String.valueOf(pid));
        map.put("amount", String.valueOf(amount));
        map.put("pway", String.valueOf(pway));
        return map;
    }

    /**
     * 获取验证码
     *
     * @param tel      手机号
     * @param tpl      信息模板（SMSCode已提供基本类型）
     * @param sms_sign 短信签名
     */
    public static Map<String, String> getVarCode(String tel, String tpl, String sms_sign) {
        Map<String, String> map = new HashMap<>();
        map.putAll(getCurrencyMap());
        map.put("tel", tel);
        // 2019.11.11新增
        map.put("tpl", tpl);
        map.put("sms_sign", sms_sign);
        return map;
    }

    /**
     * 手机号动态登陆
     * 2019.11.11新增
     *
     * @param tel     手机号
     * @param smscode 短信认证码
     * @param smskey  短信认证码校验key
     */
    public static Map<String, String> getUserCodeLogin(String tel, String smscode, String smskey) {
        Map<String, String> map = new HashMap<>();
        map.putAll(getCurrencyMap());
        map.put("tel", tel);
        map.put("smscode", smscode);
        map.put("smskey", smskey);
        return map;
    }

    /**
     * 微信登录
     *
     * @param open_id  微信open_id
     * @param nickname 微信昵称
     * @param sex      性别
     * @param headurl  头像URL地址
     */
    public static Map<String, String> getWeChatLogin(String open_id, String nickname, String sex, String headurl) {
        Map<String, String> map = new HashMap<>();
        map.putAll(MapUtils.getCurrencyMap());
        map.put("open_id", open_id);
        map.put("nickname", nickname);
        map.put("sex", sex);
        map.put("headurl", headurl);
        return map;
    }
}
