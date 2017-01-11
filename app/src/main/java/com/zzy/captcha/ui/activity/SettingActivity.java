package com.zzy.captcha.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.view.View;
import android.widget.Toast;

import com.danielstone.materialaboutlibrary.ConvenienceBuilder;
import com.danielstone.materialaboutlibrary.MaterialAboutActivity;
import com.danielstone.materialaboutlibrary.model.MaterialAboutActionItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;
import com.danielstone.materialaboutlibrary.model.MaterialAboutTitleItem;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.zzy.captcha.R;
import com.zzy.captcha.ui.widget.EditTextDialog;
import com.zzy.captcha.utils.RegexUtils;
import com.zzy.captcha.utils.SharedPreferencesUtils;

import de.psdev.licensesdialog.LicensesDialog;
import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by zzyandzzy on 2017/1/9.
 */

public class SettingActivity extends MaterialAboutActivity {
    String SENT_SMS_ACTION="SENT_SMS_ACTION";
    String DELIVERED_SMS_ACTION="DELIVERED_SMS_ACTION";

    private MaterialAboutCard.Builder appCardBuilder;
    private MaterialAboutCard.Builder authorCardBuilder;
    private MaterialAboutCard.Builder smsTestBuilder;
    private SharedPreferencesUtils sharedPreferencesUtils;

    @Override
    protected MaterialAboutList getMaterialAboutList(final Context context) {
        init();
        appCardBuilder = new MaterialAboutCard.Builder();
        appCardBuilder.addItem(new MaterialAboutTitleItem.Builder()
                .text(R.string.app_name)
                .icon(R.mipmap.ic_launcher)
                .build());
        try {
            appCardBuilder.addItem(ConvenienceBuilder.createVersionActionItem(context,
                    "版本", new IconicsDrawable(context)
                            .icon(GoogleMaterial.Icon.gmd_perm_device_information)
                            .color(ContextCompat.getColor(context, R.color.icon))
                            .sizeDp(18)));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        appCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.licences)
                .icon(new IconicsDrawable(context)
                        .icon(GoogleMaterial.Icon.gmd_bookmark_border)
                        .color(ContextCompat.getColor(context, R.color.icon))
                        .sizeDp(18))
                .setOnClickListener(new MaterialAboutActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        showLicensesDialog();
                    }
                })
                .build());
        appCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("使用说明")
                .icon(new IconicsDrawable(context)
                        .icon(GoogleMaterial.Icon.gmd_info_outline)
                        .color(ContextCompat.getColor(context, R.color.icon))
                        .sizeDp(18))
                .setOnClickListener(new MaterialAboutActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        final MaterialDialog materialDialog = new MaterialDialog(SettingActivity.this);
                        materialDialog.setTitle("使用说明");
                        materialDialog.setMessage(RegexUtils.getExplain(SettingActivity.this));
                        materialDialog.setPositiveButton("知道了", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                materialDialog.dismiss();
                            }
                        });
                        materialDialog.show();
                    }
                })
                .build());

        smsTestBuilder = new MaterialAboutCard.Builder();
        smsTestBuilder.title("功能");
        smsTestBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("测试验证码")
                .subText("测试验证码是否可以正确复制")
                .icon(new IconicsDrawable(context)
                        .icon(GoogleMaterial.Icon.gmd_sms)
                        .color(ContextCompat.getColor(context, R.color.icon))
                        .sizeDp(18))
                .setOnClickListener(new MaterialAboutActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        String regex;
                        regex = sharedPreferencesUtils.getString("smstest",RegexUtils.getSmsTest(SettingActivity.this));
                        EditTextDialog editTextDialog = new EditTextDialog(SettingActivity.this,"测试"
                                ,regex,"测试","取消","smstest");
                    }
                })
                .build());
        smsTestBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("关键字")
                .subText("触发自动解析的关键字")
                .icon(new IconicsDrawable(context)
                        .icon(GoogleMaterial.Icon.gmd_short_text)
                        .color(ContextCompat.getColor(context, R.color.icon))
                        .sizeDp(18))
                .setOnClickListener(new MaterialAboutActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        String regex;
                        regex = sharedPreferencesUtils.getString("keyword",RegexUtils.getKeywordRegex(SettingActivity.this));
                        EditTextDialog editTextDialog = new EditTextDialog(SettingActivity.this,"关键字"
                                ,regex,"确定","默认","keyword");
                    }
                })
                .build());
        smsTestBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("触发字")
                .subText("触发自动解析的关键字")
                .icon(new IconicsDrawable(context)
                        .icon(GoogleMaterial.Icon.gmd_text_format)
                        .color(ContextCompat.getColor(context, R.color.icon))
                        .sizeDp(18))
                .setOnClickListener(new MaterialAboutActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        String regex;
                        regex = sharedPreferencesUtils.getString("tigger",RegexUtils.getTiggerRegex(SettingActivity.this));
                        EditTextDialog editTextDialog = new EditTextDialog(SettingActivity.this,"触发字"
                                ,regex,"确定","默认","tigger");
                    }
                })
                .build());
        smsTestBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("正则匹配验证码")
                .subText("测试正则验证码是否正确")
                .icon(new IconicsDrawable(context)
                        .icon(GoogleMaterial.Icon.gmd_edit)
                        .color(ContextCompat.getColor(context, R.color.icon))
                        .sizeDp(18))
                .setOnClickListener(new MaterialAboutActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        String regex;
                        regex = sharedPreferencesUtils.getString("smsRegex",RegexUtils.getSmsRegex());
                        EditTextDialog editTextDialog = new EditTextDialog(SettingActivity.this,"正则表达式"
                                ,regex,"确定","默认","smsregex");
                    }
                })
                .build());
        smsTestBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(Html.fromHtml("<font color="+getResources().getColor(R.color.colorPrimary)+">提示文本</font>"))
                .subText(Html.fromHtml("<font color="+getResources().getColor(R.color.colorPrimary)+">验证码提示文本</font>"))
                .icon(new IconicsDrawable(context)
                        .icon(GoogleMaterial.Icon.gmd_text_fields)
                        .color(ContextCompat.getColor(context, R.color.colorPrimary))
                        .sizeDp(18))
                .setOnClickListener(new MaterialAboutActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        String regex;
                        regex = sharedPreferencesUtils.getString("copytext",RegexUtils.getCopyText(SettingActivity.this));
                        EditTextDialog editTextDialog = new EditTextDialog(SettingActivity.this,"提示文本"
                                ,regex,"确定","默认","copytext");
                    }
                })
                .build());

        authorCardBuilder = new MaterialAboutCard.Builder();
        authorCardBuilder.title("关于");

        authorCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("zzyandzzy")
                .subText("1428658308@qq.com")
                .icon(new IconicsDrawable(context)
                        .icon(GoogleMaterial.Icon.gmd_person)
                        .color(ContextCompat.getColor(context, R.color.icon))
                        .sizeDp(18))
                .setOnClickListener(ConvenienceBuilder.createWebsiteOnClickAction(context,
                        Uri.parse("https://github.com/zzyandzzy")))
                .build());
        authorCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("加群")
                .subText("iCode交流群(124593121)")
                .icon(new IconicsDrawable(context)
                        .icon(GoogleMaterial.Icon.gmd_group_add)
                        .color(ContextCompat.getColor(context, R.color.icon))
                        .sizeDp(24))
                .setOnClickListener(new MaterialAboutActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        joinQQGroup("7zlOs8QB-TpNjClr2YTMphCK3lriWf6t");
                    }
                })
                .build());
        authorCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text("GitHub")
                .icon(new IconicsDrawable(context)
                        .icon(CommunityMaterial.Icon.cmd_github_circle)
                        .color(ContextCompat.getColor(context, R.color.icon))
                        .sizeDp(18))
                .setOnClickListener(ConvenienceBuilder.createWebsiteOnClickAction(context,
                        Uri.parse("https://github.com/zzyandzzy/captcha")))
                .build());
        return new MaterialAboutList(appCardBuilder.build()
                ,smsTestBuilder.build()
                ,authorCardBuilder.build());
    }

    private void init() {
        sharedPreferencesUtils = new SharedPreferencesUtils(this);
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
            Toast.makeText(SettingActivity.this,"未安装手Q或安装的版本不支持",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}
