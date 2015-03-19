package com.xjp.myapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.xjp.myapp.R;
import com.xjp.myapp.View.ViewHolder;
import com.xjp.myapp.beans.Index.Datum;
import com.xjp.myapp.network.VolleyHttp;
import com.xjp.myapp.utils.MyLog;

/**
 * Description:
 * User: xjp
 * Date: 2015/3/13
 * Time: 13:52
 */
public class CategoryAdapter extends CustomBaseAdapter<Datum> {

    private Context context;

    public CategoryAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public View CustomHoldView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_detail_category, null);
        }
        NetworkImageView img = ViewHolder.get(convertView, R.id.img_item_category_food);
        TextView txtTitle = ViewHolder.get(convertView, R.id.tv_item_food_title);
        TextView txtTep = ViewHolder.get(convertView, R.id.tv_item_food_tep);
        String url = mList.get(position).getAlbums().get(0);
        VolleyHttp.getInstance().displayImage(img, url, R.drawable.ic_default, R.drawable.ic_default);
        txtTitle.setText(mList.get(position).getTitle());
        if (null != mList.get(position).getSteps()) {
            txtTep.setText(mList.get(position).getSteps().size() + "步");
        }
        return convertView;
    }
}
