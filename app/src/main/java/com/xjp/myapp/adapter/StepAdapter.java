package com.xjp.myapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.xjp.myapp.R;
import com.xjp.myapp.View.ViewHolder;
import com.xjp.myapp.beans.Index.Step;
import com.xjp.myapp.network.VolleyHttp;

import org.w3c.dom.Text;

/**
 * User: xjp
 * Date: 2015/3/15
 * Time: 0:51
 */
public class StepAdapter extends CustomBaseAdapter<Step> {
    private Context context;

    public StepAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public View CustomHoldView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_detail_step, null);
        }

        TextView txtStepNum = ViewHolder.get(convertView,R.id.tv_item_step_num);
        TextView txtStepContent = ViewHolder.get(convertView,R.id.tv_item_step_content);
        NetworkImageView imgStep = ViewHolder.get(convertView,R.id.img_step_list);
        txtStepNum.setText(position + 1 + " ");
        txtStepContent.setText(mList.get(position).getStep());
        VolleyHttp.getInstance().displayImage(imgStep, mList.get(position).getImg(), R.drawable.ic_default, R.drawable.ic_default);

        return convertView;
    }
}
