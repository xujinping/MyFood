package com.xjp.myapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;

import com.android.volley.VolleyError;
import com.xjp.myapp.R;
import com.xjp.myapp.adapter.CategoryAdapter;
import com.xjp.myapp.beans.Index.Datum;
import com.xjp.myapp.beans.Index.Index;
import com.xjp.myapp.utils.Key;
import com.xjp.myapp.utils.Urls;
import com.xjp.myapp.widget.XListView;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Description:
 * User: xjp
 * Date: 2015/3/11
 * Time: 16:53
 */
public class CategoryActivity extends BaseHttpActivity implements XListView.IXListViewListener, AdapterView.OnItemClickListener {
    private XListView mListView;
    private CategoryAdapter mAdapter;
    private String cid;
    private String category;
    private final static int PAGE = 15;
    private int loadNum = 0;
    private String url;
    private int totalNum = 0;
    private List<Datum> datumList;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_category);
        mListView = (XListView) findViewById(R.id.lv_category);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        cid = intent.getStringExtra(Key.CID);
        category = intent.getStringExtra(Key.CATEGORY);

        mListView.setOnItemClickListener(this);
        mListView.setPullRefreshEnable(true);
        mListView.setPullLoadEnable(true);
        mListView.setAutoLoadEnable(true);
        mListView.setXListViewListener(this);
        mListView.setRefreshTime(getTime());
        mAdapter = new CategoryAdapter(this);
        mListView.setAdapter(mAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        actionBar.setTitle(category);
    }

    /**
     * 获取时间格式
     *
     * @return
     */
    private String getTime() {
        return new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA).format(new Date());
    }

    @Override
    protected void loadData() {
        url = Urls.INDEX + cid + "&rn=" + PAGE + "&pn=";
        get(url, Index.class);
        showLog(url);
    }

    @Override
    protected void reLoad() {
        String strUrl = url + loadNum * PAGE;
        get(strUrl, Index.class);
    }

    @Override
    public void onSuccess(Object response) {
        if (null != response) {
            datumList = ((Index) response).getResult().getData();
            mAdapter.initAllData(datumList);
            totalNum = Integer.valueOf(((Index) response).getResult().getTotalNum());
        }
    }

    @Override
    public void onFailed(VolleyError error) {

    }

    @Override
    public void onRefresh() {//下拉刷新
        //下拉刷新前清除所有数据
        mAdapter.clearAllData();
        get(url, Index.class);
        onLoad();
    }

    private void onLoad() {
        mListView.stopRefresh();
        mListView.stopLoadMore();
        mListView.setRefreshTime(getTime());
    }


    @Override
    public void onLoadMore() { //上拉刷新
        loadNum++;
        if (loadNum * PAGE < totalNum) {
            String strUrl = url + loadNum * PAGE;
            get(strUrl, Index.class);
        } else {
            onLoad();
            mListView.setPullRefreshEnable(false);
            mListView.setPullLoadEnable(false);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(position);
    }

    private void startActivity(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Key.DETAILS, mAdapter.getAllData().get(position - 1));
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
