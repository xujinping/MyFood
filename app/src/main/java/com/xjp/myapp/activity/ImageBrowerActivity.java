package com.xjp.myapp.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.xjp.myapp.R;
import com.xjp.myapp.adapter.BasePageAdapter;
import com.xjp.myapp.beans.Index.Datum;
import com.xjp.myapp.beans.Index.Step;
import com.xjp.myapp.network.VolleyHttp;
import com.xjp.myapp.utils.Key;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * User: xjp
 * Date: 2015/3/18
 * Time: 16:15
 */
public class ImageBrowerActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private ViewPager mViewPager;
    private TextView txtPageNum;
    private TextView txtClose;
    private TextView txtTitle;
    private Datum mData;
    private List<View> viewList;
    private BasePageAdapter viewPagerAdapter;
    private int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(R.layout.activity_image_brower);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getIntentData();
        initView();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_image_brower;
    }

    private void initView() {
        total = mData.getSteps().size();
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setOnPageChangeListener(this);
        txtClose = (TextView) findViewById(R.id.tv_page_close);
        txtClose.setOnClickListener(this);
        txtPageNum = (TextView) findViewById(R.id.tv_page_num);
        txtPageNum.setText(1 + "/" + total);
        txtTitle = (TextView) findViewById(R.id.tv_page_title);
        txtTitle.setText(mData.getTitle());


        List<Step> stepList = mData.getSteps();
        viewList = new ArrayList<>();
        for (int i = 0; i < total; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.item_viewpager, null);
            TextView txtTitle = (TextView) view.findViewById(R.id.tv_step_title);
            txtTitle.setText(stepList.get(i).getStep());
            NetworkImageView img = (NetworkImageView) view.findViewById(R.id.img_step);
            VolleyHttp.getInstance().displayImage(img, stepList.get(i).getImg(), R.drawable.login_box_bg4,
                    R.drawable.login_box_bg4);
            viewList.add(view);
        }
        viewPagerAdapter = new BasePageAdapter(viewList);
        mViewPager.setAdapter(viewPagerAdapter);

    }

    private void getIntentData() {
        mData = (Datum) getIntent().getSerializableExtra(Key.DETAILS);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_page_close:
                finish();
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        txtPageNum.setText(position + 1 + "/" + total);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
