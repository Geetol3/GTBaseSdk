package com.gtdev5.geetolsdk.mylibrary.util;

import android.content.Context;
import android.util.Log;


import com.gtdev5.geetolsdk.mylibrary.initialization.GTBaseSdk;

/**
 * Created by zl
 * 2020/05/19
 * 反射工具类
 */
public class CPResourceUtils {
    private static Context mContext;

    public static void init(Context context) {
        if (context != null) {
            mContext = context;
        } else {
            LogUtils.e(GTBaseSdk.TAG, "未初始化lib");
        }
    }

    /**
     * 通过反射获取layout
     */
    public static int getLayoutId(String paramString) {
        return mContext.getResources().getIdentifier(paramString, "layout", mContext.getPackageName());
    }

    /**
     * 通过反射获取string
     */
    public static int getStringId(String paramString) {
        return mContext.getResources().getIdentifier(paramString, "string", mContext.getPackageName());
    }

    /**
     * 通过反射获取drawable
     */
    public static int getDrawableId(String paramString) {
        return mContext.getResources().getIdentifier(paramString, "drawable", mContext.getPackageName());
    }

    /**
     * 通过反射获取style
     */
    public static int getStyleId(String paramString) {
        return mContext.getResources().getIdentifier(paramString, "style", mContext.getPackageName());
    }

    /**
     * 通过反射获取id
     */
    public static int getId(String paramString) {
        return mContext.getResources().getIdentifier(paramString, "id", mContext.getPackageName());
    }

    /**
     * 通过反射获取color
     */
    public static int getColor(String paramString) {
        return mContext.getResources().getIdentifier(paramString, "color", mContext.getPackageName());
    }

    /**
     * 通过反射获取array
     */
    public static int getArrayId(String paramString) {
        return mContext.getResources().getIdentifier(paramString, "array", mContext.getPackageName());
    }

    /**
     * 获取资源目录值
     */
    public static String getString(String key) {
        return mContext.getResources().getString(getStringId(key));
    }

    /**
     * 获取设备标识码
     */
    public static String getDevice() {
        return DeviceUtils.getSpDeviceId();
    }
}
