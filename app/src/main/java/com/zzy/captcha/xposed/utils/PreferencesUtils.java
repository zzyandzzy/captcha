package com.zzy.captcha.xposed.utils;

import com.zzy.captcha.utils.Utils;

import de.robv.android.xposed.XSharedPreferences;

/**
 * Created by zzyandzzy on 2017/1/12.
 */

public class PreferencesUtils {
    private static XSharedPreferences instance = null;

    private static XSharedPreferences getInstance() {
        if (instance == null) {
            instance = new XSharedPreferences(Utils.PackageName,"xposed");
            instance.makeWorldReadable();
        } else {
            instance.reload();
        }
        return instance;
    }

    public static String getString(String key,String defValue){
        return getInstance().getString(key,defValue);
    }

    public static Boolean getBoolean(String key,boolean defValue){
        return getInstance().getBoolean(key,defValue);
    }

    public static int getInt(String key,int defValue){
        return getInstance().getInt(key,defValue);
    }
}