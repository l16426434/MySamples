package com.example.liujishun.mysamples.test;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.liujishun.mysamples.R;
import com.example.liujishun.mysamples.base.BaseActivity;
import com.example.liujishun.mysamples.utils.ImageLoader;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by liujishun on 16/3/28.
 * CommonAdapter 的使用例子
 */
public class CommonAdapterTest extends BaseActivity {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.pullLoadMoreRecyclerView)
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_frist);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ArrayList list = new ArrayList();
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        recyclerView.setAdapter(new CommonAdapter<String>(this, R.layout.image, list) {

            @Override
            protected void convert(ViewHolder holder, String s, int position) {
//                holder.setText(R.id.md_title, s);
                ImageView imageView = holder.getView(R.id.image);
                ImageLoader.load(mActivity,"http://wol.jw.org/cmn-Hans/wol/mp/r23/lp-chs/w16/2016/996",imageView);

            }

            @Override
            public void onViewHolderCreated(ViewHolder holder, View itemView) {
                super.onViewHolderCreated(holder, itemView);
                if(!AutoUtils.autoed(itemView)){
                    AutoUtils.auto(itemView);
                }

            }
        });


    }


}
