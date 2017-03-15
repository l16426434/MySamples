package com.example.liujishun.mysamples.base.mvp;

import com.example.liujishun.mysamples.mvp.MainModel;

/**
 * Created by liujishun on 2017/2/15.
 */

public interface IBaseView {
    void getDataSuccess(MainModel model);

    void getDataFail(String msg);

    void showLoading();

    void hideLoading();
}
