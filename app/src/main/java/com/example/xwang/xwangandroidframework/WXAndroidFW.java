package com.example.xwang.xwangandroidframework;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.backends.pipeline.Fresco;

import okhttp3.http.OkHttpStack;


/**
 * Created by wangxin on 2016/7/10.
 */
public class WXAndroidFW extends Application {
    private RequestQueue mRequestQueue;
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(getApplicationContext());
        //JPushInterface.setDebugMode(true);
        //JPushInterface.init(this);
    }

    public RequestQueue getVolleyRequestQueue() {
        if (mRequestQueue == null)
        {
            //一个应用只需要创建一个requestQueue，可以将它在apllication中实例化。
            mRequestQueue = Volley.newRequestQueue(this, new OkHttpStack());
        }

        return mRequestQueue;
    }

}
