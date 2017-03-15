package com.example.liujishun.mysamples.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.liujishun.mysamples.R;
import com.example.liujishun.mysamples.utils.ImageLoader;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by liujishun on 16/3/28.
 * CommonAdapter 的使用例子
 */
public class CommonAdapterTest2 extends Activity {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;

    RecyclerView recyclerView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_frist);
        ButterKnife.bind(this);

        recyclerView = mPullLoadMoreRecyclerView.getRecyclerView();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ArrayList list = new ArrayList();
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        recyclerView.setAdapter(new BaseQuickAdapter<String,BaseViewHolder>(R.layout.image,list) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {

                AutoUtils.auto(helper.getConvertView());
                ImageView imageView = helper.getView(R.id.image);
                ImageLoader.load(CommonAdapterTest2.this,"http://wol.jw.org/cmn-Hans/wol/mp/r23/lp-chs/w16/2016/996",imageView);
            }
        });


    }


}
