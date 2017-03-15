package com.example.liujishun.mysamples.base.mvp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.example.liujishun.mysamples.base.BaseFragment;

import javax.inject.Inject;


/**
 * A simple {@link Fragment} subclass.
 */
public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment {
    @Inject
    protected P mvpPresenter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        mvpPresenter = createPresenter();
    }
    //关联P层 得到对象
//    protected abstract P createPresenter();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //解除关联
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }
}
