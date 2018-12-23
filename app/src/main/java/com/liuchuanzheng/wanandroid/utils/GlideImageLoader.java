package com.liuchuanzheng.wanandroid.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * 主要用于 结合 banner 加载图片使用
 *
 * @packageName: cn.white.ymc.wanandroidmaster.util
 * @fileName: GlideImageLoader
 * @date: 2018/8/9  10:15
 * @author: ymc
 * @QQ:745612618
 */

public class GlideImageLoader extends ImageLoader {
    /**
     * 注意：
     * 1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
     * 2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
     * 传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
     * 切记不要胡乱强转！
     */
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context.getApplicationContext()).load(path).into(imageView);
    }

    //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
    @Override
    public ImageView createImageView(Context context) {
        return super.createImageView(context);
    }
}
