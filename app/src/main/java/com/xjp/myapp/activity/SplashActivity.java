package com.xjp.myapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.xjp.myapp.R;


/**
 * Description:应用启动页面
 * User: xjp
 * Date: 2015/3/10
 * Time: 14:54
 */

public class SplashActivity extends Activity {

    private final static int SHOW_TIME = 2500;
    private final int DELAY = 0;
    private ImageView imgStartTop;
    private ImageView imgStartBottom;
    private Animation animationTop;
    private Animation animationBottom;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case DELAY:
                    startActivity();
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initView();
    }

    private void initView() {
        imgStartTop = (ImageView) findViewById(R.id.img_start_top);
        imgStartBottom = (ImageView) findViewById(R.id.img_start_bottom);
        animationTop = AnimationUtils.loadAnimation(this, R.anim.move_up);
        animationBottom = AnimationUtils.loadAnimation(this, R.anim.move_bottom);
        imgStartTop.startAnimation(animationTop);
        imgStartBottom.startAnimation(animationBottom);
        mHandler.sendEmptyMessageDelayed(DELAY, SHOW_TIME);

    }

    private void startActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
