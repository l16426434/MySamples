package com.example.liujishun.mysamples.utils;

import android.net.Uri;
import android.os.Environment;

import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.TakePhotoOptions;

import java.io.File;

/**
 * Created by liujishun on 2017/2/22.
 */

public class TakeUtils {
    private static volatile TakeUtils mTakeUtils;
    private boolean isCompress = false;
    private int limit = 1;//多图时，最多几张图
    private Uri imageUri;

    private TakeUtils() {
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        imageUri = Uri.fromFile(file);
    }

    public static TakeUtils getInstance() {

        if (null == mTakeUtils) {
            synchronized (TakeUtils.class) {

                if (null == mTakeUtils) {
                    mTakeUtils = new TakeUtils();
                }
            }
        }
        return mTakeUtils;
    }

    /**
     * 选择照片
     * 多图，带剪切图片
     *
     * @param takePhoto
     */
    public void onPickMultipleWithCrop(TakePhoto takePhoto) {
        configCompress(takePhoto);
        configTakePhotoOption(takePhoto);
        takePhoto.onPickMultipleWithCrop(limit, getCropOptions());
    }

    /**
     * 选择照片
     * 多图，不带剪切
     *
     * @param takePhoto
     */
    public void onPickMultiple(TakePhoto takePhoto) {
        configCompress(takePhoto);
        configTakePhotoOption(takePhoto);
        takePhoto.onPickMultiple(limit);
    }

    /**
     * 选择图片
     * 带剪切图片，从相册中
     * 适用于1张图片
     */
    public void onPickFromGalleryWithCrop(TakePhoto takePhoto) {
        configCompress(takePhoto);
        configTakePhotoOption(takePhoto);
        takePhoto.onPickFromGalleryWithCrop(imageUri, getCropOptions());
    }

    /**
     * 选择图片
     * 不剪切图片，从相册中
     * 适用于1张图片
     *
     * @param takePhoto
     */
    public void onPickFromGallery(TakePhoto takePhoto) {
        configCompress(takePhoto);
        configTakePhotoOption(takePhoto);
        takePhoto.onPickFromGallery();
    }

    /**
     * 拍照得到的照片 剪切
     *
     * @param takePhoto
     */
    public void onPickFromCaptureWithCrop(TakePhoto takePhoto) {
        configCompress(takePhoto);
        configTakePhotoOption(takePhoto);
        takePhoto.onPickFromCaptureWithCrop(imageUri, getCropOptions());
    }

    /**
     * 拍照得到的照片 不剪切
     *
     * @param takePhoto
     */
    public void onPickFromCapture(TakePhoto takePhoto) {
        configCompress(takePhoto);
        configTakePhotoOption(takePhoto);
        takePhoto.onPickFromCapture(imageUri);
    }

    private void configTakePhotoOption(TakePhoto takePhoto) {
        TakePhotoOptions.Builder builder = new TakePhotoOptions.Builder();
        //使用TakePhoto自带相册
        //提示：选择多张图片时会自动切换到TakePhoto自带相册
        if (limit > 1) {
            builder.setWithOwnGallery(true);
        } else
            builder.setWithOwnGallery(false);

        //纠正拍照的照片旋转角度
        builder.setCorrectImage(true);
        //照片配置选项
        takePhoto.setTakePhotoOptions(builder.create());

    }

    private void configCompress(TakePhoto takePhoto) {
        if (isCompress) {
            //是否压缩图片
            takePhoto.onEnableCompress(null, false);
            return;
        }
        int maxSize = 100 * 1024; //B
        int width = 800;//px
        int height = 800;
        boolean showProgressBar = true;
        boolean enableRawFile = true;
        CompressConfig config;
        //使用系统自带 压缩方法       
        config = new CompressConfig.Builder()
                .setMaxSize(maxSize)
                .setMaxPixel(width >= height ? width : height)
                .enableReserveRaw(enableRawFile)
                .create();

        //使用Luban 第三方压缩
        //        LubanOptions option = new LubanOptions.Builder()
        //                .setMaxHeight(height)
        //                .setMaxWidth(width)
        //                .setMaxSize(maxSize)
        //                .create();
        //        config = CompressConfig.ofLuban(option);
        //        config.enableReserveRaw(enableRawFile);

        takePhoto.onEnableCompress(config, showProgressBar);


    }

    private CropOptions getCropOptions() {

        int height = 800;
        int width = 800;
        boolean withWonCrop = false;

        CropOptions.Builder builder = new CropOptions.Builder();
        builder.setAspectX(width).setAspectY(height);
        //builder.setOutputX(width).setOutputY(height);
        builder.setWithOwnCrop(withWonCrop);
        return builder.create();
    }

    public boolean isCompress() {
        return isCompress;
    }

    public void setCompress(boolean compress) {
        isCompress = compress;
    }
}
