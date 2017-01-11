package com.zzy.captcha.utils;

import android.content.Context;

import com.zzy.captcha.R;

/**
 * Created by zzyandzzy on 2017/1/10.
 */

public class RegexUtils {
    public static String getSmsRegex(){
        return "(?<![0-9])([0-9]{4,10})(?![09])";
    }
    public static String getKeywordRegex(Context context){
        return context.getResources().getString(R.string.keyword);
    }
    public static String getTiggerRegex(Context context){
        return context.getResources().getString(R.string.tigger);
    }
    public static String getCopyText(Context context){
        return context.getResources().getString(R.string.copytext);
    }
    public static String getSmsTest(Context context){
        return context.getResources().getString(R.string.smstest);
    }
    public static String getExplain(Context context){
        return context.getResources().getString(R.string.explain);
    }
    public static int getNotificationId(){
        return 100;
    }
}
