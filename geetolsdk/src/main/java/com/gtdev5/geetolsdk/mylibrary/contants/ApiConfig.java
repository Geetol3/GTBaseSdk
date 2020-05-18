package com.gtdev5.geetolsdk.mylibrary.contants;

import com.google.gson.Gson;
import com.gtdev5.geetolsdk.mylibrary.beans.XResp;
import com.gtdev5.geetolsdk.mylibrary.util.SpUtils;

/**
 * Created by zl
 * 2020/05/18
 * api数据获取
 */
public class ApiConfig {
    private static final String API_CONFIG = "gt_base_api_config";
    private static ApiConfig instance;
    private XResp xResp;
    private Gson gson;

    public static ApiConfig getInstance() {
        if (instance == null) {
            synchronized (ApiConfig.class) {
                if (instance == null) {
                    instance = new ApiConfig();
                }
            }
        }
        return instance;
    }

    private ApiConfig() {
        gson = new Gson();
        xResp = gson.fromJson(SpUtils.getInstance().getString(API_CONFIG), XResp.class);
        if (xResp == null) xResp = new XResp();
    }

    /**
     * 保存信息
     */
    public void saveApiConfig(XResp xResp) {
        this.xResp = xResp;
        SpUtils.getInstance().putString(API_CONFIG, gson.toJson(xResp));
    }

    /**
     * 获取api信息
     */
    public XResp getXResp() {
        return xResp;
    }

    /**
     * 获取域名
     */
    public String getHostUrl() {
        if (xResp != null) {
            return xResp.getHost();
        }
        return "";
    }

    /**
     * 根据action获取别名
     */
    public String getCommand(String action) {
        if (xResp != null && xResp.getxRedirect() != null && xResp.getxRedirect().size() > 0) {
            for (XResp.XRedirect xRedirect : xResp.getxRedirect()) {
                if (xRedirect.getAction().equals(action)) {
                    return xRedirect.getCommand();
                }
            }
        }
        return "";
    }
}
