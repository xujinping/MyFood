package com.xjp.myapp.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xjp.myapp.utils.MyLog;

/**
 * Description:Fragment 基础类
 * User: xjp
 * Date: 2015/3/19
 * Time: 10:55
 */
public abstract class BaseFragment extends Fragment {

    private static String TAG;
    protected Activity mActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        TAG = this.getClass().getSimpleName();
        MyLog.d(TAG, "--onCreate---");
        mActivity = getActivity();
        getBundle();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        MyLog.d(TAG, "--onCreateView---");
        return initView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        MyLog.d(TAG, "--onDestroyView---");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        MyLog.d(TAG, "--onDestroy---");
        super.onDestroy();
    }

    //初始化布局UI
    protected abstract View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    //获取传递参数
    protected abstract void getBundle();

    //打印Log
    protected void showLog(String msg) {
        MyLog.d(TAG, msg);
    }
}
