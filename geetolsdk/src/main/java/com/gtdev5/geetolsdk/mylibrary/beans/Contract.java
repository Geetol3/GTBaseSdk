package com.gtdev5.geetolsdk.mylibrary.beans;

import java.io.Serializable;

/**
 * Created by zl
 * 2020/05/14
 * app联系方式返回参数
 */
public class Contract  implements Serializable {
    /**
     * 联系方式
     */
    private String txt;
    /**
     * 联系号码
     */
    private String num;

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
