package com.example.liujishun.mysamples;

import android.app.Application;
import android.util.Log;

import com.example.liujishun.mysamples.di.component.AppComponent;
import com.example.liujishun.mysamples.di.component.DaggerAppComponent;
import com.example.liujishun.mysamples.di.module.AppModule;
import com.facebook.stetho.Stetho;
import com.litesuits.orm.LiteOrm;
import com.mbase.monch.BaseConfig;
import com.mbase.monch.MBase;
import com.mbase.monch.utils.CrashHandlerUtil;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsListener;

import java.nio.charset.Charset;

/**
 * Created by liujishun on 16/6/30.
 */

public class App extends Application {
    public static App instance;
    // 数据库版本号
    public static final int dbVersion = 1;
    static LiteOrm liteOrm;
    public static String versionCode = "V1.0";
    public static String DB_NAME = "heruan.db";
    private static boolean IsDebug = true;
    public static String Version_Name;

    private static AppComponent appComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        try {
            //debug
            if(IsDebug){

                if (LeakCanary.isInAnalyzerProcess(this)) {
                    // This process is dedicated to LeakCanary for heap analysis.
                    // You should not init your app in this process.
                    return;
                }
                LeakCanary.install(this);
                Stetho.initializeWithDefaults(this);
            }
            
            instance = this;
            //初始化数据
            BaseConfig config = new BaseConfig.Builder(this)
                    .setDebug(IsDebug) // DEBUG模式
                    .setCharset(Charset.forName("UTF-8"))   // 默认编码方式
                    .setLoggerTag("HERUAN")
                    .builder();
            //初始化配置文件
            MBase.initialize(this, config);
            initX5();
            //崩溃处理
            CrashHandlerUtil crashHandlerUtil = CrashHandlerUtil.getInstance();
            crashHandlerUtil.init(this);
            crashHandlerUtil.setCrashTip("很抱歉，程序出现异常，即将退出！");

            getAppComponent();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initX5() {
        //初始化 腾讯x5 内核sdk
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                Log.e("apptbs", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub

            }
        };
        QbSdk.setTbsListener(new TbsListener() {
            @Override
            public void onDownloadFinish(int i) {
                Log.d("apptbs", "onDownloadFinish");
            }

            @Override
            public void onInstallFinish(int i) {
                Log.d("apptbs", "onInstallFinish");
            }

            @Override
            public void onDownloadProgress(int i) {
                Log.d("apptbs", "onDownloadProgress:" + i);
            }
        });

        QbSdk.initX5Environment(getApplicationContext(), cb);
    }

    /**
     * dagger2 appComponent 初始化
     * 有些对象的初始化需要context 对象，可以通过注入得到
     * @return AppComponent
     */
    public static AppComponent getAppComponent(){
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(instance))
                    .build();
        }
        return appComponent;
    }

}
