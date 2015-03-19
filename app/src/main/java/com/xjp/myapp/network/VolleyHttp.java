package com.xjp.myapp.network;

import android.app.Dialog;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.mytools.ImageCache;
import com.android.volley.toolbox.FastJsonRequest;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.xjp.myapp.R;
import com.xjp.myapp.application.MyApplication;
import com.xjp.myapp.utils.MyLog;

import static com.android.volley.Response.ErrorListener;

/**
 * Description:
 * User: xjp
 * Date: 2015/3/11
 * Time: 9:11
 */
public class VolleyHttp {
    public RequestQueue mQueue;
    public ImageLoader imageLoader;
    private Object object = this;
    public static VolleyHttp instance = null;


    public static synchronized VolleyHttp getInstance() {
        if (null == instance) {
            instance = new VolleyHttp();
        }
        return instance;
    }

    private VolleyHttp() {
        mQueue = Volley.newRequestQueue(MyApplication.instance);
        imageLoader = new ImageLoader(mQueue, new ImageCache());
    }

    public void displayImage(NetworkImageView imageView, String strImgUrl,
                             int defaultImageResId, int failedImageResId) {
        imageView.setDefaultImageResId(defaultImageResId);
        imageView.setErrorImageResId(failedImageResId);
        imageView.setImageUrl(strImgUrl, imageLoader);
    }


    public <T> void get(String url, final HttpResult httpResult, Class<T> clazz,
                        final Dialog dialog, final AnimationDrawable drawable) {
        FastJsonRequest<T> fastJsonRequest = new FastJsonRequest<T>(url, null, null, clazz, new Response.Listener<T>() {
            @Override
            public void onResponse(T response) {
                if (null != dialog && dialog.isShowing()) {
                    dialog.dismiss();
                }
                httpResult.onSuccess(response);
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (null != drawable && drawable.isRunning()) {
                    drawable.stop();
                    dialog.findViewById(R.id.btn_reload).setVisibility(View.VISIBLE);
                    ((TextView) dialog.findViewById(R.id.tv_loading_tip)).setText(R.string.load_failed);
                }
                httpResult.onFailed(error);
            }
        });
        mQueue.add(fastJsonRequest);
    }

}
