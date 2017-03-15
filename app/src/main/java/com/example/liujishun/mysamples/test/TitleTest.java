package com.example.liujishun.mysamples.test;

import android.os.Bundle;
import android.view.View;

import com.example.liujishun.mysamples.R;
import com.example.liujishun.mysamples.base.BaseActivity;
import com.mbase.monch.view.TitleBar;

/**
 * Created by liujishun on 16/8/22.
 */

public class TitleTest extends BaseActivity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_title);

        TitleBar titleBar = (TitleBar)findViewById(R.id.title);
        titleBar.setTitle("标题");
        titleBar.addAction(new TitleBar.TextAction("函数") {
            @Override
            public void performAction(View view) {
                
            }
        });
        titleBar.addAction(new TitleBar.ImageAction(R.drawable.leak_canary_icon) {
            @Override
            public void performAction(View view) {
                
            }
        });
    }
}
