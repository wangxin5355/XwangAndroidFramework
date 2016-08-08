package com.example.xwang.xwangandroidframework.utils;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by yejiasun on 16/6/28.
 */
public class StringUtil {
    //    空字符串
    public static String EMPTY_STRING = "";
    public final static SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public final static SimpleDateFormat format2 = new SimpleDateFormat("HH:mm:ss");


    /**
     * 不为空 返回真
     *
     * @param str 字符串
     * @return 如果字符串不为空且长度大于1 返回真 ，其他返回假
     */
    public static boolean isNotBlank(String str) {
        return str != null && !str.trim().equals(EMPTY_STRING);
    }

    /**
     * 如果为空 返回真
     *
     * @param str 字符串
     * @return 如果为空或长度等于零，返回真，其他返回假
     */
    public static boolean isBlank(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * 是否是手机字符串
     *
     * @param str
     * @return
     */
    public static boolean isPhoneNumber(String str) {
        if (11 == str.length()) {
            return true;
        }
        return false;
//        Pattern p = Pattern.compile("^((\\+?86)|((\\+86)))?1\\d{10}$");
//        Matcher m = p.matcher(str);
//        return m.matches();
    }

    /**
     * 是否两回密码一致
     *
     * @return
     */
    public static boolean isMatch(String str1, String str2) {
        return (str1.equals(str2)) ? true : false;

    }

    /**
     * 是否在1-100个字符之间
     *
     * @param str
     * @return
     */
    public static boolean isFeedbackContent(String str) {
        int length = str.length();
        return ((0 < length) && (length < 100)) ? true : false;
    }

    /**
     * 隐藏字符串
     *
     * @param str
     * @return
     */
    public static String setPhone(String str) {
        if (isBlank(str)) {
            return EMPTY_STRING;
        }
        int length = str.length();
        return str.substring(0, 2) + "***" + str.substring(length - 3, length - 1);
    }

    public static String MD5(String str) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

        char[] charArray = str.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);

        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString().toUpperCase();
    }

    /**
     * 改变时区
     */
    public static Date changeTimeZone(Date date, TimeZone oldZone, TimeZone newZone) {
        Date dateTmp = null;
        if (date != null) {
            int timeOffset = oldZone.getRawOffset() - newZone.getRawOffset();
            dateTmp = new Date(date.getTime() - timeOffset);
        }
        return dateTmp;
    }

    /**
     * 设置时间格式\/Date(1466574140000+0800)\/
     */
    public static String setTime(String date) {
        if (date == null) {
            return null;
        }
        date = date.replace("/Date(", "").replace("+0800)", "").replace("\\", "").replace("/", "");
        Long longDate = Long.valueOf(date);
        Date time = null;
        time = new Date(longDate);
        return format2.format(time);
    }
}
