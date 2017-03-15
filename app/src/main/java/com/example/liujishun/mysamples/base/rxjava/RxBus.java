package com.example.liujishun.mysamples.base.rxjava;


import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by liujishun on 16/6/29.
 */

public class RxBus {
    private static volatile RxBus defaultInstance;
    private PublishSubject<Object> bus = PublishSubject.create();
    // PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者
    public RxBus() {

    }
    // 单例RxBus
    public static RxBus getDefault() {
        RxBus rxBus = defaultInstance;
        if (defaultInstance == null) {
            synchronized (RxBus.class) {
                rxBus = defaultInstance;
                if (defaultInstance == null) {
                    rxBus = new RxBus();
                    defaultInstance = rxBus;
                }
            }
        }
        return rxBus;
    }
    // 提供了一个新的事件
    public void post (Object o) {
        bus.onNext(o);
    }
    // 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
    public <T> Observable toObserverable (Class<T> eventType) {
        return bus.ofType(eventType);
    }
}