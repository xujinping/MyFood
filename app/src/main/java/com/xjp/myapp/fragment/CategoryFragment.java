package com.xjp.myapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.android.volley.VolleyError;
import com.xjp.myapp.R;
import com.xjp.myapp.activity.DetailActivity;
import com.xjp.myapp.adapter.CategoryAdapter;
import com.xjp.myapp.beans.Index.Datum;
import com.xjp.myapp.beans.Index.Index;
import com.xjp.myapp.utils.Key;
import com.xjp.myapp.utils.Urls;
import com.xjp.myapp.widget.XListView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Description:
 * User: xjp
 * Date: 2015/3/19
 * Time: 11:24
 */
public class CategoryFragment extends BaseHttpFragment implements AdapterView.OnItemClickListener,
        XListView.IXListViewListener {
    private String cid;
    private final static int PAGE = 15;
    private int loadNum = 0;
    private String url;
    private int totalNum = 0;
    private List<Datum> datumList;
    private XListView mListView;
    private CategoryAdapter mAdapter;

    @Override
    protected void reLoad() {
        String strUrl = url + loadNum * PAGE;
        get(strUrl, Index.class);
    }

    @Override
    protected void loadData() {
        url = Urls.INDEX + cid + "&rn=" + PAGE + "&pn=";
        get(url, Index.class);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_category, null);
        mListView = (XListView) view.findViewById(R.id.lv_category);
        mListView.setOnItemClickListener(this);
        mListView.setPullRefreshEnable(true);
        mListView.setPullLoadEnable(true);
        mListView.setAutoLoadEnable(true);
        mListView.setXListViewListener(this);
        mListView.setRefreshTime(getTime());
        mAdapter = new CategoryAdapter(mActivity);
        mListView.setAdapter(mAdapter);

        return view;
    }

    @Override
    protected void getBundle() {
        cid = getArguments().getString(Key.CID);
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
    public void onRefresh() {
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
        Intent intent = new Intent(mActivity, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Key.DETAILS, mAdapter.getAllData().get(position - 1));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 获取时间格式
     *
     * @return
     */
    private String getTime() {
        return new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA).format(new Date());
    }
}
