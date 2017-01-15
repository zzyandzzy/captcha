package com.zzy.materialsettinglibrary.model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by zzyandzzy on 2017/1/13.
 */

public class MaterialSettingList {
    private ArrayList<MaterialSettingCard> cards = new ArrayList();
    private MaterialSettingList(MaterialSettingList.Builder builder){
        this.cards = builder.cards;
    }

    public MaterialSettingList(MaterialSettingCard... materialSettingCards){
        //Collections.addAll(cards,materialSettingCards);
        cards.addAll(Arrays.asList(materialSettingCards));
    }

    public ArrayList<MaterialSettingCard> getCards(){
        return cards;
    }

    public static class Builder{
        private ArrayList<MaterialSettingCard> cards = new ArrayList<>();

        public MaterialSettingList.Builder addCard(MaterialSettingCard card){
            this.cards.add(card);
            return this;
        }

        public MaterialSettingList build(){
            return new MaterialSettingList(this);
        }
    }
}
