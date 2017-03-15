package com.example.liujishun.mysamples.test;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.example.liujishun.mysamples.R;
import com.example.liujishun.mysamples.base.BaseActivity;
import com.mbase.monch.BaseApp;
import com.mbase.monch.utils.BitmapUtils;
import com.orhanobut.logger.Logger;

import java.io.File;


/**
 * Created by liujishun on 16/7/28.
 */

public class SaveBitmapTest extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bitmap bitmap = BitmapUtils.drawableToBitmap(getResources().getDrawable(R.mipmap.ic_launcher));
        File file = new File(BaseApp.getCacheDir(), "testBitmap.jpg");
        boolean saveBitmapResult = BitmapUtils.saveBitmap(bitmap, file);
        if (saveBitmapResult)
            Logger.i(file.getAbsolutePath());
        else
            Logger.i("保存图片失败");
    }
}
