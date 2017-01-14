package com.zzy.captcha.service;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.zzy.captcha.utils.CopyCaptchaUtila;
import com.zzy.captcha.utils.RegexUtils;
import com.zzy.captcha.utils.SharedPreferencesUtils;
import com.zzy.captcha.utils.XposedPreferencesUtils;

/**
 * Created by zzyandzzy on 2017/1/11.
 */

public class NotificationClickReceiver extends BroadcastReceiver {
    private NotificationManager notificationManager;
    private XposedPreferencesUtils xposedPreferencesUtils;
    private SharedPreferencesUtils sharedPreferencesUtils;
    private String message,regex,copytext,tigger,keyword;
    @Override
    public void onReceive(Context context, Intent intent) {
        xposedPreferencesUtils = new XposedPreferencesUtils(context);
        sharedPreferencesUtils = new SharedPreferencesUtils(context);
        notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null)
            notificationManager.cancel(RegexUtils.getNotificationId());
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N){
            message = xposedPreferencesUtils.getString("smstest", RegexUtils.SmsTest);
            regex = xposedPreferencesUtils.getString("smsRegex", RegexUtils.SmsRegex);
            copytext = xposedPreferencesUtils.getString("copytext",RegexUtils.CpoyText);
            keyword = xposedPreferencesUtils.getString("keyword",RegexUtils.Keyword);
            tigger = xposedPreferencesUtils.getString("tigger",RegexUtils.TiggerRegex);
        }else {
            message = sharedPreferencesUtils.getString("smstest", RegexUtils.SmsTest);
            regex = sharedPreferencesUtils.getString("smsRegex", RegexUtils.SmsRegex);
            copytext = sharedPreferencesUtils.getString("copytext",RegexUtils.CpoyText);
            keyword = sharedPreferencesUtils.getString("keyword",RegexUtils.Keyword);
            tigger = sharedPreferencesUtils.getString("tigger",RegexUtils.TiggerRegex);
        }
        CopyCaptchaUtila.CopyCptcha(context,message, regex,keyword,tigger,copytext);
    }
}
