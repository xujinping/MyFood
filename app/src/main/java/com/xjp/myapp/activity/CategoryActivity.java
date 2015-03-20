package com.xjp.myapp.activity;

import com.xjp.materiallibrary.drawerlayout.ActionBarDrawerToggle;
import com.xjp.materiallibrary.drawerlayout.DrawerArrowDrawable;
import com.xjp.myapp.R;
import com.xjp.myapp.base.BaseActivity;
import com.xjp.myapp.fragment.CategoryFragment;
import com.xjp.myapp.utils.Key;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:菜谱分类
 * User: xjp
 * Date: 2015/3/19
 * Time: 11:44
 */
public class CategoryActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private DrawerLayout mDrawerLayout;

    private ListView mListView;

    private DrawerArrowDrawable drawerArrow;

    private ActionBarDrawerToggle mDrawerToggle;

    private List<com.xjp.myapp.beans.Category.List> mList;

    private FragmentTransaction ft;

    private Fragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }


    /**
     * 初始化控件
     */
    private void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mListView = (ListView) findViewById(R.id.left_drawer);
        initActionBar();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mList = new ArrayList<>();
        Intent intent = getIntent();
        mList = (List<com.xjp.myapp.beans.Category.List>) intent.getSerializableExtra(Key.CATEGORY);
        int i = mList.size();
        String[] items = new String[i];
        for (int j = 0; j < i; j++) {
            items[j] = mList.get(j).getName();
        }
        mListView.setOnItemClickListener(this);
        mListView.setAdapter(
                new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,
                        items));
        replaceFragment(0);
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
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
        return R.layout.activity_category;
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        replaceFragment(position);
        if (mDrawerLayout.isDrawerOpen(mListView)) {
            mDrawerLayout.closeDrawer(mListView);
        }
    }

    /**
     * 替换Fragment内容
     * @param position
     */
    private void replaceFragment(int position) {
        fragment = new CategoryFragment();
        ft = getFragmentManager().beginTransaction();
        actionBar.setTitle(mList.get(position).getName());
        Bundle bundle = new Bundle();
        bundle.putString(Key.CID, mList.get(position).getId());
        fragment.setArguments(bundle);
        ft.replace(R.id.frame_content, fragment);
        ft.commit();
    }
}
