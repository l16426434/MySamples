package com.example.liujishun.mysamples.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liujishun.mysamples.R;
import com.example.liujishun.mysamples.base.BaseFragment;

import java.util.List;

/**
 * Created by liujishun on 16/7/21.
 */

public class TestFragment extends BaseFragment {
    private boolean mHasLoadedOnce = false;// 页面是否加载过
    private List mNetData;// 网络数据

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main2,null,false);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && !mHasLoadedOnce && mNetData == null) {
            //TO-DO 执行网络数据请求
            mHasLoadedOnce = true;
        }
    }
}
