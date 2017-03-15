package com.example.liujishun.mysamples.test;

import android.os.Bundle;

import com.example.liujishun.mysamples.base.BaseActivity;
import com.mbase.monch.activity.ActivityManager;
import com.orhanobut.logger.Logger;

/**
 * Created by liujishun on 16/3/28.
 */
public class LogTest extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //得到当前显示的Activity
        Logger.e("当前显示Activity:"+ActivityManager.getScreenManager().currentActivity().getLocalClassName());

    }
}
