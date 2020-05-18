package com.gtdev5.geetolsdk.mylibrary.callback;

import java.io.IOException;

import okhttp3.Request;

/**
 * Created by zl
 * 2020/05/18
 * 数据回调
 */
public interface DataCallBack {
    /**
     * 失败
     */
    void requestFailure(Request request, IOException io);

    /**
     * 成功
     */
    void requestSuceess(String result) throws Exception;
}
