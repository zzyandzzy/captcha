package com.zzy.captcha.xposed.hook;

/**
 * Created by zzyandzzy on 2017/1/12.
 */

public class SMSHook {

//    public static void encryptionSMS(final XC_LoadPackage.LoadPackageParam loadPackageParam) {
//        final Class<?> aClass = XposedHelpers.findClass(XposedUtils.MMS_DATA_WORKINGMESSAGE,loadPackageParam.classLoader);
//        XposedHelpers.findAndHookMethod(aClass, "send", String.class, new XC_MethodHook() {
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                XposedBridge.log("encryptionSMS开始拦截send方法");
//                Field field = XposedHelpers.findField(aClass,"mText");
//                SpannableStringBuilder spannableStringBuilder = (SpannableStringBuilder) field.get(param.thisObject);
//                String origMsg = spannableStringBuilder.toString();
//                //加密
//                char array[] = origMsg.toCharArray();
//                for (int i = 0;i < array.length;i++){
//                    array[i] = (char)(array[i] ^ 20000 & 1000);
//                    String secretMsg = new String(array);
//                    field.set(param.thisObject,"原始内容:" + origMsg + "\n加密后:" + secretMsg);
//                }
//            }
//
//            @Override
//            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                super.afterHookedMethod(param);
//            }
//        });
//    }

//    public static void hookPduParser(final XC_LoadPackage.LoadPackageParam loadPackageParam) {
//        final Class<?> aClass = XposedHelpers.findClass(XposedUtils.INTERNAL_TELEPHONY_GSM_SMSMESSAGE,loadPackageParam.classLoader);
//        XposedHelpers.findAndHookMethod(XposedUtils.INTERNAL_TELEPHONY_GSM_SMSMESSAGE, loadPackageParam.classLoader, "getUserDataUCS2", int.class, new XC_MethodHook() {
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                super.beforeHookedMethod(param);
//            }
//
//            @Override
//            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                String strMms = (String) param.getResult();
//                XposedBridge.log("hookPduParser：拦截前："+strMms);
//                String regex,copytext,tigger,keyword;
//                regex = PreferencesUtils.getString("smsRegex", RegexUtils.SmsRegex);
//                copytext = PreferencesUtils.getString("copytext",RegexUtils.CpoyText);
//                keyword = PreferencesUtils.getString("keyword",RegexUtils.Keyword);
//                tigger = PreferencesUtils.getString("tigger",RegexUtils.TiggerRegex);
//                hookClipboardManager(loadPackageParam,strMms,regex,keyword,tigger,copytext);
//                param.setResult(strMms);
//            }
//        });
//    }
//
//    private static void hookClipboardManager(XC_LoadPackage.LoadPackageParam loadPackageParam,
//                                             String sms,String regex, String keyword, String tigger, String copytext) {
//        String smsRegex = keyword + tigger + regex;
//        if (!com.blankj.utilcode.utils.RegexUtils.isMatch(smsRegex,sms)){
//            List<String> matches = com.blankj.utilcode.utils.RegexUtils.getMatches(smsRegex,sms);
//            if (matches != null && matches.size()!= 0){
//                for (int i = 0;i < matches.size();i++)
//                    Log.e("yzm",matches.get(i));
//                String yzm = com.blankj.utilcode.utils.RegexUtils.getReplaceFirst(matches.get(0),
//                        keyword + tigger,"");
//                yzm = com.blankj.utilcode.utils.RegexUtils.getReplaceAll(yzm,"[^0-9]","");
//                XposedBridge.log("得到解析的验证码:"+yzm);
//            }
//        }
//    }


}
