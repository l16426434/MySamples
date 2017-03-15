package com.example.liujishun.mysamples.base.rxjava;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by Administrator
 * on 2016/5/18.
 */
public class SubscriberCallBack<T> extends DisposableObserver<T> {
    private ApiCallback<T> apiCallback;

    public SubscriberCallBack(ApiCallback<T> apiCallback) {
        this.apiCallback = apiCallback;
    }


    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            //httpException.response().errorBody().string()
            int code = httpException.code();
            String msg = httpException.getMessage();
            //            if (code == 504) {
            //                msg = "网络不给力";
            //            }
            apiCallback.onFailure(code, msg);
        } else {
            if (apiCallback != null) {
                apiCallback.onFailure(0, e.getMessage());
            }

        }
        apiCallback.onCompleted();
    }

    @Override
    public void onComplete() {
        if (apiCallback != null) {
            apiCallback.onCompleted();
        }
    }

    @Override
    public void onNext(T t) {
        apiCallback.onSuccess(t);
    }
}
