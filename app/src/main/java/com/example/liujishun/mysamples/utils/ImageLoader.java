package com.example.liujishun.mysamples.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by liujishun on 2017/2/28.
 */
@SuppressLint("NewApi")
public class ImageLoader {
    

    public static void load(Activity activity, String url, ImageView iv) {    //使用Glide加载圆形ImageView(如头像)时，不要使用占位图

        if (!activity.isDestroyed()) {
            Glide.with(activity)
                    .load(url)//图片的
                    .crossFade()//渐入渐出的动画效果，默认是300ms.
                    //.dontAnimate()// 不用动画
                    .thumbnail(0.1f)//显示缩略图
//                    .centerCrop()//剪贴图片，适应图片大小
                    .fitCenter()//完全显示图片
//                    .placeholder(R.mipmap.ic_launcher)//占位图
//                    .error(R.mipmap.ic_launcher)//加载失败显示的图片
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)//缓存机制
                    .into(iv);
        }
        
    }
    
}
