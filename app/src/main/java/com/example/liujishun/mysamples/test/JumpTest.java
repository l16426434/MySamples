package com.example.liujishun.mysamples.test;

import android.os.Bundle;

import com.example.liujishun.mysamples.MainActivity;
import com.example.liujishun.mysamples.base.BaseActivity;
import com.mbase.monch.activity.ActivityJump;

/**
 * Created by liujishun on 16/7/1.
 */

public class JumpTest extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityJump.NormalJump(this, MainActivity.class);

        ActivityJump.Back(this);

        ActivityJump.BundleJump(this,MainActivity.class,new Bundle());
    }

}
