package com.example.liujishun.mysamples;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.liujishun.mysamples.base.BaseActivity;
import com.example.liujishun.mysamples.mvp.MvpMainActivity;
import com.example.liujishun.mysamples.test.CommonAdapterTest2;
import com.example.liujishun.mysamples.test.Demo7Activity;
import com.example.liujishun.mysamples.test.DialogTest;
import com.example.liujishun.mysamples.test.FrescoTest;
import com.example.liujishun.mysamples.test.HtmlJsTest;
import com.example.liujishun.mysamples.test.LogTest;
import com.example.liujishun.mysamples.test.RxBusTest;
import com.example.liujishun.mysamples.test.RxJavaTest;
import com.example.liujishun.mysamples.test.SegmentTabActivity;
import com.example.liujishun.mysamples.test.SqliteTest;
import com.example.liujishun.mysamples.test.TestActivity;
import com.example.liujishun.mysamples.test.TestBitmap2;
import com.example.liujishun.mysamples.test.TitleTest;
import com.example.liujishun.mysamples.test.WebTest;
import com.lufficc.stateLayout.StateLayout;
import com.mbase.monch.activity.ActivityJump;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.mvp)
    Button mMvp;
    @BindView(R.id.activity_main2)
    LinearLayout mActivityMain2;
    @BindView(R.id.log)
    Button mLog;
    @BindView(R.id.pullLoadMoreRecyclerView)
    Button mPullLoadMoreRecyclerView;
    @BindView(R.id.bitmap)
    Button mBitmap;
    @BindView(R.id.common_adapter)
    Button mCommonAdapter;
    @BindView(R.id.compressor)
    Button mCompressor;
    @BindView(R.id.dialog)
    Button mDialog;
    @BindView(R.id.fresco)
    Button mFresco;
    @BindView(R.id.html)
    Button mHtml;
    @BindView(R.id.rxjava)
    Button mRxjava;
    @BindView(R.id.sqlite)
    Button mSqlite;
    @BindView(R.id.rxBus)
    Button mRxBus;
    @BindView(R.id.title)
    Button mTitle;
    @BindView(R.id.stateLayout)
    StateLayout mStateLayout;
    @BindView(R.id.webview)
    Button mWebview;
    @BindView(R.id.demo)
    Button mDemo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        
        mMvp.setText("wo de mvp");
    }

    @OnClick({R.id.mvp, R.id.log, R.id.pullLoadMoreRecyclerView, R.id.bitmap, R.id.common_adapter, R.id.compressor, R.id.dialog, R.id.fresco, R.id.html, R.id.rxjava, R.id.sqlite, R.id.rxBus, R.id.title, R.id.webview, R.id.demo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mvp:
                ActivityJump.NormalJump(mActivity, MvpMainActivity.class);
                break;
            case R.id.log:
                ActivityJump.NormalJump(mActivity, LogTest.class);
                break;
            case R.id.pullLoadMoreRecyclerView:
                ActivityJump.NormalJump(mActivity, TestActivity.class);
                break;
            case R.id.bitmap:
                ActivityJump.NormalJump(mActivity, TestBitmap2.class);
                break;
            case R.id.common_adapter:
                ActivityJump.NormalJump(mActivity, CommonAdapterTest2.class);
                break;
            case R.id.compressor:
                ActivityJump.NormalJump(mActivity, SegmentTabActivity.class);
                break;
            case R.id.dialog:
                ActivityJump.NormalJump(mActivity, DialogTest.class);
                break;
            case R.id.fresco:
                ActivityJump.NormalJump(mActivity, FrescoTest.class);
                break;
            case R.id.html:
                ActivityJump.NormalJump(mActivity, HtmlJsTest.class);
                break;
            case R.id.rxjava:
                ActivityJump.NormalJump(mActivity, RxJavaTest.class);
                break;
            case R.id.sqlite:
                ActivityJump.NormalJump(mActivity, SqliteTest.class);
                break;
            case R.id.rxBus:
                ActivityJump.NormalJump(mActivity, RxBusTest.class);
                break;
            case R.id.title:
                ActivityJump.NormalJump(mActivity, TitleTest.class);
                break;
            case R.id.webview:
                ActivityJump.NormalJump(mActivity, WebTest.class);
                break;

            case R.id.demo:
                ActivityJump.NormalJump(mActivity, Demo7Activity.class);
                break;
        }
    }
    
}
