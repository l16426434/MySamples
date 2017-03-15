package com.mbase.monch.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


public class ActivityJump {

    // 普通跳转
    public static void NormalJump(Context mContext, Class<?> cls) {

        Activity activity = ActivityManager.getScreenManager().getActivity(cls.getSimpleName());
        if (activity != null) {
            ActivityManager.getScreenManager().popActivity(activity);
        }

//        ActivityManager.getScreenManager()
//                .pushActivity((Activity) mContext);

        Intent intent = new Intent(mContext, cls);

        ActivityUtil.startActivity(mContext,intent);


    }

    // 带参跳转
    public static void BundleJump(Context mContext, Class<?> cls, Bundle bundle) {
        Activity activity = ActivityManager.getScreenManager().getActivity(cls.getSimpleName());
        if (activity != null) {
            ActivityManager.getScreenManager().popActivity(activity);
        }
        //		if (!mContext.getClass().equals(MainActivity.class)) {
//        ActivityManager.getScreenManager()
//                .pushActivity((Activity) mContext);
        //		}
        //		ActivityManager.getScreenManager().pushActivity((Activity) mContext);
        Intent intent = new Intent(mContext, cls);
        intent.putExtras(bundle);
        ActivityUtil.startActivity(mContext,intent);
        // ((Activity) mContext).overridePendingTransition(R.anim.activity_in,
        // R.anim.activity_out);
    }

    // 带返回值跳转跳转
    public static void JumpForResult(Activity mActivity, Class<?> cls,
                                     Bundle bundle, int requestCode) {
        Activity activity = ActivityManager.getScreenManager().getActivity(cls.getSimpleName());
        if (activity != null) {
            ActivityManager.getScreenManager().popActivity(activity);
        }

//        ActivityManager.getScreenManager().pushActivity(mActivity);

        Intent intent = new Intent(mActivity, cls);
        intent.putExtras(bundle);

        ActivityUtil.startActivityForResult(mActivity,intent,requestCode);

    }

    public static void JumpForResultNobundle(Activity mActivity, Class<?> cls,
                                             int requestCode) {
        Activity activity = ActivityManager.getScreenManager().getActivity(cls.getSimpleName());
        if (activity != null) {
            ActivityManager.getScreenManager().popActivity(activity);
        }
        //		if (!mActivity.getClass().equals(MainActivity.class)) {
//        ActivityManager.getScreenManager().pushActivity(mActivity);
        //		}
        Intent intent = new Intent(mActivity, cls);


        ActivityUtil.startActivityForResult(mActivity,intent,requestCode);
        // mActivity.overridePendingTransition(R.anim.activity_in,
        // R.anim.activity_out);

    }

    // 登录跳转
    // public static void LoginStatusCheck(Context mContext, Class<?> cls) {
    // if (!mContext.getClass().equals(MainActivity.class)) {
    // ActivityManager.getScreenManager()
    // .pushActivity((Activity) mContext);
    // }
    // //Boolean result = ConnData.getLoginState();
    // if (result) {
    // Intent intent = new Intent(mContext, cls);
    // mContext.startActivity(intent);
    // } else {
    // // ConnData.setJumpClass(cls);
    // Intent intent = new Intent(mContext, LoginActivity.class);
    // mContext.startActivity(intent);
    // }
    // }

    // 跳转到登陆
    public static void NormalJumpFromLogin(Context mContext, Class<?> cls) {
        Intent intent = new Intent(mContext, cls);
        ActivityUtil.startActivity(mContext,intent);
    }

    // 单一返回
    public static void Back(Context mContext) {
        if (((Activity) mContext).equals(ActivityManager.getScreenManager()
                .currentActivity())) {
            ActivityManager.getScreenManager().popActivity((Activity) mContext);
        } else {
            ((Activity) mContext).finish();
        }
    }

    //
    public static void BackToHome(Activity activity) {
//        ActivityManager.getScreenManager().popAllActivityExceptOne(MainActivityNew.class);
//        activity.finish();
    }



    // 返回登陆
    public static void BackToLogin(Context mContext) {
//        ActivityJump.NormalJump(mContext, LoginActivity.class);
    }

}
