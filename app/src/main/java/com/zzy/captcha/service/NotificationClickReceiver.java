package com.zzy.captcha.service;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.zzy.captcha.utils.CopyCaptchaUtila;
import com.zzy.captcha.utils.RegexUtils;
import com.zzy.captcha.utils.SharedPreferencesUtils;

/**
 * Created by zzyandzzy on 2017/1/11.
 */

public class NotificationClickReceiver extends BroadcastReceiver {
    private SharedPreferencesUtils sharedPreferencesUtils;
    @Override
    public void onReceive(Context context, Intent intent) {
        sharedPreferencesUtils = new SharedPreferencesUtils(context);
        String regex,copytext,tigger,keyword;
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null)
            notificationManager.cancel(RegexUtils.getNotificationId());
        regex = sharedPreferencesUtils.getString("smsRegex", RegexUtils.getSmsRegex());
        copytext = sharedPreferencesUtils.getString("copytext",RegexUtils.getCopyText(context));
        keyword = sharedPreferencesUtils.getString("keyword",RegexUtils.getKeywordRegex(context));
        tigger = sharedPreferencesUtils.getString("tigger",RegexUtils.getTiggerRegex(context));
        CopyCaptchaUtila.CopyCptcha(context,intent.getStringExtra("message"), regex,keyword,tigger,copytext);
    }
}
