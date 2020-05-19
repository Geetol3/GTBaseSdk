package com.gtdev5.geetolsdk.mylibrary.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by zl
 * 2020/05/19
 * 自带点击效果的imageview
 */
public class PressedImageView extends android.support.v7.widget.AppCompatImageView {
    private float scaleSize;//按压颜色

    public PressedImageView(Context context) {
        super(context);
        this.scaleSize = 0.97f;
    }

    public PressedImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.scaleSize = 0.97f;
    }

    public PressedImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.scaleSize = 0.97f;
    }

    @Override
    public void setPressed(boolean pressed) {
        super.setPressed(pressed);
        if (isPressed()) {
            setScaleX(this.scaleSize);
            setScaleY(this.scaleSize);
        } else {
            setScaleX(1.0f);
            setScaleY(1.0f);
        }
    }

    public void setScaleSize(float scaleSize) {
        this.scaleSize = scaleSize;
    }
}
