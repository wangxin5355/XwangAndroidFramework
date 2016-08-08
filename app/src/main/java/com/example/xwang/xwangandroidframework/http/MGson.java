package com.example.xwang.xwangandroidframework.http;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

/**
 * Created by dongdongzheng on 2016/3/8.
 */
public class MGson {

    public static Object fromJson(Type type, String object, Context mContext) {
        try {
            return new Gson().fromJson(object.toString(), type);
        } catch (JsonSyntaxException e) {
            Log.e("Gson转码错误", "", e);
            Toast toast = Toast.makeText(mContext, "服务器返回数据格式错误", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return null;
        } catch (Exception e) {
            // TODO: handle exception
            Log.e("Gson转码其他错误", "", e);
            return null;
        }
    }

    public static Object fromJson(String object, Type type, Context mContext) {
        try {
            return new Gson().fromJson(object.toString(), type);
        } catch (JsonSyntaxException e) {
            Log.e("Gson转码错误", "", e);
            Toast toast = Toast.makeText(mContext, "服务器返回数据格式错误", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return null;
        } catch (Exception e) {
            // TODO: handle exception
            Log.e("Gson转码其他错误", "", e);
            return null;
        }
    }
}
