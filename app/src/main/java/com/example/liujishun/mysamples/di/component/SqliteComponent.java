package com.example.liujishun.mysamples.di.component;

import com.example.liujishun.mysamples.test.SqliteTest;

import dagger.Component;

/**
 * Created by liujishun on 2017/2/15.
 */
@Component(dependencies = AppComponent.class)
public interface SqliteComponent {
    public void inject(SqliteTest sqliteTest);
}
