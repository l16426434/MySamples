package com.example.liujishun.mysamples.test;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.example.liujishun.mysamples.base.BaseActivity;
import com.example.liujishun.mysamples.base.rxjava.RxBus;
import com.example.liujishun.mysamples.bean.Event;
import com.orhanobut.logger.Logger;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by liujishun on 16/6/29.
 */

public class RxBusTest extends BaseActivity {

    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rxBusObservers();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                rxBusPost();
            }
        }, 1000);

    }


    private void rxBusObservers() {
        Observable subscription = RxBus.getDefault().toObserverable(Event.Test.class);

        addSubscription(subscription,
                new DisposableObserver<Event.Test>() {

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onNext(Event.Test o) {
                        Logger.e("onNext" + o.toString());
                    }
                });
       
    }

    private void rxBusPost() {
        RxBus.getDefault().post(new Event.Test());//为了方便管理，object 都是在Event 对象下的类
    }

}

