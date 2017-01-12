package com.zzy.captcha.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

/**
 * Created by zzyandzzy on 2017/1/12.
 */

public class XposedPreferencesUtils {
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    //private File xml;

    public XposedPreferencesUtils(Context context){
        this.context = context;
        //xml = new File("/data/data/"+RegexUtils.getPackageName()+"/shared_prefs/"+"xposed.xml");
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N){
            sharedPreferences = context.getSharedPreferences("xposed",Context.MODE_WORLD_READABLE);
            editor = sharedPreferences.edit();
        }
    }
     public void putString(String key,String value){
         editor.putString(key,value);
         editor.apply();
     }

    public String getString(String key,String defValue){
        return sharedPreferences.getString(key,defValue);
    }
}
