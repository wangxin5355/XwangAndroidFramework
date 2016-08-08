package com.example.xwang.xwangandroidframework.activity;


import android.os.Bundle;

import com.example.xwang.xwangandroidframework.model.Configure;


public interface IBaseActivity {
    /**
     * save userInfos
     */
    public void saveConfig(Configure config);

    /**
     * 清空列表界面
     */
    public void clearConfig();


    /**
     * 跳转界面
     */
    public void toActivity(Class<?> cls, Bundle bundle);

    /**
     * 跳转界面
     */
    public void toActivityClearAll(Class<?> cls);


    /***
     * get userInfo
     */
    public Configure getConfig();


    /**
     * 校验网络-如果没有网络就返回true.
     *
     * @return
     */
    public abstract boolean hasInternetConnected();

    /**
     * 短时间显示toast.
     *
     * @param text
     */
    public abstract void showToast(String text);

    /**
     * 退出应用.
     */
    public abstract void isExit();

    /**
     * 跳转后返回
     */
    public void toActivityForResult(Class<?> cls, int requestCode, Bundle bundle);

    public Bundle getBundle();
}
