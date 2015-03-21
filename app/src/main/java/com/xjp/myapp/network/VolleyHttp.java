package com.xjp.myapp.network;

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

import android.app.Dialog;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.TextView;

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

    /**
     * 加载网络图片
     * @param imageView
     * @param strImgUrl
     * @param defaultImageResId
     * @param failedImageResId
     */
    public void displayImage(NetworkImageView imageView, String strImgUrl,
                             int defaultImageResId, int failedImageResId) {
        imageView.setDefaultImageResId(defaultImageResId);
        imageView.setErrorImageResId(failedImageResId);
        imageView.setImageUrl(strImgUrl, imageLoader);
    }


    /**
     * 网络json数据请求和解析
     * @param url
     * @param httpResult
     * @param clazz
     * @param dialog
     * @param drawable
     * @param <T>
     */
    public <T> void get(final String url, final HttpResult httpResult, Class<T> clazz,
                        final Dialog dialog, final AnimationDrawable drawable) {
        MyLog.e("VolleyHttp", url);
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
        fastJsonRequest.setTag("tag");
        mQueue.add(fastJsonRequest);
    }

    /**
     * TODO<取消所有含有Object tag标记的请求,退出线程循环>
     *
     * @throw
     * @return void
     * @param
     */
    public void cancelAll() {
        mQueue.cancelAll("tag");
        mQueue.stop();
    }

}
