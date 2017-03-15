package com.example.liujishun.mysamples.base;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;


/**
 *
 */
public abstract class BaseFragment extends SupportFragment {


    protected View mContentView;
    private Toast mToast;

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
    }

    public SupportActivity getContext() {
        return _mActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 避免多次从xml中加载布局文件
        if (mContentView == null) {
            mContentView = createView(inflater, container, savedInstanceState);
        } else {
            ViewGroup parent = (ViewGroup) mContentView.getParent();
            if (parent != null) {
                parent.removeView(mContentView);
            }
        }
        return mContentView;
    }

    protected abstract View createView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState);

    @Override
    public void onDestroy() {
        super.onDestroy();
        onUnsubscribe();
        cancelToast();
    }


    public void showToast(String str) {
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
        mToast = Toast.makeText(_mActivity, str, Toast.LENGTH_SHORT);
        mToast.show();

    }

    public void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
    }


    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public void onUnsubscribe() {
        mCompositeDisposable.clear();//取消注册，以避免内存泄露
    }

    //子类通过addSubscription方法,实现访问接口,并且得到回调
    public void addSubscription(Observable observable, DisposableObserver disposable) {

        mCompositeDisposable.add((Disposable) observable
                .delay(2000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())// 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 的回调发生在主线程
                .subscribeWith(disposable));


    }


}
