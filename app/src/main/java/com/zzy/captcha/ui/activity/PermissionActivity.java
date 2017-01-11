package com.zzy.captcha.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zzy.captcha.R;
import com.zzy.captcha.utils.SharedPreferencesUtils;

import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by zzyandzzy on 2017/1/9.
 */

public class PermissionActivity extends AppCompatActivity {
    private SharedPreferencesUtils sharedPreferencesUtils;
    private boolean isSingle;
    private String[] PER_ALL = {
            "android.permission.READ_SMS",
            "android.permission.SEND_SMS",
            "android.permission.READ_PHONE_STATE"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initPermission();
    }

    private void initPermission() {
        if (NeedPremission())
            Notice();
        else{
            startActivity(new Intent(this,SettingActivity.class));
            finish();
        }
    }

    private void Notice(){
        MaterialDialog builder = new MaterialDialog(this);
        builder.setTitle(getString(R.string.premissionTitle));
        builder.setMessage(getString(R.string.premissionMessage));
        builder.setPositiveButton(R.string.know, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PmRequest(PER_ALL,666);
            }
        });

        builder.setNegativeButton(R.string.editDialog_cancel, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        builder.show();
    }

    private void PmRequest(String[] per_all, int i) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(per_all,i);
        }
    }

    private void init() {
        sharedPreferencesUtils = new SharedPreferencesUtils(this);
    }

    private boolean NeedPremission() {
        if(Build.VERSION.SDK_INT >= 23){
            if(checkPermission(Manifest.permission.READ_PHONE_STATE, Binder.getCallingPid(),Binder.getCallingUid())
                    != PackageManager.PERMISSION_GRANTED){
                return true;            }
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults){
        switch (permsRequestCode){
            case 666:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    startActivity(new Intent(this,SettingActivity.class));
                    finish();
                }
        }
    }
}
