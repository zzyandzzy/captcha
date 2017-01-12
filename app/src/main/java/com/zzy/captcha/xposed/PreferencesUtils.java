package com.zzy.captcha.xposed;

import com.zzy.captcha.utils.RegexUtils;

import de.robv.android.xposed.XSharedPreferences;

/**
 * Created by zzyandzzy on 2017/1/12.
 */

public class PreferencesUtils {
    private static XSharedPreferences instance = null;

    private static XSharedPreferences getInstance() {
        if (instance == null) {
            instance = new XSharedPreferences(RegexUtils.getPackageName(),"xposed");
            instance.makeWorldReadable();
        } else {
            instance.reload();
        }
        return instance;
    }

    public static String getText(){
        return getInstance().getString("systemuitext"," i GDQ");
    }

}