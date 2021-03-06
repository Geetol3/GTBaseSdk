package com.gtdev5.geetolsdk.mylibrary.util;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

import com.gtdev5.geetolsdk.mylibrary.beans.XResp;
import com.gtdev5.geetolsdk.mylibrary.contants.Contants;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * Created by zl
 * 2020/05/15
 * Des加解密工具
 */
public class DesUtils {
    private static final String ALGORITHM_DES = "DES/CBC/PKCS5Padding";
    private final static String ENCODING = "utf-8";

    /**
     * 获取密钥
     */
    private static SecretKey getSecretKey(String secretKey) throws Exception {
        byte[] desKey = secretKey.getBytes();
        DESKeySpec keySpec = new DESKeySpec(desKey);// 设置密钥参数
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");// 获得密钥工厂
        return keyFactory.generateSecret(keySpec);
    }

    /**
     * 设置偏移量
     */
    private static AlgorithmParameterSpec getIv(String iv) {
        byte[] desIv = iv.getBytes();
        return new IvParameterSpec(desIv);
    }

    /**
     * 加密
     */
    public static String encode(String data, String key, String iv) throws Exception {
        Cipher enCipher = Cipher.getInstance(ALGORITHM_DES);// 得到加密对象Cipher
        enCipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key), getIv(iv));// 设置工作模式为加密模式，给出密钥和向量
        byte[] pasByte = enCipher.doFinal(data.getBytes(ENCODING));
        if (Build.VERSION.SDK_INT >= 26) {
            return Base64.getMimeEncoder().encodeToString(pasByte);
        } else {
            return android.util.Base64.encodeToString(pasByte, android.util.Base64.DEFAULT);
        }
    }

    /**
     * 解密
     */
    public static String decode(String data, String key, String iv) throws Exception {
        Cipher deCipher = Cipher.getInstance(ALGORITHM_DES);
        deCipher.init(Cipher.DECRYPT_MODE, getSecretKey(key), getIv(iv));
        byte[] pasByte;
        if (Build.VERSION.SDK_INT >= 26) {
            pasByte = deCipher.doFinal(Base64.getMimeDecoder().decode(data));
        } else {
            pasByte = deCipher.doFinal(android.util.Base64.decode(data.getBytes(ENCODING), android.util.Base64.DEFAULT));
        }
        return new String(pasByte, ENCODING);
    }

    /**
     * 获取assets文件内容
     *
     * @param fileName 文件名称
     */
    public static String getFromAssets(Context context, String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String result = "";
            while ((line = bufReader.readLine()) != null)
                result += line;
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取解码实体类
     *
     * @param fileName 文件名
     */
    public static XResp getXResp(Context context, String fileName) throws Exception {
        String hostConfig = getFromAssets(context, fileName);
        String result = decode(hostConfig, fileName.substring(0, 8), fileName.substring(0, 8));
        return GsonUtils.getFromClass(result, XResp.class);
    }

    /**
     * 获取加密传参值
     */
    public static String getRequest(String data) throws Exception {
        String fileName = SpUtils.getInstance().getString(Contants.FILE_NAME);
        if (!TextUtils.isEmpty(fileName)) {
            String result = encode(data, fileName.substring(0, 8), fileName.substring(0, 8));
            return URLEncoder.encode(result, "utf-8");
        }
        return "";
    }

    /**
     * 获取解码回调
     */
    public static String getResult(String data) throws Exception {
        String fileName = SpUtils.getInstance().getString(Contants.FILE_NAME);
        if (!TextUtils.isEmpty(fileName)) {
            return decode(data, fileName.substring(0, 8), fileName.substring(0, 8));
        }
        return "";
    }
}
