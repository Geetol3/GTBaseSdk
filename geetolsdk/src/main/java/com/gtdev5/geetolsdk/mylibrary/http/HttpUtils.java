package com.gtdev5.geetolsdk.mylibrary.http;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.gtdev5.geetolsdk.mylibrary.beans.DataResultBean;
import com.gtdev5.geetolsdk.mylibrary.beans.LoginInfo;
import com.gtdev5.geetolsdk.mylibrary.beans.ResultBean;
import com.gtdev5.geetolsdk.mylibrary.beans.UpdateBean;
import com.gtdev5.geetolsdk.mylibrary.callback.BaseCallback;
import com.gtdev5.geetolsdk.mylibrary.callback.DataCallBack;
import com.gtdev5.geetolsdk.mylibrary.contants.API;
import com.gtdev5.geetolsdk.mylibrary.contants.ApiConfig;
import com.gtdev5.geetolsdk.mylibrary.util.CPResourceUtils;
import com.gtdev5.geetolsdk.mylibrary.util.DataSaveUtils;
import com.gtdev5.geetolsdk.mylibrary.util.DesUtils;
import com.gtdev5.geetolsdk.mylibrary.util.DeviceUtils;
import com.gtdev5.geetolsdk.mylibrary.util.GsonUtils;
import com.gtdev5.geetolsdk.mylibrary.util.LogUtils;
import com.gtdev5.geetolsdk.mylibrary.util.MapUtils;
import com.gtdev5.geetolsdk.mylibrary.util.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by zl
 * 2020/05/18
 * Http请求类
 */
public class HttpUtils {
    /**
     * 请求超时时间
     */
    public static final long TIME_OUT = 10;
    /**
     * get请求
     */
    public static final int GET_HTTP_TYPE = 1;
    /**
     * post请求
     */
    public static final int POST_HTTP_TYPE = 2;
    /**
     * 上传请求
     */
    public static final int UPLOAD_HTTP_TYPE = 3;
    /**
     * 下载请求
     */
    public static final int DOWNLOAD_HTTP_TYPE = 4;
    private static HttpUtils mHttpUtils;
    private OkHttpClient mOkHttpClient;
    private Handler mHandler;
    private Request request = null;
    private MessageDigest alga;
    private Map<String, String> resultMap;
    private Gson gson;

    private HttpUtils() {
        try {
            mOkHttpClient = new OkHttpClient();
            mOkHttpClient.newBuilder().connectTimeout(TIME_OUT, TimeUnit.SECONDS).readTimeout(
                    TIME_OUT, TimeUnit.SECONDS).writeTimeout(TIME_OUT, TimeUnit.SECONDS);
            mHandler = new Handler(Looper.getMainLooper());
            gson = new Gson();
            alga = MessageDigest.getInstance("SHA-1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HttpUtils getInstance() {
        if (mHttpUtils == null) {
            synchronized (HttpUtils.class) {
                if (mHttpUtils == null) {
                    mHttpUtils = new HttpUtils();
                }
            }
        }
        return mHttpUtils;
    }

    /**
     * 提供对外调用的请求接口
     *
     * @param callBack   回调接口
     * @param url        路径
     * @param type       请求类型
     * @param paramKey   请求参数
     * @param paramValue 请求值
     */
    public static void httpsNetWorkRequest(final DataCallBack callBack, final String url, final int type,
                                           final String[] paramKey, final Object[] paramValue) {
        getInstance().inner_httpsNetWorkRequest(callBack, url, type, paramKey, paramValue);
    }

    /**
     * 内部处理请求的方法
     *
     * @param callBack   回调接口
     * @param url        路径
     * @param type       请求类型
     * @param paramKey   请求参数
     * @param paramValue 请求值
     */
    private void inner_httpsNetWorkRequest(final DataCallBack callBack, final String url, final int type,
                                           final String[] paramKey, final Object[] paramValue) {
        RequestBody requestBody = null;
        FormBody.Builder builder = new FormBody.Builder();
        Map<String, String> map = new TreeMap<String, String>();
        map.put("appid", CPResourceUtils.getString("appid"));
        map.put("sign", null);
        map.put("device", DeviceUtils.getSpDeviceId());
        if (paramKey != null) {
            for (int i = 0; i < paramKey.length; i++) {
                map.put(paramKey[i], String.valueOf(paramValue[i]));
            }
            resultMap = sortMapByKey(map);
        }
        String str = "";
        int num = 0;
        boolean isFirst = true;
        switch (type) {
            case GET_HTTP_TYPE:
                request = new Request.Builder().url(API.getUrl(url)).build();
                break;
            case POST_HTTP_TYPE:
                /**
                 * 循环遍历获取key值，拼接sign字符串
                 */
                for (Map.Entry<String, String> entry : resultMap.entrySet()) {
                    if (entry.getValue() == null) {
                        continue;
                    }
                    num++;
                    if (isFirst) {
                        str += entry.getKey() + "=" + Base64.encodeToString(entry.getValue().getBytes(),
                                Base64.DEFAULT).trim();
                        isFirst = !isFirst;
                    } else {
                        str = str.trim();
                        str += "&" + entry.getKey() + "=" + Base64.encodeToString(entry.getValue().getBytes(),
                                Base64.DEFAULT).trim();
                        if (num == resultMap.size() - 1) {
                            str += "&" + "key" + "=" + CPResourceUtils.getString("appkey");
                        }
                    }
                }
                str = str.replace("\n", "");//去除换行
                str = str.replace("\\s", "");//去除空格
                isFirst = !isFirst;
                alga.update(str.getBytes());
                /**
                 * 循环遍历value值，添加到表单
                 */
                for (Map.Entry<String, String> entry : resultMap.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    if (value == null) {
                        value = null;
                    }
                    if (key.equals("sign")) {
                        value = Utils.byte2hex(alga.digest());
                    } else if (key.equals("key")) {
                        continue;
                    }
                    builder.add(key, value);
                }
                requestBody = builder.build();
                request = new Request.Builder().url(API.getUrl(url)).post(requestBody).build();
                break;
            case UPLOAD_HTTP_TYPE:
                MultipartBody.Builder multipartBody = new MultipartBody.Builder("-----")
                        .setType(MultipartBody.FORM);
                if (paramKey != null && paramValue != null) {
                    for (int i = 0; i < paramKey.length; i++) {
                        multipartBody.addFormDataPart(paramKey[i], String.valueOf(paramValue[i]));
                    }
                    requestBody = multipartBody.build();
                }
                request = new Request.Builder().url(API.getUrl(url)).post(requestBody).build();
                break;
            default:
                break;
        }
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                deliverDataFailure(request, e, callBack);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = null;
                try {
                    result = response.body().string();
                } catch (IOException e) {
                    deliverDataFailure(request, e, callBack);
                }
                deliverDataSuccess(result, callBack);
            }
        });
    }

    /**
     * 分发失败的时候回调
     */
    private void deliverDataFailure(final Request request, final IOException e, final DataCallBack callBack) {
        mHandler.post(() -> {
            if (callBack != null) {
                callBack.requestFailure(request, e);
            }
        });
    }

    /**
     * 分发成功的时候回调
     */
    private void deliverDataSuccess(final String result, final DataCallBack callBack) {
        mHandler.post(() -> {
            if (callBack != null) {
                try {
                    callBack.requestSuceess(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * map根据key值比较大小
     */
    private static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, String> sortMap = new TreeMap<String, String>((str1, str2) -> str1.compareTo(str2));
        sortMap.putAll(map);
        return sortMap;
    }

    /**
     * 内部处理Map集合
     * 得到from表单 (post请求)
     */
    private RequestBody getRequestBody(Map<String, String> map) {
        RequestBody requestBody = null;
        FormBody.Builder builder = new FormBody.Builder();
        resultMap = sortMapByKey(map);
        LogUtils.e("请求参数(map)：", resultMap.toString());
        String str = "";
        int num = 0;
        boolean isFirst = true;
        /**
         * 循环遍历获取key值，拼接sign字符串
         */
        for (Map.Entry<String, String> entry : resultMap.entrySet()) {
            if (entry.getValue() == null) {
                continue;
            }
            num++;
            if (isFirst) {
                str += entry.getKey() + "=" + Base64.encodeToString(entry.getValue().getBytes(),
                        Base64.DEFAULT).trim();
                isFirst = !isFirst;
            } else {
                str = str.trim();
                str += "&" + entry.getKey() + "=" + Base64.encodeToString(entry.getValue().getBytes(),
                        Base64.DEFAULT).trim();
                if (num == resultMap.size() - 1) {
                    str += "&" + "key" + "=" + CPResourceUtils.getString("appkey");
                }
            }
        }
        str = str.replace("\n", "");//去除换行
        str = str.replace("\\s", "");//去除空格
        LogUtils.e("请求参数(String)：", str);
        isFirst = !isFirst;
        alga.update(str.getBytes());
        /**
         * 循环遍历value值，添加到表单
         */
        for (Map.Entry<String, String> entry : resultMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value == null) {
                value = null;
            }
            if (key.equals("sign")) {
                value = Utils.byte2hex(alga.digest());
            } else if (key.equals("key")) {
                continue;
            }
            builder.add(key, value);
        }
        requestBody = builder.build();
        return requestBody;
    }

    /**
     * 提供给外部调用的注册接口
     *
     * @param callback 回调函数
     */
    public void postRegister(BaseCallback callback) {
        post(API.getUrl(ApiConfig.getInstance().getCommand(API.REGISTER_DEVICE)),
                MapUtils.getRegistMap(), callback);
    }

    /**
     * 提供给外部调用的更新数据接口
     *
     * @param callback 回调函数
     */
    public void postUpdate(BaseCallback callback) {
        post(API.getUrl(ApiConfig.getInstance().getCommand(API.UPDATE)),
                MapUtils.getCurrencyMap(), callback, API.UPDATE);
    }

    /**
     * 提供给外部调用的版本更新接口
     *
     * @param callback 回调函数
     */
    public void postNews(BaseCallback callback) {
        post(API.getUrl(ApiConfig.getInstance().getCommand(API.GETNEW)),
                MapUtils.getNewMap(), callback);
    }

    /**
     * 提供给外部调用的支付订单接口
     *
     * @param type     订单类型    1:支付    2:打赏
     * @param pid      商品ID
     * @param amount   打赏订单必填,支付可不填
     * @param pway     支付类型    1:微信    2:支付宝
     * @param callback 回调函数
     */
    public void postOrder(int type, int pid, float amount, int pway, BaseCallback callback) {
        post(API.getUrl(ApiConfig.getInstance().getCommand(API.ORDER_OD)),
                MapUtils.getOrder(type, pid, amount, pway), callback);
    }

    /**
     * 提供外部调用的获取验证码接口
     *
     * @param tel      手机号
     * @param tpl      信息模板（SMSCode已提供基本类型）
     * @param sms_sign 短信签名
     * @param callback 回调函数
     */
    public void getVarCode(String tel, String tpl, String sms_sign, BaseCallback callback) {
        post(API.getUrl(ApiConfig.getInstance().getCommand(API.GET_VARCODE)),
                MapUtils.getVarCode(tel, tpl, sms_sign), callback);
    }

    /**
     * 提供外部调用的获取阿里云返回参数接口
     *
     * @param callback 回调函数
     */
    public void getAliOss(BaseCallback callback) {
        post(API.getUrl(ApiConfig.getInstance().getCommand(API.GET_ALIOSS)),
                MapUtils.getCurrencyMap(), callback, API.GET_ALIOSS);
    }

    /**
     * 动态码登录接口
     *
     * @param tel      手机号
     * @param smscode  短信验证码
     * @param smskey   短信认证码校验key
     * @param callback
     */
    public void userCodeLogin(String tel, String smscode, String smskey, BaseCallback callback) {
        post(API.getUrl(ApiConfig.getInstance().getCommand(API.USER_LOGIN_CODE)),
                MapUtils.getUserCodeLogin(tel, smscode, smskey), callback, API.USER_LOGIN_CODE);
    }

    /**
     * 登陆校验
     *
     * @param callback
     */
    public void checkLogin(BaseCallback callback) {
        post(API.getUrl(ApiConfig.getInstance().getCommand(API.USER_LOGIN_CHECK)),
                MapUtils.getCurrencyMap(), callback, API.USER_LOGIN_CHECK);
    }

    /**
     * 微信登录
     */
    public void wechatLogin(String open_id, String nickname, String sex, String headurl, BaseCallback callback) {
        alga.digest();
        post(API.getUrl(ApiConfig.getInstance().getCommand(API.USER_WECHAT_LOGIN)),
                MapUtils.getWeChatLogin(open_id, nickname, sex, headurl), callback);
    }

    /**
     * 针对微信加密机制的问题，提供一个外部方法来解决
     */
    public void changeDigest() {
        if (alga != null) {
            alga.digest();
        }
    }

    /**
     * 内部提供的post请求方法
     *
     * @param url      请求路径
     * @param params   请求参数(表单)
     * @param callback 回调函数
     */
    public void post(String url, Map<String, String> params, final BaseCallback callback) {
        post(url, params, callback, "default");
    }

    public void post(String url, Map<String, String> params, final BaseCallback callback, String requestType) {
        //请求之前调用(例如加载动画)
        callback.onRequestBefore();
        mOkHttpClient.newCall(getRequest(url, params)).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //返回失败
                callbackFailure(call.request(), callback, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    //返回成功回调
                    String result = response.body().string();
                    try {
                        result = DesUtils.getResult(result);
                        LogUtils.e("请求回调：", result);
                        if (requestType.equals(API.USER_LOGIN_CODE)) {
                            // 保存用户信息
                            DataResultBean info = GsonUtils.getFromClass(result, DataResultBean.class);
                            if (info != null && info.isIssucc()) {
                                LoginInfo loginInfo = GsonUtils.getFromClass(info.getData().toString(), LoginInfo.class);
                                Utils.setLoginInfo(loginInfo.getUser_id(),
                                        loginInfo.getUkey(),
                                        loginInfo.getHeadimg());
                            }
                        } else if (requestType.equals(API.GET_ALIOSS)) {
                            // 获取阿里云信息
                            try {
                                JSONObject jsonObject = new JSONObject(result);
                                if (jsonObject.getBoolean("issucc")) {
                                    String data = jsonObject.getString("data");
                                    if (!TextUtils.isEmpty(data)) {
                                        LogUtils.e("阿里云", "阿里云数据：" + data);
                                        Utils.setAliOssParam(data);
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else if (requestType.equals(API.UPDATE)) {
                            // 获取所有数据
                            UpdateBean updateBean = GsonUtils.getFromClass(result, UpdateBean.class);
                            if (updateBean != null) {
                                DataSaveUtils.getInstance().saveAppData(updateBean);
                            }
                        } else if (requestType.equals(API.USER_LOGIN_CHECK)) {
                            // 校验登陆
                            ResultBean resultBean = GsonUtils.getFromClass(result, ResultBean.class);
                            if (resultBean != null && !resultBean.isIssucc()) {
                                // 已在别的设备登陆，清空本机登陆状态
                                Utils.setLoginInfo("", "", "");
                            }
                        }
                        if (callback.mType == String.class) {
                            //如果我们需要返回String类型
                            callbackSuccess(response, result, callback);
                        } else {
                            //如果返回是其他类型,则用Gson去解析
                            try {
                                Object o = gson.fromJson(result, callback.mType);
                                callbackSuccess(response, o, callback);
                            } catch (JsonSyntaxException e) {
                                e.printStackTrace();
                                callbackError(response, callback, e);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    callbackError(response, callback, null);
                }
            }
        });
    }

    /**
     * 得到Request
     *
     * @param url    请求路径
     * @param params from表单
     * @return
     */
    private Request getRequest(String url, Map<String, String> params) {
        //可以从这么划分get和post请求，暂时只支持post
        LogUtils.e("请求参数(url)：", url);
        return new Request.Builder().url(url).post(getRequestBody(params)).build();
    }

    /**
     * 在主线程中执行成功回调
     *
     * @param response 请求响应
     * @param o        类型
     * @param callback 回调函数
     */
    private void callbackSuccess(final Response response, final Object o, final BaseCallback<Object> callback) {
        mHandler.post(() -> callback.onSuccess(response, o));
    }

    /**
     * 在主线程中执行错误回调
     *
     * @param response 请求响应
     * @param callback 回调函数
     * @param e        响应错误异常
     */
    private void callbackError(final Response response, final BaseCallback callback, Exception e) {
        mHandler.post(() -> callback.onError(response, response.code(), e));
    }

    /**
     * 在主线程中执行失败回调
     *
     * @param request  请求链接
     * @param callback 回调韩素和
     * @param e        响应错误异常
     */
    private void callbackFailure(final Request request, final BaseCallback callback, final Exception e) {
        mHandler.post(() -> callback.onFailure(request, e));
    }
}
