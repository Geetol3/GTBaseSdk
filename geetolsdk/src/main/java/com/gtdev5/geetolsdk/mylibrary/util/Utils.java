package com.gtdev5.geetolsdk.mylibrary.util;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;

import com.google.gson.Gson;
import com.gtdev5.geetolsdk.mylibrary.beans.AliOssBean;
import com.gtdev5.geetolsdk.mylibrary.contants.Contants;
import com.gtdev5.geetolsdk.mylibrary.util.des.BASE64Decoder;
import com.gtdev5.geetolsdk.mylibrary.util.des.DesUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by zl
 * 2020/05/19
 * 工具类
 */
public class Utils {
    private static MessageDigest digest;

    /**
     * 生成随机数字和字母
     *
     * @param length 长度
     */
    public static String getStringRandom(int length) {
        String val = "";
        Random random = new Random();
        //参数length，表示生成几位随机数
        for (int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (random.nextInt(26) + temp);
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    /**
     * 得到年月日
     *
     * @param type 格式
     */
    public static String getDate(String type) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(type);
        Date date = new Date();
        return simpleDateFormat.format(date);
    }

    /**
     * 判断字符串是否为空
     */
    public static boolean isEmpty(String str) {
        if (str == null || str.equals("") || str.equals("null")) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串不为空
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 数组转字符串
     */
    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs;
    }

    /**
     * 判断当前网络是否可用
     */
    public static boolean isNetworkAvailable(Context context) {
        boolean result = false;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager != null) {
                NetworkInfo[] infos = connectivityManager.getAllNetworkInfo();
                if (infos != null) {
                    for (int i = 0; i < infos.length; i++) {
                        if (infos[i].getState() == NetworkInfo.State.CONNECTED) {
                            result = true;
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 判断当前网络状态是否是wifi
     */
    public static boolean isWifi(Context context) {
        boolean result = false;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager != null) {
                NetworkInfo[] infos = connectivityManager.getAllNetworkInfo();
                if (infos != null) {
                    for (int i = 0; i < infos.length; i++) {
                        if (infos[i].getType() == ConnectivityManager.TYPE_WIFI) {
                            result = true;
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 判断是否是合法手机号
     */
    public static boolean isPhone(String phone) {
        if (phone == null) {
            return false;
        }
        String telRegex = "[1][0123456789]\\d{9}";
        return phone.matches(telRegex);
    }

    /**
     * 判断是否已经获取 有权查看使用情况的应用程序 权限
     */
    public static boolean isSatAccessPermissionSet(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                PackageManager packageManager = context.getPackageManager();
                ApplicationInfo info = packageManager.getApplicationInfo(context.getPackageName(), 0);
                AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
                appOpsManager.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, info.uid, info.packageName);
                return appOpsManager.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, info.uid, info.packageName) == AppOpsManager.MODE_ALLOWED;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return true;
        }
    }

    /**
     * 查看是存在查看使用情况的应用程序界面
     */
    public static boolean isNoOption(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            PackageManager packageManager = context.getPackageManager();
            Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
            List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
            return list.size() > 0;
        }
        return false;
    }

    /**
     * 得到手机当前版本号
     */
    public static String getVersion(Context context) {
        String localVersion = "";
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            localVersion = packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    /**
     * 比较本地版本和服务器版本大小
     *
     * @param serverVersion 服务器版本号
     * @param localVersion  本地版本号
     */
    public static boolean VersionCompare(String serverVersion, String localVersion) {
        String[] server = serverVersion.split("[.]");
        String[] local = localVersion.split("[.]");
        for (int i = 0; i < server.length; i++) {
            if (i < local.length) {
                int a = Integer.parseInt(server[i]);
                int b = Integer.parseInt(local[i]);
                if (a > b) {
                    return true;
                } else if (a == b) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * 设置用户信息
     *
     * @param userId  用户id
     * @param userKey 用户id
     * @param img     头像数据
     */
    public static void setLoginInfo(String userId, String userKey, String img) {
        SpUtils.getInstance().putString(Contants.USER_ID, userId);
        SpUtils.getInstance().putString(Contants.USER_KEY, userKey);
        SpUtils.getInstance().putString(Contants.USER_HEAD, img);
    }

    /**
     * 获取用户id
     */
    public static String getUserId() {
        String userId = SpUtils.getInstance().getString(Contants.USER_ID);
        if (TextUtils.isEmpty(userId)) {
            userId = "";
        }
        return userId;
    }

    /**
     * 获取用户key
     */
    public static String getUserKey() {
        String uKey = SpUtils.getInstance().getString(Contants.USER_KEY);
        if (TextUtils.isEmpty(uKey)) {
            uKey = "";
        }
        return uKey;
    }

    /**
     * 获取用户头像
     */
    public static String getUserHead() {
        return SpUtils.getInstance().getString(Contants.USER_HEAD);
    }

    /**
     * 通过Base32将Bitmap转换成Base64字符串
     */
    public static String Bitmap2StrByBase64(Bitmap bitmap) {
        String reslut = "";
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                /**
                 * 压缩只对保存有效果bitmap还是原来的大小
                 */
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                int options = 100;
                while (baos.toByteArray().length / 1024 > 500) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
                    baos.reset();//重置baos即清空baos
                    //第一个参数 ：图片格式 ，第二个参数： 图片质量，100为最高，0为最差  ，第三个参数：保存压缩后的数据的流
                    bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
                    if (options > 5) {
                        options -= 5;//每次都减少10
                    } else {
                        break;
                    }
                }
                // 转换为字节数组
                byte[] byteArray = baos.toByteArray();
                reslut = Base64.encodeToString(byteArray, Base64.DEFAULT);
                baos.flush();
                baos.close();
                return reslut;
                // 转换为字符串
            } else {
                return "";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return reslut;
    }

    /**
     * base64字符串转bitmap
     */
    public static Bitmap Base64ToBitmap(String string) {
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray = Base64.decode(string, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 储存阿里云oss参数
     */
    public static void setAliOssParam(String string) {
        SpUtils.getInstance().putString(Contants.ALI_OSS_PARAM, string);
    }

    /**
     * 获取解密后的参数
     */
    private static String getDecParam() {
        if (!TextUtils.isEmpty(SpUtils.getInstance().getString(Contants.ALI_OSS_PARAM))) {
            String param = SpUtils.getInstance().getString(Contants.ALI_OSS_PARAM);
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                byte[] bytes = decoder.decodeBuffer(param);
                byte[] bytes1 = DesUtil.decode(bytes);
                if (bytes1 != null) {
                    return new String(bytes1);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取AliOss参数
     */
    public static AliOssBean getAliOssParam() {
        AliOssBean aliOssBean = null;
        if (!TextUtils.isEmpty(getDecParam())) {
            Gson gson = new Gson();
            aliOssBean = gson.fromJson(getDecParam(), AliOssBean.class);
        }
        return aliOssBean;
    }

    /**
     * 检测是否安装微信
     */
    public static boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }
        return false;
    }
}
