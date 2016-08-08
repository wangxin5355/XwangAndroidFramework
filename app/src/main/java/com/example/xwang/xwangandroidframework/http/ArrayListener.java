package com.example.xwang.xwangandroidframework.http;

/**
 * Created by dongdongzheng on 2016/3/8.
 */
public interface ArrayListener {

    void onStart();

    void onResponse(String response);

    void onError(String message);
}
