package com.xjp.myapp.base;

import com.xjp.myapp.R;
import com.xjp.myapp.network.HttpResult;
import com.xjp.myapp.network.VolleyHttp;
import com.xjp.myapp.utils.Urls;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Description: 封装了网络请求的Activity基础类
 * User: xjp
 * Date: 2015/3/10
 * Time: 17:00
 */
public abstract class BaseHttpActivity extends BaseActivity implements HttpResult {

    //加载进度对话框
    protected Dialog loadingDialog;

    //加载动画
    private AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();//初始化控件
        initData();//初始化数据
        initLoadingDialog();//初始化加载进度对话框
        loadData();//开始加载数据
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!animationDrawable.isRunning()) {
            animationDrawable.start();
        }
    }

    /**
     * 初始化加载对话框
     */
    private void initLoadingDialog() {
        loadingDialog = new Dialog(this, R.style.FullDialog);
        loadingDialog.setContentView(R.layout.loading_dialog);
        loadingDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (KeyEvent.ACTION_DOWN == event.getAction() && keyCode == KeyEvent.KEYCODE_BACK) {
                    finish();
                }
                return false;
            }
        });
        final TextView txtTip = (TextView) loadingDialog.findViewById(R.id.tv_loading_tip);
        ImageView imgLoading = (ImageView) loadingDialog.findViewById(R.id.img_loading);
        final Button reButton = (Button) loadingDialog.findViewById(R.id.btn_reload);
        reButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtTip.setText(R.string.loading);
                reButton.setVisibility(View.GONE);
                if (null != animationDrawable && !animationDrawable.isRunning()) {
                    animationDrawable.start();
                }
                reLoad();
            }
        });

        imgLoading.setBackgroundResource(R.drawable.loading_progress);
        loadingDialog.show();
        animationDrawable = (AnimationDrawable) imgLoading.getBackground();
    }


    /**
     * 封装的网络请求
     */
    protected <T> void get(String url, Class<T> mClass) {
        String strUlr = Urls.BASE_URL + url;
        VolleyHttp.getInstance().get(strUlr, this, mClass, loadingDialog, animationDrawable);
        showLog(strUlr);
    }

    //初始化控件
    protected abstract void initView();

    //初始化数据
    protected abstract void initData();

    //加载数据
    protected abstract void loadData();

    //重新加载数据
    protected abstract void reLoad();

}
