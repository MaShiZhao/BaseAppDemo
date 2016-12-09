package com.resources.utils;

import android.content.Context;
import android.widget.ImageView;

import com.app.utils.ImageUtils;
import com.example.mashizhao.resourcesmodule.R;

/**
 * Created by MaShiZhao on 2016/12/8.
 */
public class GlideUtils {

    /**
     * 注意 圆角时不能设置scaleType centerCrop   center  fitxy   可以设置centerInside
     *
     */

    private static final int ROUND_RADIUS_SMALL = 10;
    private static final int ROUND_RADIUS_DEFAULT = 30;
    private static final int ROUND_RADIUS_LARGE = 60;

    /**
     * 加载课程图片
     *
     * @param context
     * @param url
     * @param image
     */
    public static void loadCourse(Context context, String url, ImageView image) {
        ImageUtils.loadImage(context, url, R.drawable.class_default_1, image);
    }

    /**
     * 加载课程圆角图片
     *
     * @param context
     * @param url
     * @param image
     */
    public static void loadRoundCourse(Context context, String url, ImageView image) {
        ImageUtils.loadRoundImage(context, url, R.drawable.class_default_1, ROUND_RADIUS_DEFAULT, image);
    }

    /**
     * 加载课程圆角图片
     *
     * @param context
     * @param url
     * @param image
     */
    public static void loadCirCleImage(Context context, String url, ImageView image) {
        ImageUtils.loadCirCleImage(context, url, R.drawable.class_default_1, image);
    }


}