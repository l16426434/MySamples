package com.example.liujishun.mysamples.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.liujishun.mysamples.R;

/**
 * Created by liujishun on 16/6/29.
 * MaterialDialog 的使用例子
 */

public class DialogTest extends Activity {
    MaterialDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new MaterialDialog.Builder(this)
                .title("title")
                .content("content")
                .negativeText("negative")
                .positiveText("positive")
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.cancel();
                    }
                })
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.cancel();
                    }
                })
                .show();

    }

    public void showProgress() {

        dialog = new MaterialDialog.Builder(this)
                .content("请等待...")
                .progress(true, 0)
                .progressIndeterminateStyle(false)
                .cancelable(false)
                .build();
        dialog.show();
    }

    public void dissmissProgress() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
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

            }
        });
        camera_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

}
