package com.gtdev5.geetolsdk.mylibrary.initialization;

import android.content.Context;

import com.gtdev5.geetolsdk.mylibrary.beans.XResp;
import com.gtdev5.geetolsdk.mylibrary.contants.ApiConfig;
import com.gtdev5.geetolsdk.mylibrary.util.CPResourceUtils;
import com.gtdev5.geetolsdk.mylibrary.util.DesUtils;
import com.gtdev5.geetolsdk.mylibrary.util.MapUtils;
import com.gtdev5.geetolsdk.mylibrary.util.SpUtils;
import com.gtdev5.geetolsdk.mylibrary.util.ToastUtils;

/**
 * Created by zl
 * 2020/05/18
 * sdk初始化
 */
public class GTBaseSdk {
    public static String TAG = "GTBaseSdk";

    private static Context mContext;

    public static void init(Context context) {
        try {
            if (mContext == null) {
                mContext = context;
            }
            SpUtils.getInstance().init(mContext);
            CPResourceUtils.init(mContext);
            ToastUtils.init(mContext);
            MapUtils.init(mContext);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Context getmContext() {
        return mContext;
    }

    public static void init(Context context, String fileName) {
        init(context);
        try {
            /**
             * 存储api信息
             */
            XResp xResp = DesUtils.getXResp(context, fileName);
            ApiConfig.getInstance().saveApiConfig(xResp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
