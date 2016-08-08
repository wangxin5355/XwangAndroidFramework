package com.example.xwang.xwangandroidframework.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 用作收集和销毁activity
 * 
 * @author Fly Chen
 */
public class ActivityUtil {

  public static List<Activity> activities = new ArrayList<Activity>();

  /**
   * 加入Activity
   */
  public static void addActivity(Activity activity) {
    activities.add(activity);
  }

  /**
   * 移除Activity
   */
  public static void removeActivity(Activity activity) {
    activities.remove(activity);
  }

  /**
   * 销毁其它Activity,只保留当前
   */
  public static void killPreActivities() {
    if (activities != null && activities.size() > 0) {
      if (!activities.get(0).isFinishing()) {
        activities.get(0).finish();
      }
    }
  }
  /**
   *退出是销毁所有activity
   */
  public static void exit(){
	  for(Activity activity:activities){
		  activity.finish();
	  }
  }

}
