package com.zzy.captcha.xposed;

import android.widget.TextView;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

/**
 * Created by zzyandzzy on 2017/1/12.
 */

public class SystemUIHook {
    private static String PACKAGE_SYSTEMUI_CLOCK = "com.android.systemui.statusbar.policy.Clock";

    public static void hookSystemUI(ClassLoader classLoader){
        XposedHelpers.findAndHookMethod(PACKAGE_SYSTEMUI_CLOCK, classLoader, "updateClock", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
            }

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                TextView textView = (TextView) param.thisObject;
                String str = textView.getText().toString();
                textView.setText(str + PreferencesUtils.getText());
            }
        });
    }
}
