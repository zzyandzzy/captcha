package com.zzy.captcha.ui.widget;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.zzy.captcha.R;
import com.zzy.captcha.utils.CopyCaptchaUtila;
import com.zzy.captcha.utils.RegexUtils;
import com.zzy.captcha.utils.SharedPreferencesUtils;

import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by zzyandzzy on 2017/1/10.
 */

public class EditTextDialog extends MaterialDialog {
    private Context context;
    private View view;
    private EditText message;
    private SharedPreferencesUtils sharedPreferencesUtils;

    public EditTextDialog(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public EditTextDialog(Context context,String title,String defineMessage,String ok,String close,String lis){
        super(context);
        this.context = context;
        init();
        this.setTitle(title);
        initShowDialog(defineMessage,ok,close,lis);
    }

    private void initShowDialog(String defineMessage, String ok,String close, final String lis) {
        message = (EditText) view.findViewById(R.id.dialog_edit_message);
        message.setText(defineMessage);
        this.setPositiveButton(ok, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (lis){
                    case "smstest":
                        if (!isMessageNull(message.getText().toString())){
                            addNotification(context,context.getString(R.string.smsTest),message.getText().toString());
                            sharedPreferencesUtils.putString("smstest",message.getText().toString());
                            dismiss();
                        }
                        break;
                    case "smsregex":
                        if (!isMessageNull(message.getText().toString())){
                            sharedPreferencesUtils.putString("smsRegex",message.getText().toString());
                            dismiss();
                        }
                        break;
                    case "keyword":
                        if (!isMessageNull(message.getText().toString())){
                            sharedPreferencesUtils.putString("keyword",message.getText().toString());
                            dismiss();
                        }
                        break;
                    case "tigger":
                        if (!isMessageNull(message.getText().toString())){
                            sharedPreferencesUtils.putString("tigger",message.getText().toString());
                            dismiss();
                        }
                        break;
                    case "copytext":
                        if (!isMessageNull(message.getText().toString())){
                            sharedPreferencesUtils.putString("copytext",message.getText().toString());
                            dismiss();
                        }
                        break;
                    default:
                        dismiss();
                }
            }
        });
        if (close != null){
            this.setNegativeButton(close, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (lis){
                        case "smsregex":
                            message.setText(RegexUtils.getSmsRegex());
                            break;
                        case "keyword":
                            message.setText(RegexUtils.getKeywordRegex(context));
                            break;
                        case "tigger":
                            message.setText(RegexUtils.getTiggerRegex(context));
                            break;
                        case "copytext":
                            message.setText(RegexUtils.getCopyText(context));
                            break;
                        default:
                            dismiss();
                    }
                }
            });
        }
        this.show();
    }

    private boolean isMessageNull(String message){
        if (message.length() != 0 && message != null){
            return false;
        }
        else{
            Toast.makeText(context, R.string.errorEditString,Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    private void init() {
        view = LayoutInflater.from(context).inflate(R.layout.dialog_edit,null);
        this.setContentView(view);
        sharedPreferencesUtils = new SharedPreferencesUtils(context);
    }

    private void addNotification(Context context, String fromAddress, String message) {
        int notificationId = 110;
        //新建一个notification
        Notification.Builder notification = new Notification.Builder(context)
                .setTicker(message)
                .setWhen(System.currentTimeMillis())
                .setContentTitle(fromAddress)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_notify)
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentIntent(createDisplayMessageIntent(context, fromAddress, message,
                        notificationId));
        //获取通知管理器服务
        NotificationManager notificationManager =
                (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        //开始通知
        notificationManager.notify(notificationId, notification.getNotification());
    }

    private PendingIntent createDisplayMessageIntent(Context context, String fromAddress, String message, int notificationId) {
        String regex,copytext,tigger,keyword;
        regex = sharedPreferencesUtils.getString("smsRegex",RegexUtils.getSmsRegex());
        copytext = sharedPreferencesUtils.getString("copytext",RegexUtils.getCopyText(context));
        keyword = sharedPreferencesUtils.getString("keyword",RegexUtils.getKeywordRegex(context));
        tigger = sharedPreferencesUtils.getString("tigger",RegexUtils.getTiggerRegex(context));
        CopyCaptchaUtila.CopyCptcha(context,message, regex,keyword,tigger,copytext);
        PendingIntent pendingIntent= PendingIntent.getActivity(context, 1, new Intent(), notificationId);
        return pendingIntent;
    }
}
