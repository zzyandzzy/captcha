package com.zzy.captcha.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.text.Html;
import android.view.View;
import android.widget.Toast;

import com.zzy.captcha.R;
import com.zzy.captcha.ui.widget.EditTextDialog;
import com.zzy.captcha.ui.widget.HtmlDialog;
import com.zzy.captcha.utils.SharedPreferencesUtils;
import com.zzy.captcha.utils.Utils;
import com.zzy.captcha.utils.XposedPreferencesUtils;
import com.zzy.materialsettinglibrary.model.MaterialSettingActionItem;
import com.zzy.materialsettinglibrary.model.MaterialSettingCard;
import com.zzy.materialsettinglibrary.model.MaterialSettingCompoundButtonItem;
import com.zzy.materialsettinglibrary.model.MaterialSettingItem;
import com.zzy.materialsettinglibrary.model.MaterialSettingList;
import com.zzy.materialsettinglibrary.model.MaterialSettingTitleItem;
import com.zzy.materialsettinglibrary.ui.MaterialSettingActivity;

import de.psdev.licensesdialog.LicensesDialog;
import me.drakeet.materialdialog.MaterialDialog;

import static com.zzy.captcha.R.string.smsVerifysubText;

/**
 * Created by zzyandzzy on 2017/1/9.
 */

public class SettingActivity extends MaterialSettingActivity {
    private MaterialSettingCard.Builder appCardBuilder;
    private MaterialSettingCard.Builder authorCardBuilder;
    private MaterialSettingCard.Builder smsTestBuilder;
    private SharedPreferencesUtils sharedPreferencesUtils;
    private XposedPreferencesUtils xposedPreferencesUtils;
    private PackageManager packageManager;
    private HtmlDialog htmlDialog;

    @Override
    protected MaterialSettingList getMaterialSettingList(final Context context) {
        init();
        appCardBuilder = new MaterialSettingCard.Builder();
        appCardBuilder.addItem(new MaterialSettingTitleItem.Builder()
                .text(R.string.app_name)
                .icon(R.mipmap.ic_launcher)
                .build());
        try {
            appCardBuilder.addItem(new MaterialSettingActionItem.Builder()
                    .text("更新日志")
                    .subText(getString(R.string.version)+"("+packageManager.getPackageInfo(context.getPackageName(),0)
                                    .versionName + ")" )
                    .icon(R.drawable.ic_perm_device_information_black_24dp)
                    .setOnClickListener(new MaterialSettingActionItem.OnClickListener() {
                        @Override
                        public void onClick() {
                            htmlDialog = new HtmlDialog(context,"更新内容","明白");
                            htmlDialog.show();
                        }
                    })
                    .build());
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        appCardBuilder.addItem(new MaterialSettingActionItem.Builder()
                .text(R.string.licences)
                .icon(context.getResources().getDrawable(R.drawable.ic_bookmark_border_black_24dp))
                .setOnClickListener(new MaterialSettingActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        showLicensesDialog();
                    }
                })
                .build());
        appCardBuilder.addItem(new MaterialSettingActionItem.Builder()
                .text(R.string.Instructions)
                .icon(context.getResources().getDrawable(R.drawable.ic_info_outline_black_24dp))
                .setOnClickListener(new MaterialSettingActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        final MaterialDialog materialDialog = new MaterialDialog(SettingActivity.this);
                        materialDialog.setTitle(R.string.Instructions);
                        materialDialog.setMessage(Utils.getExplain(SettingActivity.this));
                        materialDialog.setPositiveButton(getString(R.string.know), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                materialDialog.dismiss();
                            }
                        });
                        materialDialog.show();
                    }
                })
                .build());

        smsTestBuilder = new MaterialSettingCard.Builder();
        smsTestBuilder.title(R.string.function);
        smsTestBuilder.addItem(new MaterialSettingActionItem.Builder()
                .text(R.string.smstestText)
                .subText(R.string.smstestSubText)
                .icon(context.getResources().getDrawable(R.drawable.ic_sms_black_24dp))
                .setOnClickListener(new MaterialSettingActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        String regex;
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N)
                            regex = xposedPreferencesUtils.getString("smstest", Utils.SmsTest);
                        else
                            regex = sharedPreferencesUtils.getString("smstest", Utils.SmsTest);
                        EditTextDialog editTextDialog = new EditTextDialog(SettingActivity.this,getString(R.string.smstestText)
                                ,regex,getString(R.string.editDialog_test),getString(R.string.editDialog_cancel),"smstest");
                    }
                })
                .build());
        smsTestBuilder.addItem(new MaterialSettingActionItem.Builder()
                .text(R.string.keywordText)
                .subText(R.string.keywordSubtext)
                .icon(context.getResources().getDrawable(R.drawable.ic_short_text_black_24dp))
                .setOnClickListener(new MaterialSettingActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        String regex;
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N)
                            regex = xposedPreferencesUtils.getString("keyword", Utils.Keyword);
                        else
                            regex = sharedPreferencesUtils.getString("keyword", Utils.Keyword);
                        EditTextDialog editTextDialog = new EditTextDialog(SettingActivity.this,getString(R.string.keywordText)
                                ,regex,getString(R.string.editDialog_ok),getString(R.string.editDialog_close),"keyword");
                    }
                })
                .build());
        smsTestBuilder.addItem(new MaterialSettingActionItem.Builder()
                .text(R.string.tiggerText)
                .subText(R.string.tiggerSubText)
                .icon(context.getResources().getDrawable(R.drawable.ic_text_format_black_24dp))
                .setOnClickListener(new MaterialSettingActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        String regex;
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N)
                            regex = xposedPreferencesUtils.getString("tigger", Utils.TiggerRegex);
                        else
                            regex = sharedPreferencesUtils.getString("tigger", Utils.TiggerRegex);
                        EditTextDialog editTextDialog = new EditTextDialog(SettingActivity.this,getString(R.string.tiggerText)
                                ,regex,getString(R.string.editDialog_ok),getString(R.string.editDialog_close),"tigger");
                    }
                })
                .build());
        smsTestBuilder.addItem(new MaterialSettingActionItem.Builder()
                .text(R.string.smsRegexText)
                .subText(R.string.smsRegexSubText)
                .icon(context.getResources().getDrawable(R.drawable.ic_edit_black_24dp))
                .setOnClickListener(new MaterialSettingActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        String regex;
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N)
                            regex = xposedPreferencesUtils.getString("smsRegex", Utils.SmsRegex);
                        else
                            regex = sharedPreferencesUtils.getString("smsRegex", Utils.SmsRegex);
                        EditTextDialog editTextDialog = new EditTextDialog(SettingActivity.this,getString(R.string.smsRegexText)
                                ,regex,getString(R.string.editDialog_ok),getString(R.string.editDialog_close),"smsregex");
                    }
                })
                .build());
        smsTestBuilder.addItem(new MaterialSettingCompoundButtonItem.Builder()
                .setItemType(MaterialSettingItem.ItemType.CHECKBOX_ITEM)
                .key("smsVerify")
                .defValue(false)
                .defText(R.string.smsVerifyText)
                .defSubText(smsVerifysubText)
                .build());
        smsTestBuilder.addItem(new MaterialSettingActionItem.Builder()
                .text(Html.fromHtml("<font color="+getResources().getColor(R.color.colorPrimary) + ">"+getString(R.string.copyText)+"</font>"))
                .subText(Html.fromHtml("<font color="+getResources().getColor(R.color.colorPrimary)+">"+getString(R.string.copytextSubText)+"</font>"))
                .icon(context.getResources().getDrawable(R.drawable.ic_text_fields_black_24dp))
                .setOnClickListener(new MaterialSettingActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        String regex;
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N)
                            regex = xposedPreferencesUtils.getString("copytext", Utils.CpoyText);
                        else
                            regex = sharedPreferencesUtils.getString("copytext", Utils.CpoyText);
                        EditTextDialog editTextDialog = new EditTextDialog(SettingActivity.this,getString(R.string.copyText)
                                ,regex,getString(R.string.editDialog_ok),getString(R.string.editDialog_close),"copytext");
                    }
                })
                .build());
        smsTestBuilder.addItem(new MaterialSettingActionItem.Builder()
                .text(Html.fromHtml("<font color="+getResources().getColor(R.color.colorAccent) + ">"+getString(R.string.xposed)+"</font>"))
                .icon(context.getResources().getDrawable(R.drawable.ic_extension_black_24dp))
                .setOnClickListener(new MaterialSettingActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N){
                            String regex;
                            regex = xposedPreferencesUtils.getString("systemuitext",getString(R.string.gdq));
                            EditTextDialog editTextDialog = new EditTextDialog(SettingActivity.this,getString(R.string.xposed_systemuitext)
                                    ,regex,getString(R.string.editDialog_ok),getString(R.string.editDialog_cancel),"systemuitext");
                        }else
                            Toast.makeText(SettingActivity.this,"Xposed不支持安卓7.0",Toast.LENGTH_SHORT).show();
                    }
                })
                .build());

        authorCardBuilder = new MaterialSettingCard.Builder();
        authorCardBuilder.title(R.string.about);
        authorCardBuilder.addItem(new MaterialSettingActionItem.Builder()
                .text(R.string.author)
                .subText(R.string.authorEmail)
                .icon(context.getResources().getDrawable(R.drawable.ic_person_black_24dp))
                .setOnClickListener(new MaterialSettingActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        startUrl(getString(R.string.githubAuthor));
                    }
                })
                .build());
        authorCardBuilder.addItem(new MaterialSettingActionItem.Builder()
                .text(R.string.addGroup)
                .subText(R.string.icodeGroup)
                .icon(context.getResources().getDrawable(R.drawable.ic_group_add_black_24dp))
                .setOnClickListener(new MaterialSettingActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        joinQQGroup("7zlOs8QB-TpNjClr2YTMphCK3lriWf6t");
                    }
                })
                .build());
        authorCardBuilder.addItem(new MaterialSettingActionItem.Builder()
                .subText(R.string.github)
                .setOnClickListener(new MaterialSettingActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        startUrl(getString(R.string.githubCaptcha));
                    }
                })
                .build());
        return new MaterialSettingList(appCardBuilder.build()
                ,smsTestBuilder.build()
                ,authorCardBuilder.build());
    }

    private void init() {
        sharedPreferencesUtils = new SharedPreferencesUtils(this);
        xposedPreferencesUtils = new XposedPreferencesUtils(this);
        packageManager = this.getPackageManager();
    }

    @Override
    protected CharSequence getActivityTitle() {
        return getString(R.string.setting);
    }

    private void showLicensesDialog(){
        new LicensesDialog.Builder(this)
                .setTitle(getResources().getString(R.string.licences))
                .setNotices(R.raw.license)
                .setCloseText(R.string.ok)
                .build()
                .show();
    }

    public void startUrl(String url){
        Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /****************
     *
     * 发起添加群流程。群号：iCode交流群(124593121) 的 key 为： 7zlOs8QB-TpNjClr2YTMphCK3lriWf6t
     * 调用 joinQQGroup(7zlOs8QB-TpNjClr2YTMphCK3lriWf6t) 即可发起手Q客户端申请加群 iCode交流群(124593121)
     *
     * @param key 由官网生成的key
     * @return 返回true表示呼起手Q成功，返回fals表示呼起失败
     ******************/
    public boolean joinQQGroup(String key) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + key));
        // 此Flag可根据具体产品需要自定义，如设置，则在加群界面按返回，返回手Q主界面，不设置，按返回会返回到呼起产品界面    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            startActivity(intent);
            return true;
        } catch (Exception e) {
            // 未安装手Q或安装的版本不支持
            Toast.makeText(SettingActivity.this, R.string.errorAddQQGroup,Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}
