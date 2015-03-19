package com.xjp.myapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xjp.myapp.R;
import com.xjp.myapp.beans.Category.Result;
import com.xjp.myapp.View.ViewHolder;

/**
 * Description:
 * User: xjp
 * Date: 2015/3/11
 * Time: 13:34
 */
public class MainAdapter extends CustomBaseAdapter<Result> {

    private Context mContext;
    private int imgId[] = {
            R.drawable.lm1_1, R.drawable.lm1_2, R.drawable.lm1_3,
            R.drawable.lm1_4, R.drawable.lm1_5, R.drawable.lm1_6,
            R.drawable.lm1_7, R.drawable.lm2_1, R.drawable.lm2_2,
            R.drawable.lm2_3, R.drawable.lm2_4, R.drawable.lm2_5,
            R.drawable.lm2_6, R.drawable.lm2_7, R.drawable.lm2_8,
            R.drawable.lm3_1, R.drawable.lm3_2, R.drawable.lm3_3,
            R.drawable.lm3_4, R.drawable.lm4_1, R.drawable.lm4_2,
            R.drawable.lm4_3, R.drawable.lm4_4, R.drawable.lm4_5,
            R.drawable.lm4_6, R.drawable.lm4_7, R.drawable.lm4_8,
            R.drawable.lm5_1, R.drawable.lm5_3, R.drawable.lm5_3};

    public MainAdapter(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public int getCount() {
        int i = super.getCount();
        if (i > imgId.length) {
            i = imgId.length;
        }
        return i;
    }

    @Override
    public View CustomHoldView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_main_category, null);

        }
        ImageView imgCategory = ViewHolder.get(convertView, R.id.img_item_category);
        TextView txtTitle = ViewHolder.get(convertView, R.id.tv_item_title);
        imgCategory.setImageResource(imgId[position]);
        txtTitle.setText(mList.get(position).getName());
        return convertView;
    }

}
