package com.example.liujishun.mysamples.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.liujishun.mysamples.R;
import com.example.liujishun.mysamples.base.BaseActivity;
import com.mbase.monch.activity.ActivityJump;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 选择头像的例子
 * 拍照,本地相册
 * 得到图片后进行图片压缩,并显示
 */
public class BitmapTest extends BaseActivity {


    @BindView(R.id.user_img)
    ImageView mUserImg;
   

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_activity);
        ButterKnife.bind(this);
        
    }


    private void showDialog() {
        LayoutInflater li = LayoutInflater.from(this);
        View view = li.inflate(R.layout.dialog_layout2, null);
        View picture_layout = view.findViewById(R.id.picture_layout);
        View camera_layout = view.findViewById(R.id.camera_layout);
        //自定义view 的dialog
        final MaterialDialog dialog = new MaterialDialog.Builder(this)
                .customView(view, true)
                .cancelable(true)
                .show();

        picture_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                //通过相册，选择图片
                Intent i = new Intent();
                bundle = new Bundle();
                bundle.putInt("value",1);
                i.putExtras(bundle);

                ActivityJump.BundleJump(BitmapTest.this,TestBitmap2.class,bundle);

            }
        });
        camera_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                //通过拍照，选择图片
               
            }
        });
    }


    @OnClick(R.id.img_layout)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_layout:
                showDialog();
                break;
        }
    }
    
}
