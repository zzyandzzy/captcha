package com.zzy.materialsettinglibrary.model;

/**
 * Created by zzyandzzy on 2017/1/13.
 */

public abstract class MaterialSettingItem {
    public abstract int getType();
    public static final class ItemType{
        public static final int ACTION_ITEM = 0;
        public static final int TITLE_ITEM = 1;
        public static final int CHECKBOX_ITEM = 2;
        public static final int SWITCH_ITEM = 3;
        public static final int RADIOBUTTON_ITEM = 4;
    }
}
