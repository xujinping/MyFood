package com.xjp.myapp.adapter;

import com.xjp.myapp.R;
import com.xjp.myapp.widget.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Description:主页侧滑菜单List适配器
 * User: xjp
 * Date: 2015/3/18
 * Time: 15:48
 */
public class MainDrawerAdapter extends CustomBaseAdapter<String> {

    private int imgId[] = {R.drawable.settings_icon, R.drawable.share_icon};

    private Context context;

    public MainDrawerAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public View CustomHoldView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_mian_lsit, null);
        }
        TextView txtTitel = ViewHolder.get(convertView, R.id.tv_item_main_list);
        txtTitel.setText(mList.get(position));
        ImageView imgTitle = ViewHolder.get(convertView, R.id.img_item_mian_list);
        imgTitle.setImageResource(imgId[position]);
        return convertView;
    }
}
