package com.example.liujishun.mysamples.test;


import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liujishun.mysamples.R;
import com.example.liujishun.mysamples.base.BaseFragment;
import com.orhanobut.logger.Logger;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 下拉刷新
 * 上拉更多
 * 自动循环播放的banner
 * recycler add head
 * <p>
 * SwipeRefreshLayout + ViewPager + RecyclerView 解决冲突
 */
public class PullLoadMoreRecycerViewTest extends BaseFragment {

    @BindView(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    private int mCount = 1;
    private CommonAdapter adapter;
    private List<String> networkImages;
    private String[] images = {"http://img2.imgtn.bdimg.com/it/u=3093785514,1341050958&fm=21&gp=0.jpg",
            "http://img2.3lian.com/2014/f2/37/d/40.jpg",
            "http://d.3987.com/sqmy_131219/001.jpg",
            "http://img2.3lian.com/2014/f2/37/d/39.jpg",
            "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        mActivity.setSupportActionBar(mToolbar);
        mToolbar.setTitle("title");
        mToolbar.setNavigationIcon(R.mipmap.ic_launcher);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().onBackPressed();
            }
        });
        //        mPullLoadMoreRecyclerView.setRefreshing(true);//设置下拉刷新是否可见
        //        mPullLoadMoreRecyclerView.setPullRefreshEnable(false);//设置是否下拉刷新
        //        mPullLoadMoreRecyclerView.setPushRefreshEnable(false);//设置是否上拉刷新
        //        mPullLoadMoreRecyclerView.setFooterViewText("loading");//设置上拉刷新文字
        mPullLoadMoreRecyclerView.setLinearLayout();//设置布局类型
        //viewpager 需要的Adapter
        adapter = new CommonAdapter<String>(getContext(),R.layout.image,setList()) {

            @Override
            protected void convert(ViewHolder holder, String s, int position) {

            }
        };

        //添加head foot时 需要RcvAdapterWrapper
        HeaderAndFooterWrapper wrapper = new HeaderAndFooterWrapper(adapter);
        View view1 = LayoutInflater.from(getContext()).inflate(R.layout.viewpage_layout, null);

        
        wrapper.addHeaderView(view1);
//        wrapper.addFootView(view1);

        networkImages = Arrays.asList(images);
     
        //屏蔽viewPager和SwipeRefreshLayout事件冲突
//        layout.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_MOVE:
//                        mPullLoadMoreRecyclerView.getSwipeRefreshLayout().setEnabled(false);
//                        break;
//                    case MotionEvent.ACTION_UP:
//                    case MotionEvent.ACTION_CANCEL:
//                        mPullLoadMoreRecyclerView.getSwipeRefreshLayout().setEnabled(true);
//                        break;
//                }
//                return false;
//            }
//        });


        mPullLoadMoreRecyclerView.setAdapter(wrapper);
        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreListener());

        //        mPullLoadMoreRecyclerView.setEmptyView(LayoutInflater.from(getContext()).inflate(R.layout.empty_view, null));//setEmptyView

    }

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_frist, container, false);
    }

    //模拟访问网络的方法
    private void getData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //新的数据
                        adapter.getDatas().addAll(setList());
                        adapter.notifyDataSetChanged();
                        //改变状态
                        mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
                    }
                });

            }
        }, 1000);

    }

    public void clearData() {
        adapter.getDatas().clear();
        adapter.notifyDataSetChanged();
    }


    private List<String> setList() {
        List<String> dataList = new ArrayList<>();
        int start = 5 * (mCount - 1);
        for (int i = start; i < 5 * mCount; i++) {
            dataList.add("Frist" + i);
        }
        return dataList;

    }


    class PullLoadMoreListener implements PullLoadMoreRecyclerView.PullLoadMoreListener {
        @Override
        public void onRefresh() {
            //下拉刷新时
            setRefresh();
            getData();
        }

        @Override
        public void onLoadMore() {
            //加载更多时
            Logger.e("onLoadMore");
            mCount = mCount + 1;
            getData();
        }
    }

    private void setRefresh() {
        adapter.getDatas().clear();
        mCount = 1;
    }

}
