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
                    getString(R.string.version), new IconicsDrawable(context)
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
                .text(R.string.Instructions)
                .icon(new IconicsDrawable(context)
                        .icon(GoogleMaterial.Icon.gmd_info_outline)
                        .color(ContextCompat.getColor(context, R.color.icon))
                        .sizeDp(18))
                .setOnClickListener(new MaterialAboutActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        final MaterialDialog materialDialog = new MaterialDialog(SettingActivity.this);
                        materialDialog.setTitle(R.string.Instructions);
                        materialDialog.setMessage(RegexUtils.getExplain(SettingActivity.this));
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

        smsTestBuilder = new MaterialAboutCard.Builder();
        smsTestBuilder.title(R.string.function);
        smsTestBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.smstestText)
                .subText(R.string.smstestSubText)
                .icon(new IconicsDrawable(context)
                        .icon(GoogleMaterial.Icon.gmd_sms)
                        .color(ContextCompat.getColor(context, R.color.icon))
                        .sizeDp(18))
                .setOnClickListener(new MaterialAboutActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        String regex;
                        regex = sharedPreferencesUtils.getString("smstest",RegexUtils.getSmsTest(SettingActivity.this));
                        EditTextDialog editTextDialog = new EditTextDialog(SettingActivity.this,getString(R.string.smstestText)
                                ,regex,getString(R.string.editDialog_test),getString(R.string.editDialog_cancel),"smstest");
                    }
                })
                .build());
        smsTestBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.keywordText)
                .subText(R.string.keywordSubtext)
                .icon(new IconicsDrawable(context)
                        .icon(GoogleMaterial.Icon.gmd_short_text)
                        .color(ContextCompat.getColor(context, R.color.icon))
                        .sizeDp(18))
                .setOnClickListener(new MaterialAboutActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        String regex;
                        regex = sharedPreferencesUtils.getString("keyword",RegexUtils.getKeywordRegex(SettingActivity.this));
                        EditTextDialog editTextDialog = new EditTextDialog(SettingActivity.this,getString(R.string.keywordText)
                                ,regex,getString(R.string.editDialog_ok),getString(R.string.editDialog_close),"keyword");
                    }
                })
                .build());
        smsTestBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.tiggerText)
                .subText(R.string.tiggerSubText)
                .icon(new IconicsDrawable(context)
                        .icon(GoogleMaterial.Icon.gmd_text_format)
                        .color(ContextCompat.getColor(context, R.color.icon))
                        .sizeDp(18))
                .setOnClickListener(new MaterialAboutActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        String regex;
                        regex = sharedPreferencesUtils.getString("tigger",RegexUtils.getTiggerRegex(SettingActivity.this));
                        EditTextDialog editTextDialog = new EditTextDialog(SettingActivity.this,getString(R.string.tiggerText)
                                ,regex,getString(R.string.editDialog_ok),getString(R.string.editDialog_close),"tigger");
                    }
                })
                .build());
        smsTestBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.smsRegexText)
                .subText(R.string.smsRegexSubText)
                .icon(new IconicsDrawable(context)
                        .icon(GoogleMaterial.Icon.gmd_edit)
                        .color(ContextCompat.getColor(context, R.color.icon))
                        .sizeDp(18))
                .setOnClickListener(new MaterialAboutActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        String regex;
                        regex = sharedPreferencesUtils.getString("smsRegex",RegexUtils.getSmsRegex());
                        EditTextDialog editTextDialog = new EditTextDialog(SettingActivity.this,getString(R.string.smsRegexText)
                                ,regex,getString(R.string.editDialog_ok),getString(R.string.editDialog_close),"smsregex");
                    }
                })
                .build());
        smsTestBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(Html.fromHtml("<font color="+getResources().getColor(R.color.colorPrimary)+getString(R.string.copyText)))
                .subText(Html.fromHtml("<font color="+getResources().getColor(R.color.colorPrimary)+getString(R.string.copytextSubText)))
                .icon(new IconicsDrawable(context)
                        .icon(GoogleMaterial.Icon.gmd_text_fields)
                        .color(ContextCompat.getColor(context, R.color.colorPrimary))
                        .sizeDp(18))
                .setOnClickListener(new MaterialAboutActionItem.OnClickListener() {
                    @Override
                    public void onClick() {
                        String regex;
                        regex = sharedPreferencesUtils.getString("copytext",RegexUtils.getCopyText(SettingActivity.this));
                        EditTextDialog editTextDialog = new EditTextDialog(SettingActivity.this,getString(R.string.copyText)
                                ,regex,getString(R.string.editDialog_ok),getString(R.string.editDialog_close),"copytext");
                    }
                })
                .build());

        authorCardBuilder = new MaterialAboutCard.Builder();
        authorCardBuilder.title(R.string.about);

        authorCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.author)
                .subText(R.string.authorEmail)
                .icon(new IconicsDrawable(context)
                        .icon(GoogleMaterial.Icon.gmd_person)
                        .color(ContextCompat.getColor(context, R.color.icon))
                        .sizeDp(18))
                .setOnClickListener(ConvenienceBuilder.createWebsiteOnClickAction(context,
                        Uri.parse(getString(R.string.githubAuthor))))
                .build());
        authorCardBuilder.addItem(new MaterialAboutActionItem.Builder()
                .text(R.string.addGroup)
                .subText(R.string.icodeGroup)
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
                .text(R.string.github)
                .icon(new IconicsDrawable(context)
                        .icon(CommunityMaterial.Icon.cmd_github_circle)
                        .color(ContextCompat.getColor(context, R.color.icon))
                        .sizeDp(18))
                .setOnClickListener(ConvenienceBuilder.createWebsiteOnClickAction(context,
                        Uri.parse(getString(R.string.githubCaptcha))))
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
            Toast.makeText(SettingActivity.this, R.string.errorAddQQGroup,Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}
