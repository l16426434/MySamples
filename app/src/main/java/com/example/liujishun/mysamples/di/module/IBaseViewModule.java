package com.example.liujishun.mysamples.di.module;

import com.example.liujishun.mysamples.base.mvp.IBaseView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by liujishun on 2017/2/14.
 */
@Module
public class IBaseViewModule {
    private IBaseView mBaseView;

    public IBaseViewModule(IBaseView iBaseView){
        this.mBaseView = iBaseView;
    }

    @Provides
    public IBaseView provideIBaseView() {
        return mBaseView;
    }

    @Provides
    public String provideString(){
        return "haha";
    }
}
