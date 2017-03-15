package com.example.liujishun.mysamples.di.component;

import com.example.liujishun.mysamples.di.module.IBaseViewModule;
import com.example.liujishun.mysamples.mvp.MvpMainActivity;
import com.example.liujishun.mysamples.test.SqliteTest;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 */
@Component(dependencies = AppComponent.class,modules = IBaseViewModule.class)
public interface ActivityComponent {

    public void inject(MvpMainActivity mvpMainActivity);

    public void inject(SqliteTest sqliteTest);

}
