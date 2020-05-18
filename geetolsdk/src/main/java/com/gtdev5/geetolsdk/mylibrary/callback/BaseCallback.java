package com.gtdev5.geetolsdk.mylibrary.callback;

import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zl
 * 2020/05/18
 * 基础回调
 */
public abstract class BaseCallback<T> {
    /**
     * 用于方便json解析
     */
    public Type mType;

    /**
     * 把type转换成对应的类
     */
    public static Type getSuperclassTypeParamter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type paramter.");
        }
        ParameterizedType parameterizedType = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterizedType.getActualTypeArguments()[0]);
    }

    /**
     * 实例化的时候获取type的Class类型
     */
    public BaseCallback() {
        mType = getSuperclassTypeParamter(getClass());
    }

    /**
     * 请求之前调用
     */
    public abstract void onRequestBefore();

    /**
     * 请求失败调用
     */
    public abstract void onFailure(Request request, Exception e);

    /**
     * 请求成功调用
     */
    public abstract void onSuccess(Response response, T t);

    /**
     * 请求成功但是有错误的时候调用,例如Gson解析
     */
    public abstract void onError(Response response, int errorCode, Exception e);
}
