package com.xjp.myapp.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xjp.myapp.R;
import com.xjp.myapp.network.HttpResult;
import com.xjp.myapp.network.VolleyHttp;
import com.xjp.myapp.utils.ShowDialog;
import com.xjp.myapp.utils.Urls;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Description:
 * User: xjp
 * Date: 2015/3/10
 * Time: 17:00
 */
public abstract class BaseHttpActivity extends BaseActivity implements HttpResult {

    protected Dialog loadingDialog;
    private AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initLoadingDialog();
        loadData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!animationDrawable.isRunning()) {
            animationDrawable.start();
        }
    }

    private void initLoadingDialog() {
        loadingDialog = new Dialog(this, R.style.FullDialog);
        loadingDialog.setContentView(R.layout.loading_dialog);
        loadingDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (KeyEvent.ACTION_DOWN == event.getAction() && keyCode == KeyEvent.KEYCODE_BACK){
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


    protected <T> void get(String url, Class<T> mClass) {
        String strUlr = Urls.BASE_URL + url;
        VolleyHttp.getInstance().get(strUlr, this, mClass, loadingDialog, animationDrawable);
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
