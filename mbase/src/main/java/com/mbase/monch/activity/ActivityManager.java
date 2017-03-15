package com.mbase.monch.activity;

import android.app.Activity;

import java.util.Stack;

public class ActivityManager {
    // 栈
    private static Stack<Activity> activityStack = new Stack<Activity>();

    private static ActivityManager instance;

    private ActivityManager() {
    }

    // 管理器
    public static ActivityManager getScreenManager() {
        if (instance == null) {
            instance = new ActivityManager();
        }
        return instance;
    }

    // 栈顶弹出
    public void popActivity() {
        Activity activity = activityStack.lastElement();
        if (activity != null) {
            activity.finish();
            activity = null;
        }
    }

    // 指定activity弹出..
    public void popActivity(Activity activity) {
        if (activity != null) {
            activity.finish();
            activityStack.remove(activity);
            activity = null;
        }
    }

    // 弹出到指定activity
    public void popAssignActivity(Class clazz) {
        for (int a = activityStack.size() - 1; a >= 0; a--) {
            String currentActivity = activityStack.get(a).getClass().getSimpleName();
            String targetActivity = clazz.getSimpleName();
            if (!currentActivity.equals(targetActivity)) {
                ActivityManager.getScreenManager().popActivity(
                        activityStack.get(a));
            } else {
                return;
            }

        }
    }

    // 返回栈顶activity
    public Activity currentActivity() {
        if (activityStack == null || activityStack.size() == 0) {
            return null;
        }
        Activity activity = activityStack.lastElement();

        return activity;
    }

    // 增加（压栈）
    public void pushActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }

        activityStack.add(activity);

    }

    // 保留一个指定activity
    public void popAllActivityExceptOne(Class<?> cls) {
        while (true) {
            Activity activity = currentActivity();
            if (activity == null) {
                break;
            }
            if (activity.getClass().equals(cls)) {
                break;
            }
            popActivity(activity);
        }
    }

    // 弹出所有activity
    public void popAllActivity() {
        while (true) {
            Activity activity = currentActivity();
            if (activity == null) {
                break;
            }
            popActivity(activity);
        }

    }

    // 弹出所有n个activity
    public void popActivity(int num) {
        for (int i = 0; i < num; i++) {
            Activity activity = currentActivity();
            if (activity == null) {
                break;
            }
            popActivity(activity);
        }
    }

    public Stack<Activity> getActivityStack() {
        return activityStack;
    }

    public Activity getActivity(String activityName) {
        for (int i = 0; i < activityStack.size(); i++) {
            String activityNameInStack = activityStack.get(i).getClass().getSimpleName();
            if (activityNameInStack.equals(activityName)) {
                return activityStack.get(i);
            }
        }
        return null;
    }
    /**
     * 退出应用程序
     */
    public void appExit() {
        try {
            popAllActivity();
            //退出JVM（java虚拟机）,释放所占内存资源,0表示正常退出(非0的都为异常退出)
            System.exit(0);
            //从操作系统中结束掉当前程序的进程
            android.os.Process.killProcess(android.os.Process.myPid());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
