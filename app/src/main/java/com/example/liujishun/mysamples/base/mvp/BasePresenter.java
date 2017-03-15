package com.example.liujishun.mysamples.base.mvp;


import com.example.liujishun.mysamples.base.retrofit.ApiStores;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


/**
 * Presenter基类
 * 负责处理事物,逻辑
 */
public class BasePresenter<V extends IBaseView> implements Presenter<V> {
    protected V mvpView;
//    protected ApiStores apiStores = AppClient.retrofit().create(ApiStores.class);//子类通过apiStrore对象可以访问接口
    @Inject
    public ApiStores apiStores;
    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    public void attachView(V mvpView) {
        //初始化时 关联v
        this.mvpView = mvpView;
    }


    @Override
    public void detachView() {
        this.mvpView = null;
        //销毁时解除关联
        onUnsubscribe();
    }


    //RXjava取消注册，以避免内存泄露
    public void onUnsubscribe() {
        mCompositeDisposable.clear();
    }

    //子类通过addSubscription方法,实现访问接口,并且得到回调
    public void addSubscription(Observable observable, DisposableObserver disposable) {

        mCompositeDisposable.add((Disposable) observable
                .delay(2000, TimeUnit.MILLISECONDS)
//                .retryWhen(new RetryWhenNetworkException())//当访问失败时,重试。并且拦截onError
                .subscribeOn(Schedulers.io())// 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 的回调发生在主线程
                .subscribeWith(disposable));


    }



}
