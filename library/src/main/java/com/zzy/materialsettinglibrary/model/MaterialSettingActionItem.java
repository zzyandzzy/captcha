package com.zzy.materialsettinglibrary.model;

import android.graphics.drawable.Drawable;

/**
 * Created by zzyandzzy on 2017/1/14.
 */

public class MaterialSettingActionItem extends MaterialSettingItem {
    private CharSequence text;
    private int textRes;
    private CharSequence subText;
    private int subTextRes;
    private Drawable icon;
    private int iconRes;
    public interface OnClickListener{
        void onClick();
    }
    private OnClickListener onClickListener = null;

    public CharSequence getText() {
        return text;
    }

    public CharSequence getSubText() {
        return subText;
    }

    public Drawable getIcon() {
        return icon;
    }

    public int getTextRes() {
        return textRes;
    }

    public int getSubTextRes() {
        return subTextRes;
    }

    public int getIconRes() {
        return iconRes;
    }

    public OnClickListener getOnClickListener() {
        return onClickListener;
    }

    @Override
    public int getType() {
        return ItemType.ACTION_ITEM;
    }

    private MaterialSettingActionItem(Builder builder){
        this.text = null;
        this.textRes = 0;
        this.subText = null;
        this.subTextRes = 0;
        this.icon = null;
        this.iconRes = 0;
        this.onClickListener = null;
        this.text = builder.text;
        this.textRes = builder.textRes;
        this.subText = builder.subText;
        this.subTextRes = builder.subTextRes;
        this.icon = builder.icon;
        this.iconRes = builder.iconRes;
        this.onClickListener = builder.onClickListener;
    }

    public MaterialSettingActionItem(CharSequence text,CharSequence subText,Drawable icon,OnClickListener onClickListener){
        this.text = null;
        this.textRes = 0;
        this.subText = null;
        this.subTextRes = 0;
        this.icon = null;
        this.iconRes = 0;
        this.onClickListener = null;
        this.text = text;
        this.subText = subText;
        this.icon = icon;
        this.onClickListener = onClickListener;
    }

    public MaterialSettingActionItem(CharSequence text,CharSequence subText,Drawable icon){
        this.text = null;
        this.textRes = 0;
        this.subText = null;
        this.subTextRes = 0;
        this.icon = null;
        this.iconRes = 0;
        this.onClickListener = null;
        this.text = text;
        this.subText = subText;
        this.icon = icon;
    }

    public MaterialSettingActionItem(int textRes,int subTextRes,int iconRes,OnClickListener onClickListener){
        this.text = null;
        this.textRes = 0;
        this.subText = null;
        this.subTextRes = 0;
        this.icon = null;
        this.iconRes = 0;
        this.onClickListener = null;
        this.textRes = textRes;
        this.subTextRes = subTextRes;
        this.iconRes = iconRes;
        this.onClickListener = onClickListener;
    }

    public MaterialSettingActionItem(int textRes,int subTextRes,int iconRes){
        this.text = null;
        this.textRes = 0;
        this.subText = null;
        this.subTextRes = 0;
        this.icon = null;
        this.iconRes = 0;
        this.onClickListener = null;
        this.textRes = textRes;
        this.subTextRes = subTextRes;
        this.iconRes = iconRes;
    }

    public static class Builder {
        private CharSequence text = null;
        private int textRes = 0;
        private CharSequence subText = null;
        private int subTextRes = 0;
        private Drawable icon = null;
        private int iconRes = 0;
        private MaterialSettingActionItem.OnClickListener onClickListener;

        public Builder text(CharSequence text){
            this.text = text;
            this.textRes = 0;
            return this;
        }

        public Builder text(int textRes){
            this.textRes = textRes;
            this.text = null;
            return this;
        }

        public Builder subText(CharSequence subText){
            this.subText = subText;
            this.textRes = 0;
            return this;
        }

        public Builder subText(int subTextRes){
            this.subTextRes = subTextRes;
            this.subText = null;
            return this;
        }

        public Builder icon(Drawable icon){
            this.icon = icon;
            return this;
        }

        public Builder icon(int iconRes){
            this.iconRes = iconRes;
            return this;
        }

        public Builder setOnClickListener(MaterialSettingActionItem.OnClickListener onClickListener){
            this.onClickListener = onClickListener;
            return this;
        }

        public CharSequence getText(){
            return this.text;
        }

        public MaterialSettingActionItem build(){
            return new MaterialSettingActionItem(this);
        }
    }
}
