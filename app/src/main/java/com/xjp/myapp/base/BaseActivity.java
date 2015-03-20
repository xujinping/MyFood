package com.xjp.myapp.base;

import com.xjp.myapp.R;
import com.xjp.myapp.activity.CategoryActivity;
import com.xjp.myapp.activity.MainActivity;
import com.xjp.myapp.activity.SearchActivity;
import com.xjp.myapp.application.MyApplication;
import com.xjp.myapp.utils.MyLog;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * activity 基础类，所有activity都继承此基础类，便于控制生命周期和代码复用
 * User :xjp
 */
public abstract class BaseActivity extends Activity {

    private String TAG;

    //是否隐藏ActionBar
    protected boolean isHideActionBar = false;

    protected ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TAG = this.getClass().getSimpleName();
        MyLog.d(TAG, "===onCreate===");
        MyApplication.instance.addActivity(this);
        actionBar = getActionBar();
        //是否隐藏 ActionBar
        if (null != actionBar && isHideActionBar) {
            actionBar.hide();
        }
        //显示返回图标
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setContentView(getContentView());
        ImageButton search = (ImageButton) findViewById(R.id.btn_search);
        if (null != search) {
            search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(SearchActivity.class);
                }
            });
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        MyLog.d(TAG, "===onStart===");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        MyLog.d(TAG, "===onNewIntent===");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        MyLog.d(TAG, "===onRestart===");
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyLog.d(TAG, "===onResume===");
    }

    @Override
    protected void onPause() {
        super.onPause();
        MyLog.d(TAG, "===onPause===");
    }

    @Override
    protected void onStop() {
        super.onStop();
        MyLog.d(TAG, "===onStop===");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyLog.d(TAG, "===onDestroy===");
        MyApplication.instance.removeActivity(this);
        if (this instanceof MainActivity) {
            MyApplication.instance.closeAllActivity();
        }
    }

    /**
     * TODO 显示 Toast 提示
     */
    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * TODO 显示 Toast 提示
     */
    protected void showToast(int msgId) {
        Toast.makeText(this, msgId, Toast.LENGTH_SHORT).show();

    }

    /**
     * TODO 显示 Log 打印信息
     */
    protected void showLog(String msg) {
        MyLog.d(TAG, msg);
    }

    /**
     * Activity 的跳转
     */
    protected void startActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (!(this instanceof MainActivity) && !(this instanceof CategoryActivity)) {
                    finish();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 抽象方法加载布局，每个子类都需要实现这个方法来加载布局
     * 在 onCreate()方法中使用setContentView (getContentView());
     */
    protected abstract int getContentView();
}
