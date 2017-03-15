package com.example.liujishun.mysamples.mvp;


import com.example.liujishun.mysamples.base.mvp.BasePresenter;
import com.example.liujishun.mysamples.base.rxjava.ApiCallback;
import com.example.liujishun.mysamples.base.rxjava.SubscriberCallBack;

import javax.inject.Inject;

/**
 * on 2015/9/23.
 */
public class MainPresenter extends BasePresenter<MainView> {
    //构造函数中关联V
    @Inject
    public MainPresenter() {
       
    }

    public void loadData(String cityId) {
        mvpView.showLoading();
        addSubscription(apiStores.loadData(cityId),
                new SubscriberCallBack(new ApiCallback<MainModel>() {
                    @Override
                    public void onSuccess(MainModel model) {
                        mvpView.getDataSuccess(model);
                        mvpView.main();
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        mvpView.getDataFail(msg);
                    }

                    @Override
                    public void onCompleted() {
                        mvpView.hideLoading();
                    }
                }));


    }



}
