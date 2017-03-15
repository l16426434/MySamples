package com.example.liujishun.mysamples.base.mvp;

public interface Presenter<V> {

    void attachView(V view);

    void detachView();

}
