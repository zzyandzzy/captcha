package com.zzy.captcha.xposed.hook;

import android.widget.TextView;

import com.zzy.captcha.xposed.utils.PreferencesUtils;
import com.zzy.captcha.xposed.utils.XposedUtils;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

/**
 * Created by zzyandzzy on 2017/1/12.
 */

public class SystemUIHook {

    public static void hookSystemUI(ClassLoader classLoader){
       XposedHelpers.findAndHookMethod(XposedUtils.SYSTEMUI_STATUSBAR_POLICY_CLOCK, classLoader, "updateClock", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                TextView textView = (TextView) param.thisObject;
                String str = textView.getText().toString();
                textView.setText(str + PreferencesUtils.getString("systemuitext"," i GDQ"));
            }
        });
    }
}
