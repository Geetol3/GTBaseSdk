package com.gtdev5.geetolsdk.mylibrary.beans;

import java.io.Serializable;

/**
 * Created by zl
 * 2020/05/18
 * 开关
 */
public class Swt implements Serializable {
    /**
     * 开关名称
     */
    private String name;
    /**
     * 开关代号
     */
    private String code;
    /**
     * 值1的值
     */
    private int val1;
    /**
     * 值2的值
     */
    private String val2;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getVal1() {
        return val1;
    }

    public void setVal1(int val1) {
        this.val1 = val1;
    }

    public String getVal2() {
        return val2;
    }

    public void setVal2(String val2) {
        this.val2 = val2;
    }
}