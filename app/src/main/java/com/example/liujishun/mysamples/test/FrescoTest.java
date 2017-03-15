package com.example.liujishun.mysamples.test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.liujishun.mysamples.R;
import com.example.liujishun.mysamples.utils.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liujishun on 16/7/1.
 * Fresco 的使用例子
 */

public class FrescoTest extends Activity {
    @BindView(R.id.image)
    ImageView image;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image);
        ButterKnife.bind(this);


        ImageLoader.load(this,"http://wol.jw.org/cmn-Hans/wol/mp/r23/lp-chs/w16/2016/996",image);
//        image.setResource(R.mipmap.ic_launcher);//从本地resource得到图片
//        image.setFile(new File(""));//
//        image.setFilePath("");
    }

}
