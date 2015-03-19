package com.xjp.myapp.network;

import com.android.volley.VolleyError;

/**
 * Description:
 * User: xjp
 * Date: 2015/3/11
 * Time: 12:55
 */
public interface HttpResult<T> {
    public void onSuccess(T response);

    public void onFailed(VolleyError error);
}
