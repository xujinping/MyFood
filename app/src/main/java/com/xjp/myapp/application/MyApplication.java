package com.xjp.myapp.application;

import com.tencent.bugly.crashreport.CrashReport;

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
        initCrash();
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

    /**
     * 利用腾讯Bugly 捕获crash代码，便于开发人员解决bug。
     */
    private void initCrash() {
        String appId = "900002458";
        boolean isDebug = true;  //true代表App处于调试阶段，false代表App发布阶段
        CrashReport.initCrashReport(this, appId, isDebug);  //初始化SDK
        CrashReport.setUserId("myfood");  //设置用户的唯一标识
//        CrashReport.testJavaCrash ();//测试crash上报服务器
    }
}
