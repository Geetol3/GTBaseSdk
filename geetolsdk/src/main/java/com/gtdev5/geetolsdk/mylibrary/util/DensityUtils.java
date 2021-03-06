package com.gtdev5.geetolsdk.mylibrary.util;

import android.content.Context;

/**
 * Created by zl
 * 2020/05/19
 * dp和像素之间的转换
 */
public class DensityUtils {
    public static int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int Px2Dp(Context context, float px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }
}
