package com.example.xwang.xwangandroidframework.activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xwang.xwangandroidframework.R;
import com.example.xwang.xwangandroidframework.WXAndroidFW;
import com.example.xwang.xwangandroidframework.constant.Constant;
import com.example.xwang.xwangandroidframework.model.Configure;
import com.example.xwang.xwangandroidframework.utils.ActivityUtil;
import com.example.xwang.xwangandroidframework.utils.JPushUtil;
import com.example.xwang.xwangandroidframework.utils.StringUtil;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public abstract class BaseActivity extends FragmentActivity implements IBaseActivity {
    private static final String TAG = "BaseActivity";
    protected Context mContext;
    protected Context appContext;
    protected WXAndroidFW mApplication;
    protected ProgressDialog mProgressDialog;
    protected FragmentManager mFragmentMan;
    protected SharedPreferences mSharedPreferences;
    private DisplayMetrics dm;
    protected LayoutInflater mInflater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mSharedPreferences = getSharedPreferences(Constant.SHAREDPREFERENCES_FILE, Context.MODE_PRIVATE);
        appContext = getBaseContext();
        mApplication = (WXAndroidFW) getApplication();
        mFragmentMan = getSupportFragmentManager();
        ActivityUtil.addActivity(this);
        dm = getResources().getDisplayMetrics();
        mInflater = (LayoutInflater) appContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public void saveConfig(Configure config) {
        mSharedPreferences.edit().putString(Constant.EXTRA_USER_EMAIL, config.getEmail()).commit();
        mSharedPreferences.edit().putString(Constant.EXTRA_USER_USER_NAME, config.getUserName()).commit();
        mSharedPreferences.edit().putString(Constant.EXTRA_USER_QQ, config.getQq()).commit();
        mSharedPreferences.edit().putString(Constant.EXTRA_USER_REAL_NAME, config.getRealName()).commit();
        mSharedPreferences.edit().putString(Constant.EXTRA_USER_TELPHONE, config.getTelphone()).commit();
        mSharedPreferences.edit().putString(Constant.EXTRA_USER_TOKENID, config.getTokenID()).commit();
        mSharedPreferences.edit().putString(Constant.EXTRA_MODEL_NAME, config.getModelName()).commit();
    }

    @Override
    public void clearConfig() {
        Configure configure = getConfig();
        configure.setEmail(null);
        configure.setUserName(null);
        configure.setQq(null);
        configure.setRealName(null);
        configure.setTelphone(null);
        configure.setTokenID(null);
        configure.setModelName(null);
        saveConfig(configure);

    }

    @Override
    public Configure getConfig() {
        Configure configure = new Configure();
        configure.setEmail(mSharedPreferences.getString(Constant.EXTRA_USER_EMAIL, null));
        configure.setUserName(mSharedPreferences.getString(Constant.EXTRA_USER_USER_NAME, null));
        configure.setQq(mSharedPreferences.getString(Constant.EXTRA_USER_QQ, null));
        configure.setRealName(mSharedPreferences.getString(Constant.EXTRA_USER_REAL_NAME, null));
        configure.setTelphone(mSharedPreferences.getString(Constant.EXTRA_USER_TELPHONE, null));
        configure.setTokenID(mSharedPreferences.getString(Constant.EXTRA_USER_TOKENID, null));
        configure.setModelName(mSharedPreferences.getString(Constant.EXTRA_MODEL_NAME, null));
        return configure;
    }

    public SharedPreferences getSharePreferences() {
        return mSharedPreferences;
    }

    /**
     * 清除缓存信息
     */
    public void clearSharePreferences() {
        Editor mEditor = mSharedPreferences.edit();
        mEditor.clear();
        mEditor.commit();
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public int getWinWidth() {
        return dm.widthPixels;
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public String getVersion() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public String getVersionNum() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            String versionNum = info.versionCode + "";
            return versionNum;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    //***** dialog start *****//

    /**
     * 开启加载进行中动画
     */
    public void showProgressdialog() {
        showProgressdialog(R.string.dialog_loading);
    }

    public void showProgressdialog(int strResId) {
        showProgressdialog(getResources().getString(strResId));
    }

    public void showProgressdialog(String msg) {
        showProgressDialog("", msg);
    }

    /**
     * 开启动画
     *
     * @param s
     * @param msg
     */
    public void showProgressDialog(String s, String msg) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this, R.style.MyProgressDialog);
        }
        if (!isFinishing()) {
            try {
                mProgressDialog.show();
            } catch (Exception e) {
                Log.e(TAG, "", e);
            }
        }
        mProgressDialog.setContentView(R.layout.progressdialog_custom);
        TextView textView = (TextView) mProgressDialog.findViewById(R.id.tv_loading_msg);
        mProgressDialog.setCanceledOnTouchOutside(false);
        textView.setText(msg);
    }


    /**
     * 关闭加载进行中动画
     */
    public void dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
    //***** dialog end *****//


    /**
     * * 校验网络-如果有网络返回true.
     *
     * @return
     */
    @Override
    public boolean hasInternetConnected() {
        ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(mContext
                .CONNECTIVITY_SERVICE);
        if (manager != null) {
            NetworkInfo network = manager.getActiveNetworkInfo();
            if (network != null && network.isConnectedOrConnecting()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 请求失败
     * 检查网络或提示远程服务器错误
     */
    public void cluesHttpFail() {
        if (!hasInternetConnected()) {
            showToast(getString(R.string.err_net));
        } else {
            showToast(getString(R.string.err_svc));
        }
    }

    public void handleRsCode(String rs, String messaage) {
        if ("0".equals(rs)) {
            switch (messaage) {
                case "token expired":
                    showToast(R.string.err_token_expired);
                    clearSharePreferences();
                    //startActivity(new Intent(this, LoginAndRegActicity.class)); TODO启动页
                    break;
                case "login fail , wrong password or mobile":
                    showToast(R.string.err_phone_or_pwd);
                    break;
                default:
                    break;
            }

        }
    }

    public String getStringXml(int id) {
        return getString(id);
    }

    /**
     * 显示提示信息
     *
     * @param
     */
    @Override
    public void showToast(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void showToast(int resId) {
        this.showToast(getString(resId));
    }


    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化控件监听
     */
    protected abstract void initListener();


    /**
     * 退出
     */
    @Override
    public void isExit() {
        this.finish();
    }


    /**
     * 双击退出
     */
    private static Boolean isExit = false;

    public void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, getStringXml(R.string.toast_double_out), Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            ActivityUtil.exit();
            System.exit(0);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(mContext.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    /**
     * 关闭软键盘相关
     *
     * @param v
     * @param event
     * @return
     */
    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public void toActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @Override
    public void toActivityForResult(Class<?> cls, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void toActivityClearAll(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        this.finish();
    }

    @Override
    public Bundle getBundle() {
        return getIntent().getExtras();
    }


    public void setTag() {
        // 检查 tag 的有效性
        // ","隔开的多个 转换成 Set
//        String[] sArray = "xuangu3,xuangu2,xuangu4,xuangu5,test2,xuangu1,xuangu6,DualMA,turtleStock".split(",");
        String[] sArray = getConfig().getModelName().replace("[", "").replace("]", "").replace(" ", "").split(",");
        Set<String> tagSet = new LinkedHashSet<>();
        for (String sTagItme : sArray) {
            if (!JPushUtil.isValidTagAndAlias(sTagItme)) {
                return;
            }
            tagSet.add(sTagItme);
        }
        //调用JPush API设置Tag
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_TAGS, tagSet));

    }


    private final TagAliasCallback mTagsCallback = new TagAliasCallback() {

        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    Log.i(TAG, logs);
                    break;

                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    Log.i(TAG, logs);
                    if (JPushUtil.isConnected(getApplicationContext())) {
                        mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_TAGS, tags), 1000 * 60);
                    } else {
                        Log.i(TAG, "No network");
                    }
                    break;

                default:
                    logs = "Failed with errorCode = " + code;
                    Log.e(TAG, logs);
            }

        }

    };
    private static final int MSG_SET_TAGS = 1002;


    /**
     * 注册tag到jpush
     */
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_TAGS:
                    Log.d(TAG, "Set tags in handler.");
                    JPushInterface.setAliasAndTags(getApplicationContext(), null, (Set<String>) msg.obj, mTagsCallback);
                    break;

                default:
                    Log.i(TAG, "Unhandled msg - " + msg.what);
            }
        }
    };

    /**
     * 检查是否已经登录
     */
    public boolean isLogin() {
        if (StringUtil.isNotBlank(getConfig().getUserName())) {
            return true;
        }
        return false;
    }


}