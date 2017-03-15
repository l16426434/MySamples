package com.example.liujishun.mysamples.base.mvp;

import android.os.Bundle;

import com.example.liujishun.mysamples.App;
import com.example.liujishun.mysamples.base.BaseActivity;
import com.example.liujishun.mysamples.di.component.ActivityComponent;
import com.example.liujishun.mysamples.di.component.DaggerActivityComponent;
import com.example.liujishun.mysamples.di.module.IBaseViewModule;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by liujishun on 16/6/29.
 */

public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity implements IBaseView {
    @Inject
    protected P mvpPresenter;
    private Unbinder mUnBinder;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        mvpPresenter = createPresenter();
        setContentView(getLayout());
        mUnBinder = ButterKnife.bind(this);
        initInject();
        mvpPresenter.attachView(this);
        initEventAndData();
    }
    //    //关联p层 得到p的对象
    //    protected abstract P createPresenter();

    public ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .iBaseViewModule(new IBaseViewModule(this))
                .build();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除关联
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
        mUnBinder.unbind();
    }

    protected abstract void initInject();
    protected abstract int getLayout();
    protected abstract void initEventAndData();
}
