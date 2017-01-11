package com.zzy.captcha.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import com.zzy.captcha.utils.CopyCaptchaUtila;
import com.zzy.captcha.utils.RegexUtils;
import com.zzy.captcha.utils.SharedPreferencesUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zzyandzzy on 2017/1/9.
 */

public class SMSBroadcastReceiver extends BroadcastReceiver {
    SharedPreferencesUtils sharedPreferencesUtils;
    String smsMessage = "sms";
    @Override
    public void onReceive(Context context, Intent intent) {
        sharedPreferencesUtils = new SharedPreferencesUtils(context);
        Bundle bundle = intent.getExtras();
        SmsMessage msg = null;
        if (null != bundle) {
            Object[] smsObj = (Object[]) bundle.get("pdus");
            for (Object object : smsObj) {
                msg = SmsMessage.createFromPdu((byte[]) object);
                Date date = new Date(msg.getTimestampMillis());
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String receiveTime = format.format(date);
                String regex,copytext,tigger,keyword;
                regex = sharedPreferencesUtils.getString("smsRegex",RegexUtils.getSmsRegex());
                copytext = sharedPreferencesUtils.getString("copytext",RegexUtils.getCopyText(context));
                keyword = sharedPreferencesUtils.getString("keyword",RegexUtils.getKeywordRegex(context));
                tigger = sharedPreferencesUtils.getString("tigger",RegexUtils.getTiggerRegex(context));
                CopyCaptchaUtila.CopyCptcha(context,msg.getDisplayMessageBody(), regex,keyword,tigger,copytext);
            }
        }
    }
}
