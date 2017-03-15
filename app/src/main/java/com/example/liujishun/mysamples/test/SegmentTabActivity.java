package com.example.liujishun.mysamples.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.liujishun.mysamples.R;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

import me.yokeyword.fragmentation.SupportActivity;

public class SegmentTabActivity extends SupportActivity {
    private ArrayList<Fragment> mFragments2 = new ArrayList<>();

    private String[] mTitles_2 = {"首页", "消息", "联系人"};

    private CommonTabLayout mTabLayout_2;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segment_tab);

        
        for (int i = 0; i < mTitles_2.length; i++) {
            //tab 对象
            mTabEntities.add(new TabEntity(mTitles_2[i], R.mipmap.icon, R.mipmap.delete));
        }
        for (String title : mTitles_2) {
            mFragments2.add(SimpleCardFragment.getInstance(2,9));
        }
        
        mTabLayout_2 = (CommonTabLayout) findViewById(R.id.tl_2);
        
        mTabLayout_2.setTabData(mTabEntities,this,R.id.fl_change,mFragments2);
        //两位数
        mTabLayout_2.showMsg(0, 0);
        mTabLayout_2.setMsgMargin(0, -5, 5);

        //三位数
        mTabLayout_2.showMsg(1, 100);
        mTabLayout_2.setMsgMargin(1, -5, 5);

        //设置未读消息红点
        mTabLayout_2.showDot(2);
        
        
    }
    public class TabEntity implements CustomTabEntity {
        public String title;
        public int selectedIcon;
        public int unSelectedIcon;

        public TabEntity(String title, int selectedIcon, int unSelectedIcon) {
            this.title = title;
            this.selectedIcon = selectedIcon;
            this.unSelectedIcon = unSelectedIcon;
        }

        @Override
        public String getTabTitle() {
            return title;
        }

        @Override
        public int getTabSelectedIcon() {
            return selectedIcon;
        }

        @Override
        public int getTabUnselectedIcon() {
            return unSelectedIcon;
        }
    }
}
