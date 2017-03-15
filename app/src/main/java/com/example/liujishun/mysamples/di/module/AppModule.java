package com.example.liujishun.mysamples.di.module;

import com.example.liujishun.mysamples.App;
import com.example.liujishun.mysamples.base.retrofit.ApiStores;
import com.example.liujishun.mysamples.base.retrofit.AppClient;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBaseConfig;

import dagger.Module;
import dagger.Provides;

import static com.example.liujishun.mysamples.App.DB_NAME;
import static com.example.liujishun.mysamples.App.dbVersion;

/**
 * Created by liujishun on 2017/2/15.
 */
@Module
public class AppModule {
    private App mApp;
    public AppModule(App app){
        this.mApp = app;
    }

    @Provides
    public App provideApp(){
        return mApp;
    }

    @Provides
    public LiteOrm provideLiteOrm(){
        DataBaseConfig config = new DataBaseConfig(mApp, DB_NAME);
        config.debugged = true; // open the log
        config.dbVersion = dbVersion; // set database version
        config.onUpdateListener = null; // set database update listener
        return LiteOrm.newCascadeInstance(config);
    }
    
    @Provides
    public ApiStores provideApiStores(){
        return AppClient.retrofit().create(ApiStores.class);
    }
}
