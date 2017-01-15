package com.zzy.materialsettinglibrary.model;

/**
 * Created by zzyandzzy on 2017/1/13.
 */

public class MaterialSettingCompoundButtonItem extends MaterialSettingItem {
    private CharSequence defText = null;
    private int defTextRes = 0;
    private CharSequence subDefText = null;
    private int subDefTextRes = 0;
    private String key = null;
    private boolean defValue = false;
    private CharSequence changeOnText = null;
    private int changeOnTextRes = 0;
    private CharSequence changeOffText = null;
    private int changeOffTextRes = 0;
    private CharSequence changeOnSubText = null;
    private int changeOnSubTextRes = 0;
    private CharSequence changeOffSubText = null;
    private int changeOffSubTextRes = 0;
    private int itemType = ItemType.CHECKBOX_ITEM;

    public OnCheckedChangeListener getOnCheckedChangeListener() {
        return onCheckedChangeListener;
    }

    public CharSequence getChangeOnText() {
        return changeOnText;
    }

    public CharSequence getChangeOffText() {
        return changeOffText;
    }

    public int getChangeOnTextRes() {
        return changeOnTextRes;
    }

    public int getChangeOffTextRes() {
        return changeOffTextRes;
    }

    public CharSequence getChangeOnSubText() {
        return changeOnSubText;
    }

    public int getChangeOnSubTextRes() {
        return changeOnSubTextRes;
    }

    public CharSequence getChangeOffSubText() {
        return changeOffSubText;
    }

    public int getChangeOffSubTextRes() {
        return changeOffSubTextRes;
    }

    public interface OnCheckedChangeListener{
        void onCheckedChanged(String key,boolean isChanged);
    }
    private OnCheckedChangeListener onCheckedChangeListener;

    public MaterialSettingCompoundButtonItem(Builder builder) {
        this.itemType = builder.itemType;
        this.defText = builder.defText;
        this.defTextRes = builder.defTextRes;
        this.subDefText = builder.subDefText;
        this.subDefTextRes = builder.subDefTextRes;
        this.key = builder.key;
        this.defValue = builder.defValue;
        this.changeOnText = builder.changeOnText;
        this.changeOnTextRes = builder.changeOnTextRes;
        this.changeOffText = builder.changeOffText;
        this.changeOffTextRes = builder.changeOffTextRes;
        this.changeOnSubText = builder.changeOnSubText;
        this.changeOnSubTextRes = builder.changeOnSubTextRes;
        this.changeOffSubText = builder.changeOffSubText;
        this.changeOffSubTextRes = builder.changeOffSubTextRes;
        this.onCheckedChangeListener = builder.onCheckedChangeListener;
    }

    public MaterialSettingCompoundButtonItem(int itemType,String key, boolean defValue,
            CharSequence defText, CharSequence subDefText,CharSequence changeOnText,
            CharSequence changeOnSubText,CharSequence changeOffText,
            CharSequence changeOffSubText ,OnCheckedChangeListener onCheckedChangeListener){
        this.itemType = itemType;
        this.defText = defText;
        this.subDefText = subDefText;
        this.key = key;
        this.defValue = defValue;
        this.changeOnText = changeOnText;
        this.changeOffSubText = changeOffSubText;
        this.changeOffText = changeOffText;
        this.changeOffSubText = changeOffSubText;
        this.onCheckedChangeListener = onCheckedChangeListener;
    }

    public MaterialSettingCompoundButtonItem(int itemType,String key, CharSequence defText, CharSequence subDefText){
        this.itemType = itemType;
        this.defText = defText;
        this.subDefText = subDefText;
        this.key = key;
    }

    public MaterialSettingCompoundButtonItem(int itemType,String key, int defTextRes, int subDefTextRes){
        this.itemType = itemType;
        this.defTextRes = defTextRes;
        this.subDefTextRes = subDefTextRes;
        this.key = key;
    }

    public MaterialSettingCompoundButtonItem(
            int itemType,String key, boolean defValue, int defTextRes, int subDefTextRes,
            int changeOnTextRes,int changeOnSubTextRes,int changeOffTextRes,
            int changeOffSubTextRes,OnCheckedChangeListener onCheckedChangeListener){
        this.itemType = itemType;
        this.defTextRes = defTextRes;
        this.subDefTextRes = subDefTextRes;
        this.key = key;
        this.defValue = defValue;
        this.changeOnTextRes = changeOnTextRes;
        this.changeOffTextRes = changeOffTextRes;
        this.changeOnSubTextRes = changeOnSubTextRes;
        this.changeOffSubTextRes = changeOffSubTextRes;
        this.onCheckedChangeListener = onCheckedChangeListener;
    }

    public int getItemType(){
        return itemType;
    }

    @Override
    public int getType() {
        if (getItemType()== ItemType.CHECKBOX_ITEM)
            return ItemType.CHECKBOX_ITEM;
        else if (getItemType() == ItemType.SWITCH_ITEM)
            return ItemType.SWITCH_ITEM;
        else if (getItemType() == ItemType.RADIOBUTTON_ITEM)
            return ItemType.RADIOBUTTON_ITEM;
        else return ItemType.CHECKBOX_ITEM;
    }

    public CharSequence getDefText() {
        return defText;
    }

    public int getDefTextRes() {
        return defTextRes;
    }

    public CharSequence getSubDefText() {
        return subDefText;
    }

    public int getSubDefTextRes() {
        return subDefTextRes;
    }

    public String getKey() {
        return key;
    }

    public boolean getDefValue() {
        return defValue;
    }

    public static class Builder {
        private int itemType;
        private CharSequence defText = null;
        private int defTextRes = 0;
        private CharSequence subDefText = null;
        private int subDefTextRes = 0;
        private String key = null;
        private boolean defValue = false;
        private CharSequence changeOnText = null;
        private int changeOnTextRes = 0;
        private CharSequence changeOffText = null;
        private int changeOffTextRes = 0;
        private CharSequence changeOnSubText = null;
        private int changeOnSubTextRes = 0;
        private CharSequence changeOffSubText = null;
        private int changeOffSubTextRes = 0;
        private OnCheckedChangeListener onCheckedChangeListener;

        public Builder setItemType(int itemType){
            this.itemType = itemType;
            return this;
        }

        public Builder defText(CharSequence defText){
            this.defText = defText;
            return this;
        }

        public Builder defText(int defTextRes){
            this.defTextRes = defTextRes;
            this.defText = null;
            return this;
        }

        public Builder subDefText(CharSequence subDefText){
            this.subDefText = subDefText;
            return this;
        }

        public Builder subDefText(int subDefTextRes){
            this.subDefTextRes = subDefTextRes;
            this.subDefText = null;
            return this;
        }

        public Builder key(String key){
            this.key = key;
            return this;
        }

        public Builder defValue(boolean defValue){
            this.defValue = defValue;
            return this;
        }

        public Builder changeOnText(CharSequence changeOnText){
            this.changeOnText = changeOnText;
            this.changeOnTextRes = 0;
            return this;
        }

        public Builder changeOnText(int changeOnTextRes){
            this.changeOnText = null;
            this.changeOnTextRes = changeOnTextRes;
            return this;
        }

        public Builder changeOnSubText(CharSequence changeOnSubText){
            this.changeOnSubText = changeOnSubText;
            this.changeOnSubTextRes = 0;
            return this;
        }

        public Builder changeOnSubText(int changeOnSubTextRes){
            this.changeOnSubTextRes = changeOnSubTextRes;
            this.changeOnSubText = null;
            return this;
        }

        public Builder changeOffText(CharSequence changeOffText){
            this.changeOffText = changeOffText;
            this.changeOffTextRes = 0;
            return this;
        }

        public Builder changeOffText(int changeOffTextRes){
            this.changeOffTextRes = changeOffTextRes;
            this.changeOffText = null;
            return this;
        }

        public Builder changeOffSubText(CharSequence changeOffSubText){
            this.changeOffSubText = changeOffSubText;
            this.changeOffSubTextRes = 0;
            return this;
        }

        public Builder changeOffSubText(int changeOffSubTextRes){
            this.changeOffSubTextRes = changeOffSubTextRes;
            this.changeOffSubText = null;
            return this;
        }

        public Builder setOnCheckedChangeListener(MaterialSettingCompoundButtonItem.OnCheckedChangeListener onCheckedChangeListener){
            this.onCheckedChangeListener = onCheckedChangeListener;
            return this;
        }

        public MaterialSettingCompoundButtonItem build(){
            return new MaterialSettingCompoundButtonItem(this);
        }
    }
}
