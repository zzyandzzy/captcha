package com.zzy.captcha.utils;

import android.content.Context;

import com.zzy.captcha.R;

/**
 * Created by zzyandzzy on 2017/1/10.
 */

public class RegexUtils {
    public static final String TiggerRegex = "(是|为|:|：| |G-)+(:|：|G-|\\[|【| |)";
    public static final String SmsRegex = "(?<![0-9a-zA-Z])([0-9a-zA-Z]{4,10})(?![0-9a-zA-Z])";
    public static final String Keyword = "(验证|校验|动态|确认|随机|激活|兑换|认证|交易|授权|操作|密码|提取|安全)+(码|代码|号码|密码)";
    public static final String CpoyText = "已复制验证码:";
    public static final String SmsTest = "【xx银行】您正在使用xx银行xx支付快捷支付，验证码：233333,付款金额100.00元。[工作人员不会索取，请勿泄露]";
    public static final String PackageName = "com.zzy.captcha";

    public static String getExplain(Context context){
        return context.getResources().getString(R.string.explain);
    }
    public static int getNotificationId(){
        return 100;
    }
}
