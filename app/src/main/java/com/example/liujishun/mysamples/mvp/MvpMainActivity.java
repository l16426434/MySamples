package com.example.liujishun.mysamples.mvp;

import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.liujishun.mysamples.R;
import com.example.liujishun.mysamples.R2;
import com.example.liujishun.mysamples.base.mvp.MvpActivity;
import com.example.liujishun.mysamples.bean.Cat;
import com.lufficc.stateLayout.StateLayout;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 由Activity/Fragment实现View里方法，包含一个Presenter的引用
 */
public class MvpMainActivity extends MvpActivity<MainPresenter> implements MainView {

    @BindView(R2.id.text)
    TextView mText;
    @BindView(R2.id.progressBar)
    ProgressBar mProgressBar;

    @Inject
    Cat mCat;
    @BindView(R2.id.stateLayout)
    StateLayout mStateLayout;


    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_mvp;
    }

    @Override
    protected void initEventAndData() {
        //请求接口
        mvpPresenter.loadData("101010100");
    }


    @Override
    public void getDataSuccess(MainModel model) {
        //接口成功回调
        MainModel.WeatherinfoBean weatherinfo = model.getWeatherinfo();
        String showData = weatherinfo.getCity() + weatherinfo.getWD() + weatherinfo.getWS()
                + weatherinfo.getTime();
        mText.setText(showData);

    }

    @Override
    public void getDataFail(String msg) {

    }

    @Override
    public void showLoading() {
        mStateLayout.showProgressView();
    }

    @Override
    public void hideLoading() {
        mStateLayout.showContentView();
    }

    @Override
    public void main() {
        showToast(mCat.getName());
    }
    
}
