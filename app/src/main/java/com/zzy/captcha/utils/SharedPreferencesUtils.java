package com.zzy.captcha.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreferencesUtils
{
	SharedPreferences sp;
	
	public SharedPreferencesUtils(Context context){
		sp = PreferenceManager.getDefaultSharedPreferences(context);
	}
	
	public void putInt(String key,int value){
		sp.edit().putInt(key,value).apply();
	}
	
	public int getInt(String key,int def){
		return sp.getInt(key,def);
	}
	
	public void putString(String key,String value){
		sp.edit().putString(key,value).apply();
	}
	
	public String getString(String key,String def){
		return sp.getString(key,def);
	}
	
	public void putBoolean(String key,boolean value){
		sp.edit().putBoolean(key,value).apply();
	}
	
	public boolean getBoolean(String key,boolean def){
		return sp.getBoolean(key,def);
	}
	
}
