package com.gtdev5.geetolsdk.mylibrary.initialization;

import android.content.Context;

import com.gtdev5.geetolsdk.mylibrary.beans.XResp;
import com.gtdev5.geetolsdk.mylibrary.contants.ApiConfig;
import com.gtdev5.geetolsdk.mylibrary.contants.Contants;
import com.gtdev5.geetolsdk.mylibrary.util.CPResourceUtils;
import com.gtdev5.geetolsdk.mylibrary.util.DesUtils;
import com.gtdev5.geetolsdk.mylibrary.util.LogUtils;
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

    /**
     * 简单初始化(默认打印日志，但不保存错误信息)
     * @param fileName 配置文件名
     */
    public static void init(Context context, String fileName) {
        init(context, fileName, true, false, "", "");
    }

    /**
     * 简单初始化(默认打印日志，但不保存错误信息)
     * @param fileName 配置文件名
     * @param showLog 是否显示日志
     * @param saveLog 是否保存日志
     * @param logFile 错误日志保存路径
     */
    public static void init(Context context, String fileName, boolean showLog, boolean saveLog, String logFile, String logName) {
        init(context);
        SpUtils.getInstance().putString(Contants.FILE_NAME, fileName);
        LogUtils.getInstance() // 单例获取LogCook实例
                .setLogPath(logFile) //设置日志保存路径
                .setLogName(logName) //设置日志文件名
                .isOpen(showLog)  //是否开启输出日志
                .isSave(saveLog)  //是否保存日志
                .initialize(); //完成初始化Crash监听
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
