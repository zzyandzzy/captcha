package com.zzy.captcha.utils;

import android.content.ClipboardManager;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.blankj.utilcode.utils.RegexUtils;

import java.util.List;

/**
 * Created by zzyandzzy on 2017/1/9.
 */

public class CopyCaptchaUtila {

    public static void CopyCptcha(Context context,String sms,String regex,String keyword,String tigger, String copytext){
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(context.CLIPBOARD_SERVICE);
        String smsRegex = keyword + tigger + regex;
        if (!RegexUtils.isMatch(smsRegex,sms)){
            List<String> matches = RegexUtils.getMatches(smsRegex,sms);
            if (matches != null && matches.size()!= 0){
                for (int i = 0;i < matches.size();i++)
                    Log.e("yzm",matches.get(i));
                String yzm = RegexUtils.getReplaceFirst(matches.get(0),
                        keyword + tigger,"");
                yzm = RegexUtils.getReplaceAll(yzm,"[^0-9]","");
                clipboardManager.setText(yzm);
                Toast.makeText(context,copytext + clipboardManager.getText().toString()
                        ,Toast.LENGTH_SHORT).show();
            }
        }
    }
}
