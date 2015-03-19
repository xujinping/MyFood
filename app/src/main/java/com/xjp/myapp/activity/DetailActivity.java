package com.xjp.myapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.xjp.myapp.R;
import com.xjp.myapp.adapter.StepAdapter;
import com.xjp.myapp.beans.Index.Datum;
import com.xjp.myapp.network.VolleyHttp;
import com.xjp.myapp.utils.Key;
import com.xjp.myapp.widget.MyListView;

/**
 * User: xjp
 * Date: 2015/3/14
 * Time: 22:18
 */
public class DetailActivity extends BaseActivity implements View.OnClickListener {
    private TextView txtContent;
    private TextView txtBig;
    private NetworkImageView imgTop;
    private Datum mData;
    private MyListView myListView;
    private StepAdapter mAdapter;
    private LinearLayout linearLayout1;//材料
    private LinearLayout linearLayout2;//调料

    private String ingredients;//材料
    private String burden;//调料

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        loadData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        actionBar.setTitle(mData.getTitle());
    }

    protected void initView() {
        txtContent = (TextView) findViewById(R.id.tv_content);
        imgTop = (NetworkImageView) findViewById(R.id.img_top);
        myListView = (MyListView) findViewById(R.id.lv_setp);
        txtBig = (TextView) findViewById(R.id.tv_detail_big);
        txtBig.setOnClickListener(this);

        linearLayout1 = (LinearLayout) findViewById(R.id.ll_mtrls);
        linearLayout2 = (LinearLayout) findViewById(R.id.ll_burden);

    }

    protected void initData() {
        Intent intent = getIntent();
        mData = (Datum) intent.getSerializableExtra(Key.DETAILS);
        //加载当前菜谱的首页图片
        VolleyHttp.getInstance().displayImage(imgTop, mData.getAlbums().get(0),
                R.drawable.login_box_bg4, R.drawable.login_box_bg4);
        //显示用户个人心得或者评价信息
        txtContent.setText(mData.getImtro());

        //得到做菜的材料的数据
        ingredients = mData.getIngredients();
        //得到做菜的调料数据
        burden = mData.getBurden();

        String[] items1 = ingredients.split(";");
        addView(linearLayout1, items1);
        String[] items2 = burden.split(";");
        addView(linearLayout2, items2);
    }

    protected void loadData() {
        mAdapter = new StepAdapter(this);
        mAdapter.initAllData(mData.getSteps());
        myListView.setAdapter(mAdapter);
    }

    private int getLineNum(int len) {
        int line = len;
        if (0 == line % 2) {
            line = line / 2;
        } else {
            line = line / 2 + 1;
        }
        return line;
    }

    //动态添加菜谱详细信息里面的 用料和调料显示布局
    private void addView(LinearLayout layout, String items[]) {
        int len = items.length;
        int size = getLineNum(len);
        for (int i = 0, j = 0; i < size; i++, j++) {
            View view = LayoutInflater.from(this).inflate(R.layout.item_layout, null);
            LinearLayout llContent1 = (LinearLayout) view.findViewById(R.id.ll_layout1);
            TextView txtContent1 = (TextView) llContent1.getChildAt(0);
            TextView txtContent2 = (TextView) llContent1.getChildAt(1);
            txtContent1.setText(items[j].split(",")[0] + "：");
            txtContent2.setText(items[j].split(",")[1]);
            j++;
            if (j < len) {
                LinearLayout llContent2 = (LinearLayout) view.findViewById(R.id.ll_layout2);
                TextView txtContent3 = (TextView) llContent2.getChildAt(0);
                TextView txtContent4 = (TextView) llContent2.getChildAt(1);
                txtContent3.setText(items[j].split(",")[0] + "：");
                txtContent4.setText(items[j].split(",")[1]);
            }
            layout.addView(view);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_detail_big:
                startActivity(ImageBrowerActivity.class);
                break;
        }
    }

    protected void startActivity(Class<?> clszz) {
        Intent intent = new Intent(this, clszz);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Key.DETAILS, mData);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_detail;
    }
}
