package com.zzy.captcha.xposed.utils;

/**
 * Created by zzyandzzy on 2017/1/12.
 */

public class XposedUtils {
    public static final String PACKAGE_SYSTEMUI = "com.android.systemui";
    public static final String SYSTEMUI_STATUSBAR_POLICY_CLOCK = "com.android.systemui.statusbar.policy.Clock";
    public static final String PACKAGE_MMS = "com.android.mms";
    public static final String MMS_DATA_WORKINGMESSAGE = "com.android.mms.data.WorkingMessage";
    public static final String PACKAGE_INTERNAL = "com.android.internal";
    public static final String PACKAGE_CLIPBOARDMANAGER = "com.android.content.ClipboardManager";
    public static final String INTERNAL_TELEPHONY_GSM_SMSMESSAGE = "com.android.internal.telephony.gsm.SmsMessage$PduParser";
    public static final String SmsBroadcastReceiver = "com.android.internal.telephony.InboundSmsHandler$SmsBroadcastReceiver";
}
