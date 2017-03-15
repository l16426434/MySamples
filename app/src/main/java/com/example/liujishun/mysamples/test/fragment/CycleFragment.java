package com.example.liujishun.mysamples.test.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liujishun.mysamples.R;
import com.example.liujishun.mysamples.base.BaseFragment;
import com.lufficc.stateLayout.StateLayout;

import butterknife.ButterKnife;

import static com.example.liujishun.mysamples.R.id.action_item1;


/**
 * Created by YoKeyword on 16/2/7.
 */
public class CycleFragment extends BaseFragment {
    private static final String ARG_NUMBER = "arg_number";

    private Toolbar mToolbar;
    private TextView mTvName;
    private Button mBtnNext, mBtnNextWithFinish;
    private StateLayout mStateLayout;

    private int mNumber;
    private String mString;

    public static CycleFragment newInstance(int number) {
        CycleFragment fragment = new CycleFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_NUMBER, number);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mNumber = args.getInt(ARG_NUMBER);
        }

    }

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cycle, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mStateLayout = (StateLayout) view.findViewById(R.id.stateLayout);
        mStateLayout.showErrorView();
        mStateLayout.setErrorAction(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStateLayout.showProgressView();
            }
        });
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mTvName = (TextView) view.findViewById(R.id.tv_name);
        mBtnNext = (Button) view.findViewById(R.id.btn_next);
        mBtnNextWithFinish = (Button) view.findViewById(R.id.btn_next_with_finish);

        String title = "循环Fragment" + mNumber;

        mToolbar.setTitle(title);
        //左边导航图片的点击事件
        mToolbar.setNavigationIcon(R.mipmap.delete);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(_mActivity, "toolbar", Toast.LENGTH_SHORT).show();
            }
        });
        mToolbar.inflateMenu(R.menu.base_toolbar_menu);//设置右上角的填充菜单
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {//添加点击事件
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int menuItemId = item.getItemId();
                if (menuItemId == R.id.action_search) {
                    Toast.makeText(_mActivity, "action_search", Toast.LENGTH_SHORT).show();

                } else if (menuItemId == action_item1) {
                    Toast.makeText(_mActivity, "action_item1", Toast.LENGTH_SHORT).show();

                } else if (menuItemId == R.id.action_item2) {
                    Toast.makeText(_mActivity, "action_item2:", Toast.LENGTH_SHORT).show();

                }
                return true;
            }


        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}

