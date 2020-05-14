package com.gtdev5.geetolsdk.mylibrary.beans;

import java.util.List;

/**
 * Created by zl
 * 2020/05/14
 * 公共列表返回参数
 */
public class ListResultBean<T> extends ResultBean {
    /**
     * 总页数
     */
    private int page;
    /**
     * 总条数
     */
    private int count;

    private List<T> items;

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<T> getItems() {
        return this.items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
