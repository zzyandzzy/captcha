package com.zzy.materialsettinglibrary.model;

import android.graphics.drawable.Drawable;

/**
 * Created by zzyandzzy on 2017/1/13.
 */

public class MaterialSettingTitleItem extends MaterialSettingItem {
    private CharSequence text = null;
    private int textRes = 0;
    private Drawable icon = null;
    private int iconRes = 0;

    public MaterialSettingTitleItem(Builder builder) {
        this.text = builder.text;
        this.textRes = builder.textRes;
        this.icon = builder.icon;
        this.iconRes = builder.iconRes;
    }

    public MaterialSettingTitleItem(CharSequence text,Drawable icon){
        this.text = text;
        this.icon = icon;
    }

    public MaterialSettingTitleItem(int textRes,int iconRes){
        this.textRes = textRes;
        this.iconRes = iconRes;
    }

    @Override
    public int getType() {
        return ItemType.TITLE_ITEM;
    }

    public CharSequence getText() {
        return text;
    }

    public int getTextRes() {
        return textRes;
    }

    public Drawable getIcon() {
        return icon;
    }

    public int getIconRes(){
        return iconRes;
    }

    public static class Builder {
        private CharSequence text = null;
        private int textRes = 0;
        private Drawable icon = null;
        private int iconRes = 0;

        public MaterialSettingTitleItem.Builder text(CharSequence text){
            this.text = text;
            this.textRes = 0;
            return this;
        }

        public MaterialSettingTitleItem.Builder text(int textRes){
            this.textRes = textRes;
            this.text = null;
            return this;
        }

        public MaterialSettingTitleItem.Builder icon(Drawable icon){
            this.icon = icon;
            return this;
        }

        public MaterialSettingTitleItem.Builder icon(int iconRes){
            this.iconRes = iconRes;
            return this;
        }

        public MaterialSettingTitleItem build(){
            return new MaterialSettingTitleItem(this);
        }
    }
}
