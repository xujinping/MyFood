package com.xjp.myapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xjp
 * Date: 2015/3/8
 * Time: 11:48
 */
public abstract class CustomBaseAdapter<T> extends BaseAdapter {

    protected List<T> mList;
    private Context mContext;

    public CustomBaseAdapter(Context context) {
        mList = new ArrayList<>();
        this.mContext = context;
    }

    /**
     * 初始化适配器数据
     *
     * @param list
     */
    public void initAllData(List<T> list) {
        if (null != mList) {
            addDataAtEnd(list);
        }
    }

    public List<T> getAllData() {
        return mList;
    }

    /**
     * 在末尾追加数据
     *
     * @param list
     */
    public void addDataAtEnd(List<T> list) {
        if (null != list) {
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }

    /**
     * 清除所有数据
     */
    public void clearAllData() {
        if (null != mList) {
            mList.clear();
            notifyDataSetChanged();
        }
    }

    /**
     * 从头部添加数据
     *
     * @param list
     */
    public void addDataAtStart(List<T> list) {
        mList.addAll(0, list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList == null ? null : mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return CustomHoldView(position, convertView, parent);
    }

    public abstract View CustomHoldView(int position, View convertView, ViewGroup parent);
}
