package com.zzy.materialsettinglibrary.model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by zzyandzzy on 2017/1/13.
 */

public class MaterialSettingCard {
    private CharSequence title;
    private int titleRes;
    private ArrayList<MaterialSettingItem> items;

    private MaterialSettingCard(MaterialSettingCard.Builder builder){
        this.title = null;
        this.titleRes = 0;
        this.items = new ArrayList();
        this.title = builder.title;
        this.titleRes = builder.titleRes;
        this.items = builder.items;
    }

    public MaterialSettingCard(CharSequence title,MaterialSettingItem... materialSettingItems){
        this.title = null;
        this.titleRes = 0;
        this.items = new ArrayList();
        this.title = title;
        //Collections.addAll(items,materialSettingItems);
        items.addAll(Arrays.asList(materialSettingItems));
    }

    public MaterialSettingCard(int titleRes,MaterialSettingItem... materialSettingItems){
        this.title = null;
        this.titleRes = 0;
        this.items = new ArrayList();
        this.titleRes = titleRes;
        //Collections.addAll(items,materialSettingItems);
        items.addAll(Arrays.asList(materialSettingItems));
    }

    public CharSequence getTitle(){
        return title;
    }

    public int getTitleRes(){
        return titleRes;
    }

    public ArrayList<MaterialSettingItem> getItems(){
        return items;
    }

    public static class Builder{
        private CharSequence title = null;
        private int titleRes = 0;
        private ArrayList<MaterialSettingItem> items = new ArrayList<>();

        public Builder title(CharSequence title){
            this.title = title;
            this.titleRes = 0;
            return this;
        }

        public Builder title(int titleRes){
            this.titleRes = titleRes;
            this.title = null;
            return this;
        }

        public Builder addItem(MaterialSettingItem item){
            this.items.add(item);
            return this;
        }

        public MaterialSettingCard build(){
            return new MaterialSettingCard(this);
        }
    }
}
