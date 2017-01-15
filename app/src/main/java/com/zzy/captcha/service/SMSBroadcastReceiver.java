package com.zzy.captcha.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;

import com.zzy.captcha.utils.CopyCaptchaUtila;
import com.zzy.captcha.utils.Utils;
import com.zzy.captcha.utils.SharedPreferencesUtils;
import com.zzy.captcha.utils.XposedPreferencesUtils;

/**
 * Created by zzyandzzy on 2017/1/9.
 */

public class SMSBroadcastReceiver extends BroadcastReceiver {
    private XposedPreferencesUtils xposedPreferencesUtils;
    private SharedPreferencesUtils sharedPreferencesUtils;
    private SmsMessage msg = null;
    private String regex,copytext,tigger,keyword;

    @Override
    public void onReceive(Context context, Intent intent) {
        xposedPreferencesUtils = new XposedPreferencesUtils(context);
        sharedPreferencesUtils = new SharedPreferencesUtils(context);
        Bundle bundle = intent.getExtras();
        if (null != bundle) {
            Object[] smsObj = (Object[]) bundle.get("pdus");
            for (Object object : smsObj) {
                msg = SmsMessage.createFromPdu((byte[])object);
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N){
                    regex = xposedPreferencesUtils.getString("smsRegex", Utils.SmsRegex);
                    copytext = xposedPreferencesUtils.getString("copytext", Utils.CpoyText);
                    keyword = xposedPreferencesUtils.getString("keyword", Utils.Keyword);
                    tigger = xposedPreferencesUtils.getString("tigger", Utils.TiggerRegex);
                }else {
                    regex = sharedPreferencesUtils.getString("smsRegex", Utils.SmsRegex);
                    copytext = sharedPreferencesUtils.getString("copytext", Utils.CpoyText);
                    keyword = sharedPreferencesUtils.getString("keyword", Utils.Keyword);
                    tigger = sharedPreferencesUtils.getString("tigger", Utils.TiggerRegex);
                }
                CopyCaptchaUtila.CopyCptcha(context,msg.getDisplayMessageBody(), regex,keyword,tigger,copytext);
            }
        }
    }
}
