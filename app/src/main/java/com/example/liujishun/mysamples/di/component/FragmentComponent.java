package com.example.liujishun.mysamples.di.component;

import com.example.liujishun.mysamples.di.module.IBaseViewModule;

import dagger.Component;

/**
 * Created by liujishun on 2017/2/23.
 */
@Component(dependencies = AppComponent.class,modules = IBaseViewModule.class)
public interface FragmentComponent {
}
