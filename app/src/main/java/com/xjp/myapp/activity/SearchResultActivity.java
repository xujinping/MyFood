package com.xjp.myapp.activity;

import com.android.volley.VolleyError;
import com.xjp.myapp.R;
import com.xjp.myapp.adapter.CategoryAdapter;
import com.xjp.myapp.base.BaseHttpActivity;
import com.xjp.myapp.beans.Index.Datum;
import com.xjp.myapp.beans.Index.Index;
import com.xjp.myapp.beans.Index.Result;
import com.xjp.myapp.utils.Key;
import com.xjp.myapp.utils.Urls;
import com.xjp.myapp.widget.XListView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Description:菜谱搜索结果页面
 * User: xjp
 * Date: 2015/3/11
 * Time: 16:53
 */
public class SearchResultActivity extends BaseHttpActivity
        implements XListView.IXListViewListener, AdapterView.OnItemClickListener {

    private XListView mListView;

    private CategoryAdapter mAdapter;

    private String name;

    //每一页请求的数据个数
    private final static int PAGE = 15;

    //向服务器请求数据的次数
    private int loadNum = 0;

    //请求数据的url
    private String url;

    private int totalNum = 0;

    //请求的结果
    private List<Datum> datumList;

    private TextView txtTips;

    @Override
    protected void initView() {
        mListView = (XListView) findViewById(R.id.lv_category);
        txtTips = (TextView) findViewById(R.id.tv_tips);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        name = intent.getStringExtra(Key.NAME);
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
        actionBar.setTitle(R.string.search_result);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_search_result;
    }

    /**
     * 获取时间格式
     */
    private String getTime() {
        return new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA).format(new Date());
    }

    @Override
    protected void loadData() {
        //将中文编码，
        String name1 = "";
        try {
            name1 = URLEncoder.encode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        url = Urls.QUERY_NAME + name1 + "&rn=" + PAGE + "&pn=";
        get(url, Index.class);
    }

    @Override
    protected void reLoad() {
        String strUrl = url + loadNum * PAGE;
        get(strUrl, Index.class);
    }

    @Override
    public void onSuccess(Object response) {
        if (null != response) {
            Result result = ((Index) response).getResult();
            if (null == result) {//未搜索到相应菜谱信息
                txtTips.setVisibility(View.VISIBLE);
                mListView.setVisibility(View.GONE);
            } else {//显示相应的菜谱信息
                datumList = result.getData();
                mAdapter.initAllData(datumList);
                totalNum = Integer.valueOf(result.getTotalNum());
            }
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

    /**
     * 跳转到对应位置的activity
     */
    private void startActivity(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Key.DETAILS, mAdapter.getAllData().get(position - 1));
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
