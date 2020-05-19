package com.gtdev5.geetolsdk.mylibrary.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.gtdev5.geetolsdk.mylibrary.initialization.GTBaseSdk;

/**
 * Created by zl
 * 2020/05/19
 * Toast工具类
 */
public class ToastUtils {
    private static Toast mToast = null;

    private static Context mContext;

    public static void init(Context context) {
        if (mContext == null) {
            mContext = context;
            mToast = new Toast(mContext);
        } else {
            Log.e(GTBaseSdk.TAG, "未初始化lib");
        }
    }

    /**
     * short Toast
     */
    public static void showShortToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
        } else {
            mToast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    /**
     * long Toast
     */
    public static void showLongToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, msg, Toast.LENGTH_LONG);
        } else {
            mToast = Toast.makeText(mContext, msg, Toast.LENGTH_LONG);
        }
        mToast.show();
    }
}
