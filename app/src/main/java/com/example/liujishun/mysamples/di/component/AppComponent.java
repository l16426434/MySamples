package com.example.liujishun.mysamples.di.component;

import com.example.liujishun.mysamples.App;
import com.example.liujishun.mysamples.base.retrofit.ApiStores;
import com.example.liujishun.mysamples.di.module.AppModule;
import com.litesuits.orm.LiteOrm;

import dagger.Component;

/**
 * Created by liujishun on 2017/2/15.
 */
@Component(modules = AppModule.class)
public interface AppComponent {

    App getApp();

    LiteOrm getLiteOrm();

    ApiStores getApiStores();
    
}
