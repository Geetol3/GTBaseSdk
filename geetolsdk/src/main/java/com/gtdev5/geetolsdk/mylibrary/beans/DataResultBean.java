package com.gtdev5.geetolsdk.mylibrary.beans;

/**
 * Created by zl
 * 2020/05/14
 * 公共带字典返回参数
 */
public class DataResultBean<T> extends ResultBean {
    private T data;

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
