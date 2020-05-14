package com.gtdev5.geetolsdk.mylibrary.beans;

import java.io.Serializable;

/**
 * Created by zl
 * 2020/05/14
 * 软件图片返回参数(启动图、banner图)
 */
public class Ads implements Serializable {
    /**
     * 图片名称
     */
    private String name;
    /**
     * 跳转链接
     */
    private String link;
    /**
     * 图片位置
     */
    private String pos;
    /**
     * 图片链接
     */
    private String img;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
