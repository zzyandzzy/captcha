package com.zzy.materialsettinglibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by zzyandzzy on 2017/1/14.
 */

public class MaterialSettingSharedPreferences {
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private Context context;

    public MaterialSettingSharedPreferences(Context context){
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    public void putInt(String key,int value){
        editor.putInt(key,value).apply();
    }

    public int getInt(String key,int defValue){
        return sharedPreferences.getInt(key,defValue);
    }

    public void putString(String key,String value){
        editor.putString(key,value).apply();
    }

    public String getString(String key,String defValue){
        return sharedPreferences.getString(key,defValue);
    }

    public void putBoolean(String key,boolean value){
        editor.putBoolean(key,value).apply();
    }

    public boolean getBoolean(String key,boolean defValue){
        return sharedPreferences.getBoolean(key,defValue);
    }
}
