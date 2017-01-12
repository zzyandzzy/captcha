package com.zzy.captcha.xposed;

import com.zzy.captcha.xposed.hook.SystemUIHook;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static com.zzy.captcha.xposed.utils.XposedUtils.PACKAGE_SYSTEMUI;

/**
 * Created by zzyandzzy on 2017/1/12.
 */

public class init implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        switch (loadPackageParam.packageName){
            case PACKAGE_SYSTEMUI:
                SystemUIHook.hookSystemUI(loadPackageParam.classLoader);
                break;
//            case PACKAGE_MMS:
//                SMSHook.hookPduParser(loadPackageParam);
//                break;
        }
    }
}
