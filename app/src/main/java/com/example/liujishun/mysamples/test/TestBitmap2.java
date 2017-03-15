package com.example.liujishun.mysamples.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.liujishun.mysamples.R;
import com.example.liujishun.mysamples.base.BaseActivity;
import com.example.liujishun.mysamples.utils.ImageLoader;
import com.example.liujishun.mysamples.utils.TakeUtils;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liujishun on 2017/2/21.
 */

public class TestBitmap2 extends BaseActivity implements TakePhoto.TakeResultListener,InvokeListener {
    @BindView(R.id.user_img)
    ImageView mUserImg;
    private TakePhoto takePhoto;
    private InvokeParam invokeParam;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);
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
                TakeUtils.getInstance().onPickFromGalleryWithCrop(getTakePhoto());
                   
                
            }
        });
        camera_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                //通过拍照，选择图片
                TakeUtils.getInstance().onPickFromCaptureWithCrop(getTakePhoto());
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
    @Override
    public void takeCancel() {
      Logger.e("cancel");
    }

    @Override
    public void takeFail(TResult result, String msg) {
        Logger.e(msg);
    }

    @Override
    public void takeSuccess(final TResult result) {
    
//        Intent intent=new Intent(this,ResultActivity.class);
//        intent.putExtra("images",result.getImages());
//        startActivity(intent);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ImageLoader.load(mActivity,result.getImage().getCompressPath(),mUserImg);
            }
        });
      
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type=PermissionManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
        PermissionManager.handlePermissionsResult(this,type,invokeParam,this);
    }

    /**
     *  获取TakePhoto实例
     * @return
     */
    public TakePhoto getTakePhoto(){
        if (takePhoto==null){
            takePhoto= (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this,this));
        }
        return takePhoto;
    }
    

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type=PermissionManager.checkPermission(TContextWrap.of(this),invokeParam.getMethod());
        if(PermissionManager.TPermissionType.WAIT.equals(type)){
            this.invokeParam=invokeParam;
        }
        return type;
    }



}
