package com.xjp.myapp.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xjp.myapp.R;
import com.xjp.myapp.network.HttpResult;
import com.xjp.myapp.network.VolleyHttp;
import com.xjp.myapp.utils.Urls;

/**
 * Description:Fragment网络请求基础类
 * User: xjp
 * Date: 2015/3/19
 * Time: 11:05
 */
public abstract class BaseHttpFragment extends BaseFragment implements HttpResult {

    protected Dialog loadingDialog;
    protected AnimationDrawable animationDrawable;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initLoadingDialog();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
        if (!animationDrawable.isRunning()) {
            animationDrawable.start();
        }
    }

    protected <T> void get(String url, Class<T> clzz) {
        String strUrl = Urls.BASE_URL + url;
        VolleyHttp.getInstance().get(strUrl, this, clzz, loadingDialog, animationDrawable);
        showLog(strUrl);
    }

    private void initLoadingDialog() {
        loadingDialog = new Dialog(mActivity, R.style.FullDialog);
        loadingDialog.setContentView(R.layout.loading_dialog);
        loadingDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (KeyEvent.ACTION_DOWN == event.getAction() && keyCode == KeyEvent.KEYCODE_BACK) {
                    mActivity.finish();
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

    //重新加载数据
    protected abstract void reLoad();

    //加载数据
    protected abstract void loadData();

}
