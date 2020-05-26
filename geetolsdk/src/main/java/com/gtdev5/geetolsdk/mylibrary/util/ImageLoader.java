package com.gtdev5.geetolsdk.mylibrary.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.gtdev5.geetolsdk.mylibrary.contants.Contants;

/**
 * Created by zl
 * 2020/05/19
 * 图片处理工具类
 * (缓存，下载，圆形，圆角，模糊，缩略图)
 */
public class ImageLoader {

    private static ImageLoader mInstance;

    private ImageLoader() {}

    public static ImageLoader getInstance() {
        if (mInstance == null) {
            synchronized (ImageLoader.class) {
                if (mInstance == null) {
                    mInstance = new ImageLoader();
                }
            }
        }
        return mInstance;
    }

    /**
     * 基本的图片加载
     */
    public <T> void loadImage(Context context, ImageView imageView, T imaUrl) {
        RequestOptions options = new RequestOptions()
                .priority(Priority.NORMAL)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).load(imaUrl).apply(options).into(imageView);
    }

    /**
     * 常规加载图片
     *
     * @param context   上下文
     * @param imageView 图片容器
     * @param imgUrl    图片路径
     * @param loadImg   加载时显示的图片(可空)
     * @param errorImg  失败时显示的图片(可空)
     * @param isFade    是否需要动画
     */
    public <T> void loadImage(Context context, ImageView imageView, T imgUrl, int loadImg, int errorImg, boolean isFade) {
        RequestOptions options = new RequestOptions()
                .placeholder(loadImg)
                .error(errorImg)
                .priority(Priority.NORMAL)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context)
                .load(imgUrl)
                .apply(options)
                .into(imageView);
    }

    /**
     * 加载缩略图
     *
     * @param context   上下文
     * @param imageView 图片容器
     * @param imgUrl    图片路径
     * @param loadImg   加载时显示的图片(可空)
     * @param errorImg  失败时显示的图片(可空)
     */
    public <T> void loadThumbnailImage(Context context, ImageView imageView, T imgUrl, int loadImg, int errorImg) {
        RequestOptions options = new RequestOptions()
                .placeholder(loadImg)
                .error(errorImg)
                .priority(Priority.NORMAL)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context)
                .load(imgUrl)
                .apply(options)
                .thumbnail(Contants.THUMB_SIZE)
                .into(imageView);
    }

    /**
     * 加载图片并且设置大小
     *
     * @param context    上下我呢
     * @param imageView  图片容器
     * @param imgUrl     图片路径
     * @param withSize   宽度
     * @param heightSize 高度
     * @param loadImg    加载时显示的图片(可空)
     * @param errorImg   失败时显示的图片(可空)
     */
    public <T> void loadOverrideImage(Context context, ImageView imageView, T imgUrl, int withSize, int heightSize, int loadImg, int errorImg) {
        RequestOptions options = new RequestOptions()
                .placeholder(loadImg)
                .error(errorImg)
                .priority(Priority.NORMAL)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .override(withSize, heightSize);
        Glide.with(context)
                .load(imgUrl)
                .apply(options)
                .into(imageView);
    }

    /**
     * 加载图片并对其进行模糊处理
     *
     * @param context   上下文
     * @param imageView 图片容器
     * @param imgUrl    图片路径
     * @param loadImg   加载时显示的图片(可空)
     * @param errorImg  失败时显示的图片(可空)
     */
    public <T> void loadBlurImage(Context context, ImageView imageView, T imgUrl, int loadImg, int errorImg) {
        RequestOptions options = new RequestOptions()
                .placeholder(loadImg)
                .error(errorImg)
                .priority(Priority.NORMAL)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context)
                .load(imgUrl)
                .apply(options)
                .into(imageView);
    }

    /**
     * 加载圆形图
     *
     * @param context   上下文
     * @param imageView 图片容器
     * @param imgUrl    图片路径
     * @param loadImg   加载时显示的图片(可空)
     * @param errorImg  失败时显示的图片(可空)
     */
    public <T> void loadCircleImage(Context context, ImageView imageView, T imgUrl, int loadImg, int errorImg) {
        RequestOptions options = new RequestOptions()
                .placeholder(loadImg)
                .error(errorImg)
                .priority(Priority.NORMAL)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context)
                .load(imgUrl)
                .apply(options)
                .into(imageView);
    }

    /**
     * 加载模糊圆形图
     *
     * @param context   上下文
     * @param imageView 图片容器
     * @param imgUrl    图片路径
     * @param loadImg   加载时显示的图片(可空)
     * @param errorImg  失败时显示的图片(可空)
     */
    public <T> void loadBlurCircleImage(Context context, ImageView imageView, T imgUrl, int loadImg, int errorImg) {
        RequestOptions options = new RequestOptions()
                .placeholder(loadImg)
                .error(errorImg)
                .priority(Priority.NORMAL)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context)
                .load(imgUrl)
                .apply(options)
                .into(imageView);
    }

    /**
     * 加载圆角图片
     *
     * @param context   上下文
     * @param imageView 图片容器
     * @param imgUrl    图片路径
     * @param loadImg   加载时显示的图片(可空)
     * @param errorImg  失败时显示的图片(可空)
     */
    public <T> void loadCornerImage(Context context, ImageView imageView, T imgUrl, int loadImg, int errorImg, int Padius) {
        RequestOptions options = new RequestOptions()
                .placeholder(loadImg)
                .error(errorImg)
                .priority(Priority.NORMAL)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context)
                .load(imgUrl)
                .apply(options)
                .into(imageView);
    }

    /**
     * 加载模糊圆角图
     *
     * @param context   上下文
     * @param imageView 图片容器
     * @param imgUrl    图片路径
     * @param loadImg   加载时显示的图片(可空)
     * @param errorImg  失败时显示的图片(可空)
     */
    public <T> void loadBlurCornerImage(Context context, ImageView imageView, T imgUrl, int loadImg, int errorImg) {
        RequestOptions options = new RequestOptions()
                .placeholder(loadImg)
                .error(errorImg)
                .priority(Priority.NORMAL)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context)
                .load(imgUrl)
                .apply(options)
                .into(imageView);
    }

    /**
     * 加载Gif
     *
     * @param context   上下文
     * @param imageView 图片容器
     * @param imgUrl    图片路径
     * @param loadImg   加载时显示的图片(可空)
     * @param errorImg  失败时显示的图片(可空)
     */
    public <T> void loadGifImage(Context context, ImageView imageView, T imgUrl, int loadImg, int errorImg) {
        RequestOptions options = new RequestOptions()
                .placeholder(loadImg)
                .error(errorImg)
                .priority(Priority.NORMAL)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context)
                .asGif()
                .load(imgUrl)
                .apply(options)
                .into(imageView);
    }

    /**
     * 加载Gif缩略图
     *
     * @param context   上下文
     * @param imageView 图片容器
     * @param imgUrl    图片路径
     * @param loadImg   加载时显示的图片(可空)
     * @param errorImg  失败时显示的图片(可空)
     */
    public <T> void loadGifThumbnailImage(Context context, ImageView imageView, T imgUrl, int loadImg, int errorImg) {
        RequestOptions options = new RequestOptions()
                .placeholder(loadImg)
                .error(errorImg)
                .priority(Priority.NORMAL)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context)
                .asGif()
                .load(imgUrl)
                .apply(options)
                .thumbnail(Contants.THUMB_SIZE)
                .into(imageView);
    }

    /**
     * 加载Gif静态图像或者视频的第一帧图像(本地视频,网络视频无法加载)
     *
     * @param context   上下文
     * @param imageView 图片容器
     * @param imgUrl    图片路径
     * @param loadImg   加载时显示的图片(可空)
     * @param errorImg  失败时显示的图片(可空)
     */
    public void loadGifBitmapImage(Context context, ImageView imageView, String imgUrl, int loadImg, int errorImg) {
        RequestOptions options = new RequestOptions()
                .placeholder(loadImg)
                .error(errorImg)
                .priority(Priority.NORMAL)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context)
                .asBitmap()
                .load(imgUrl)
                .apply(options)
                .into(imageView);
    }

    /**
     * 同步加载图片
     *
     * @param context  上下文
     * @param imgUrl   图片路径
     * @param loadImg  加载时显示的图片(可空)
     * @param errorImg 失败时显示的图片(可空)
     * @param target   回调接口
     */
    public void loadBitmapSync(Context context, String imgUrl, int loadImg, int errorImg, SimpleTarget<Bitmap> target) {
        RequestOptions options = new RequestOptions()
                .placeholder(loadImg)
                .error(errorImg)
                .priority(Priority.NORMAL)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context)
                .asBitmap()
                .load(imgUrl)
                .apply(options)
                .into(target);
    }

    /**
     * 暂停请求    正在滚动的时候
     *
     * @param context 上下文
     */
    public void pauseRequest(Context context) {
        Glide.with(context).pauseRequests();
    }

    /**
     * 恢复请求    停止滚动的时候
     *
     * @param context 上下文
     */
    public void resumeRequest(Context context) {
        Glide.with(context).resumeRequests();
    }

    /**
     * 清除磁盘缓存
     *
     * @param context 上下文
     */
    public void clearDiskCache(final Context context) {
        new Thread(() -> Glide.get(context).clearDiskCache());//需要在子线程里面执行
    }

    /**
     * 清除内存缓存
     *
     * @param context 上下文
     */
    public void clearMemory(final Context context) {
        Glide.get(context).clearMemory();//必须在主线程里面操作
    }

    public void clearCache(Context context) {
        clearMemory(context);
        clearDiskCache(context);
    }
}
