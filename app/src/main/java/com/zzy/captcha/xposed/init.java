package com.zzy.captcha.xposed;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by zzyandzzy on 2017/1/12.
 */

public class init implements IXposedHookLoadPackage {
    private static final String PACKAGE_SYSTEMUI = "com.android.systemui";
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        switch (loadPackageParam.packageName){
            case PACKAGE_SYSTEMUI:
                SystemUIHook.hookSystemUI(loadPackageParam.classLoader);
                break;
        }
    }
}
