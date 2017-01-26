package com.zzy.captcha.utils;

import android.content.ClipboardManager;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

/**
 * Created by zzyandzzy on 2017/1/9.
 */

public class CopyCaptchaUtila {
    private static SharedPreferencesUtils sharedPreferencesUtils;

    public static void CopyCptcha(Context context,String sms,String regex,String keyword,String tigger, String copytext){
        sharedPreferencesUtils = new SharedPreferencesUtils(context);
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(context.CLIPBOARD_SERVICE);
        //1.1版本复制验证码
        boolean flag = false;
        //匹配到关键字flag就为true
        if (RegexUtils.findMatch(keyword,sms)){
            flag = true;
            List<String> matches = RegexUtils.getMatches(keyword + tigger + regex,sms);
            if (matches != null && matches.size() != 0 && flag){
                for (int i = 0;i < matches.size();i++)
                    Log.d("TAG",matches.get(i));
                String yzm;
                if (RegexUtils.findMatch("Google",matches.get(0)))
                    yzm = RegexUtils.getReplaceFirst(matches.get(1), keyword + tigger,"");
                else
                    yzm = RegexUtils.getReplaceFirst(matches.get(0), keyword + tigger,"");
                if (sharedPreferencesUtils.getBoolean("smsVerify",false))
                    yzm = RegexUtils.getReplaceAll(yzm,"[^0-9a-zA-Z]","");
                //复制到剪贴板
                clipboardManager.setText(yzm);
                //Toast提示
                Toast.makeText(context,copytext + clipboardManager.getText().toString()
                        ,Toast.LENGTH_SHORT).show();
            }else if (flag){
                matches = RegexUtils.getMatches(regex,sms);
                if (matches != null && matches.size() != 0) {
                    for (int i = 0; i < matches.size(); i++)
                        Log.d("TAG", "regex:"+matches.get(i));
                    String yz = "";
                    for (int i = 0; i < matches.size(); i++){
                        if (RegexUtils.findMatch("[0-9]{4,10}",matches.get(i))
                                || RegexUtils.findMatch("[a-z]{4,10}",matches.get(i))
                                ||RegexUtils.findMatch("[A-Z]{4,10}",matches.get(i))){
                            if (RegexUtils.findMatch("Google",matches.get(0)))
                                yz = matches.get(1);
                            else
                                yz = matches.get(i);
                        }else{
                            yz = matches.get(0);
                        }
                    }
                    String yzm = RegexUtils.getReplaceFirst(yz, keyword + tigger,"");
                    if (sharedPreferencesUtils.getBoolean("smsVerify",false))
                        yzm = RegexUtils.getReplaceAll(yzm,"[^0-9a-zA-Z]","");
                    //复制到剪贴板
                    clipboardManager.setText(yzm);
                    //Toast提示
                    Toast.makeText(context,copytext + clipboardManager.getText().toString()
                            ,Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
