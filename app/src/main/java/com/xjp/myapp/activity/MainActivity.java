package com.xjp.myapp.activity;

import com.android.volley.VolleyError;
import com.xjp.materiallibrary.drawerlayout.ActionBarDrawerToggle;
import com.xjp.materiallibrary.drawerlayout.DrawerArrowDrawable;
import com.xjp.myapp.R;
import com.xjp.myapp.adapter.MainAdapter;
import com.xjp.myapp.adapter.MainDrawerAdapter;
import com.xjp.myapp.base.BaseHttpActivity;
import com.xjp.myapp.beans.Category.Category;
import com.xjp.myapp.beans.Category.Result;
import com.xjp.myapp.utils.ShowDialog;
import com.xjp.myapp.utils.Urls;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseHttpActivity implements AdapterView.OnItemClickListener {

    //侧滑菜单
    private DrawerLayout mDrawerLayout;

    private GridView mGridView;

    private MainAdapter mCategotyAdapter;

    private List<Result> resultList;

    private ListView mListView;

    private DrawerArrowDrawable drawerArrow;

    private ActionBarDrawerToggle mDrawerToggle;

    private MainDrawerAdapter mainDrawerAdapter;

    private List<String> listData;

    @Override
    protected void initView() {
        mGridView = (GridView) findViewById(R.id.gv_category);
        mListView = (ListView) findViewById(R.id.left_drawer);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        View headerView = LayoutInflater.from(this).inflate(R.layout.item_main_header, null);
        mListView.addHeaderView(headerView);
    }

    /**
     * 初始化导航栏成为 andorid5.0风格。
     */
    private void initActionBar() {
        drawerArrow = new DrawerArrowDrawable(this) {
            @Override
            public boolean isLayoutRtl() {
                return false;
            }
        };
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                drawerArrow, R.string.drawer_open,
                R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    @Override
    protected void initData() {
        mainDrawerAdapter = new MainDrawerAdapter(this);
        listData = new ArrayList<>();
        listData.add("设置");
        listData.add("分享");
        mainDrawerAdapter.initAllData(listData);
        mListView.setAdapter(mainDrawerAdapter);
        mListView.setOnItemClickListener(this);

        mCategotyAdapter = new MainAdapter(this);
        mCategotyAdapter.initAllData(null);
        mGridView.setAdapter(mCategotyAdapter);
        mGridView.setOnItemClickListener(this);
        initActionBar();
    }

    @Override
    protected void loadData() {
        get(Urls.ALL_CATEGORY, Category.class);
    }

    @Override
    protected void reLoad() {
        this.loadData();
    }


    @Override
    public void onSuccess(Object response) {
        resultList = ((Category) response).getResult();
        mCategotyAdapter.initAllData(resultList);
    }

    @Override
    public void onFailed(VolleyError error) {
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.left_drawer:
                showToast("等待接口实现");
                break;
            case R.id.gv_category:
                Result result = resultList.get(position);
                //弹出分类的对话框
                ShowDialog.showCategoryDialog(MainActivity.this, result);
                break;
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (mDrawerLayout.isDrawerOpen(mListView)) {
                mDrawerLayout.closeDrawer(mListView);
            } else {
                mDrawerLayout.openDrawer(mListView);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

}
