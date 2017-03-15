package com.example.liujishun.mysamples.test;

import android.os.Bundle;

import com.example.liujishun.mysamples.base.BaseActivity;
import com.mbase.monch.activity.ActivityManager;

/**
 * Created by liujishun on 16/7/29.
 */

public class ActivityTest extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private void w(){
        //退出app
        ActivityManager.getScreenManager().appExit();
        //得到当前显示的Activity
        ActivityManager.getScreenManager().currentActivity();
        //弹出栈顶对象
        ActivityManager.getScreenManager().popActivity();
        //加入栈
        ActivityManager.getScreenManager().pushActivity(this);


    }

}
