package com.xjp.myapp.application;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * 应用的Application
 * User: xjp
 * Date: 2015/3/7
 * Time: 17:40
 */
public class MyApplication extends Application {

    private List<Activity> activities;

    public static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        activities = new ArrayList<>();
    }


    /**
     * 添加 Activity 到 List 列表
     */
    public void addActivity(Activity activity) {
        synchronized (this) {
            activities.add(activity);
        }
    }

    /**
     * 从 List 列表中移除 Activity
     */
    public void removeActivity(Activity activity) {
        synchronized (this) {
            activities.remove(activity);
        }
    }

    /**
     * 完全退出整个应用程序
     */
    public void closeAllActivity() {
        if (null != activities && activities.size() > 0) {
            for (Activity activity : activities) {
                activity.finish();
            }
        }
    }
}
