package com.example.liujishun.mysamples.base.rxjava;


/**
 *
 */
public interface ApiCallback<T> {

    void onSuccess(T model);

    void onFailure(int code, String msg);

    void onCompleted();

}
