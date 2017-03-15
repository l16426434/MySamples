package com.example.liujishun.mysamples.test;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liujishun.mysamples.R;
import com.example.liujishun.mysamples.test.fragment.CycleFragment;
import com.flyco.tablayout.SegmentTabLayout;

import java.util.ArrayList;

import me.yokeyword.fragmentation.SupportFragment;

@SuppressLint("ValidFragment")
public class SimpleCardFragment extends SupportFragment {
    private int position;
    private int num;
    private String[] mTitles_2 = {"首页", "消息", "联系人"};
    private ArrayList<Fragment> mFragments2 = new ArrayList<>();
    public static SimpleCardFragment getInstance(int position,int num) {
        SimpleCardFragment sf = new SimpleCardFragment();
        sf.position = position;
        sf.num = num;
        return sf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fr_simple_card, null);
        for (String title : mTitles_2) {
            mFragments2.add(CycleFragment.newInstance(2));
        }
        SegmentTabLayout tabLayout_4 = (SegmentTabLayout) v.findViewById(R.id.tl_4);
        tabLayout_4.setTabData(mTitles_2,_mActivity,R.id.fl_my,mFragments2);

        tabLayout_4.showDot(position);
        return v;
    }
}