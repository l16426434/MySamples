package com.example.liujishun.mysamples.base;

import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.litesuits.orm.LiteOrm;
import com.mbase.monch.activity.ActivityManager;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import me.yokeyword.fragmentation.SupportActivity;

public abstract class BaseActivity extends SupportActivity {

    public static String TAG = BaseActivity.class.getName();

    protected BaseActivity mActivity;
    public Resources resource;
    public Bundle bundle;
    private Toast mToast;
    private MaterialDialog dialog;

    @Inject
    public LiteOrm db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        ActivityManager.getScreenManager().pushActivity(this);
        mActivity = this;
        resource = this.getResources();
        bundle = new Bundle();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        dissmissProgress();
        cancelToast();
    }

    @Override
    protected void onDestroy() {
        ActivityManager.getScreenManager().popActivity(this);
        // TODO Auto-generated method stub
        super.onDestroy();
        onUnsubscribe();

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

    public void showProgress() {

        dialog = new MaterialDialog.Builder(this)
                .content("请等待...")
                .progress(true, 0)
                .progressIndeterminateStyle(false)
                .cancelable(true)
                .build();
        dialog.show();
    }

    public void dissmissProgress() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    protected void showToast(String str) {
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
        mToast = Toast.makeText(mActivity, str, Toast.LENGTH_SHORT);
        mToast.show();

    }

    protected void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
        }
    }

    public static boolean hasKitKat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    public static boolean hasLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    private static final String LAYOUT_LINEARLAYOUT = "LinearLayout";
    private static final String LAYOUT_FRAMELAYOUT = "FrameLayout";
    private static final String LAYOUT_RELATIVELAYOUT = "RelativeLayout";


    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = null;
        if (name.equals(LAYOUT_FRAMELAYOUT)) {
            view = new AutoFrameLayout(context, attrs);
        }

        if (name.equals(LAYOUT_LINEARLAYOUT)) {
            view = new AutoLinearLayout(context, attrs);
        }

        if (name.equals(LAYOUT_RELATIVELAYOUT)) {
            view = new AutoRelativeLayout(context, attrs);
        }

        if (view != null)
            return view;

        return super.onCreateView(name, context, attrs);
    }
}
