package com.gtdev5.geetolsdk.mylibrary.contants;

/**
 * Created by zl
 * 2020/05/18
 * 接口管理
 */
public interface API {
    /**
     * 设备注册
     */
    public static String REGISTER_DEVICE = "reg";

    /**
     * 更新数据
     */
    public static String UPDATE = "update";

    /**
     * 新版检测
     */
    public static String GETNEW = "getnew";

    /**
     * 支付接口
     */
    public static String ORDER_OD = "order.od";

    /**
     * 获取验证码
     */
    public static String GET_VARCODE = "getvarcode";

    /**
     * 获取阿里云oss参数
     */
    public static String GET_ALIOSS = "get_ali_oss";

    /**
     * 手机动态码登陆
     */
    public static String USER_LOGIN_CODE = "sms.userlogin";

    /**
     * 校验登录状态
     */
    public static String USER_LOGIN_CHECK = "sms.statelogin";

    /**
     * 微信登录
     */
    public static String USER_WECHAT_LOGIN = "pub_wechat_login";
}