package com.example.liujishun.mysamples.test;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.widget.FrameLayout;

import com.example.liujishun.mysamples.R;
import com.example.liujishun.mysamples.base.BaseActivity;
import com.example.liujishun.mysamples.base.x5webview.X5WebView;
import com.example.liujishun.mysamples.base.x5webview.X5WebViewJavaScriptFunction;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * Created by liujishun on 16/7/6.
 */

public class WebTest extends BaseActivity {

    private FrameLayout mRootFrameLayout;
    private X5WebView mWebView;
    private String mHomeUrl = "http://27.223.102.50:8099/RD-Office/weixin/index.html";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        mRootFrameLayout = (FrameLayout) findViewById(R.id.fragment_webroot_layout);
        webViewTransportTest();

        mTestHandler.sendEmptyMessageDelayed(MSG_INIT_UI, 10);
    }

    private void webViewTransportTest() {
        X5WebView.setSmallWebViewEnabled(true);
    }

    public static final int MSG_INIT_UI = 1;
    private Handler mTestHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_INIT_UI:
                    initView();
                    break;
            }
            super.handleMessage(msg);
        }
    };


    private void initView() {
        mWebView = new X5WebView(this);

        mRootFrameLayout.addView(mWebView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                // TODO Auto-generated method stub
                Log.e("should", "request.getUrl().toString() is " + request.getUrl().toString());
                return super.shouldInterceptRequest(view, request);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });



        WebSettings webSetting = mWebView.getSettings();
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(false);
        // webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        // webSetting.setDatabaseEnabled(true);
//        webSetting.setDomStorageEnabled(true);
        webSetting.setJavaScriptEnabled(true);
//        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        webSetting.setAppCachePath(this.getDir("appcache", 0).getPath());
        webSetting.setDatabasePath(this.getDir("databases", 0).getPath());
//        webSetting.setGeolocationDatabasePath(this.getDir("geolocation", 0).getPath());
//         webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        // webSetting.setPreFectch(true);
        mWebView.loadUrl(mHomeUrl);

        CookieSyncManager.createInstance(this);
        CookieSyncManager.getInstance().sync();


        mWebView.addJavascriptInterface(new X5WebViewJavaScriptFunction() {
            @Override
            public void onJsFunctionCalled(String tag) {

            }


            @JavascriptInterface
            public void backFun() {
                finish();
            }

        }, "fgqqg");

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView != null && mWebView.canGoBack()) {
                mWebView.goBack();
                return true;
            } else
                return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        if (mWebView != null)
            mWebView.destroy();
        super.onDestroy();
    }
}
