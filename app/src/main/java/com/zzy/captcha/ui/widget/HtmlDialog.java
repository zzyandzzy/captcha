package com.zzy.captcha.ui.widget;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.zzy.captcha.R;
import com.zzy.captcha.utils.SharedPreferencesUtils;

import java.io.IOException;
import java.io.InputStream;

import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by zzyandzzy on 2017/1/15.
 */

public class HtmlDialog extends MaterialDialog {
    private Context context;
    private View view;
    private SharedPreferencesUtils sharedPreferencesUtils;
    private WebView webView;
    private String title,ok;

    public HtmlDialog(Context context) {
        super(context);
        this.context = context;
        this.title = context.getString(R.string.title);
        this.ok = context.getString(R.string.ok);
        init();
    }

    public HtmlDialog(Context context,String title,String ok){
        super(context);
        this.context = context;
        this.title = title;
        this.ok = ok;
        init();
    }

    private void init() {
        sharedPreferencesUtils = new SharedPreferencesUtils(context);
        view = LayoutInflater.from(context).inflate(R.layout.dialog_html,null);
        this.setContentView(view);
        this.setTitle(title);
        webView = (WebView) view.findViewById(R.id.dialog_html_webview);
        webView.loadDataWithBaseURL(null,readAssets(context,"about.html"),
                "text/html", "utf-8", null);
        webView.setWebChromeClient(new WebChromeClient());
        this.setPositiveButton(ok, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public String readAssets(Context context,String fileName){
        try {
            InputStream is = context.getResources().getAssets().open(fileName);
            int length = is.available();
            byte[] buffer = new byte[length];
            is.read(buffer);
            String result = new String(buffer,"utf-8");
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("TAG","读取:" + fileName + "文件失败。\n" + e.getMessage());
        }
        return null;
    }
}
