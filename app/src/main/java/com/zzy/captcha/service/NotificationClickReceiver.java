package com.zzy.captcha.service;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.zzy.captcha.utils.CopyCaptchaUtila;
import com.zzy.captcha.utils.Utils;
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
            notificationManager.cancel(Utils.getNotificationId());
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N){
            message = xposedPreferencesUtils.getString("smstest", Utils.SmsTest);
            regex = xposedPreferencesUtils.getString("smsRegex", Utils.SmsRegex);
            copytext = xposedPreferencesUtils.getString("copytext", Utils.CpoyText);
            keyword = xposedPreferencesUtils.getString("keyword", Utils.Keyword);
            tigger = xposedPreferencesUtils.getString("tigger", Utils.TiggerRegex);
        }else {
            message = sharedPreferencesUtils.getString("smstest", Utils.SmsTest);
            regex = sharedPreferencesUtils.getString("smsRegex", Utils.SmsRegex);
            copytext = sharedPreferencesUtils.getString("copytext", Utils.CpoyText);
            keyword = sharedPreferencesUtils.getString("keyword", Utils.Keyword);
            tigger = sharedPreferencesUtils.getString("tigger", Utils.TiggerRegex);
        }
        CopyCaptchaUtila.CopyCptcha(context,message, regex,keyword,tigger,copytext);
    }
}
