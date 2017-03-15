package com.example.liujishun.mysamples.test;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.mbase.monch.utils.ThreadUtils;

import java.util.concurrent.ExecutorService;

/**
 * Created by liujishun on 16/7/27.
 */

public class SingleExecutorTest extends AppCompatActivity{
    private ExecutorService executor = ThreadUtils.createSingleExecutor();

    private boolean checkTaskExecutor() {
        return executor != null && !executor.isShutdown();
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        test();
    }

    public void test(){
        executor.submit(new Runnable() {
            @Override
            public void run() {
                //异步
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (executor != null)
            executor.shutdown();
    }
}
