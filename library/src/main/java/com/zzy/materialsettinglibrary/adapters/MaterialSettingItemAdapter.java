package com.zzy.materialsettinglibrary.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import com.zzy.materialsettinglibrary.R;
import com.zzy.materialsettinglibrary.model.MaterialSettingActionItem;
import com.zzy.materialsettinglibrary.model.MaterialSettingCompoundButtonItem;
import com.zzy.materialsettinglibrary.model.MaterialSettingItem;
import com.zzy.materialsettinglibrary.model.MaterialSettingTitleItem;
import com.zzy.materialsettinglibrary.utils.MaterialSettingSharedPreferences;

import java.util.ArrayList;

/**
 * Created by zzyandzzy on 2017/1/14.
 */

public class MaterialSettingItemAdapter extends RecyclerView.Adapter<MaterialSettingItemAdapter.MaterialSettingItemViewHolder>{
    private ArrayList<MaterialSettingItem> data;
    private Context context;
    private MaterialSettingSharedPreferences sharedPreferences;
    public MaterialSettingItemAdapter(ArrayList<MaterialSettingItem> data){
        this.data = data;
    }

    @Override
    public MaterialSettingItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        sharedPreferences = new MaterialSettingSharedPreferences(context);
        if (!(parent instanceof RecyclerView)){
            throw new RuntimeException("Not bound to RecyclerView");
        }
        int layoutId = -1;
        switch (viewType){
            case MaterialSettingItem.ItemType.ACTION_ITEM:
                layoutId = R.layout.mal_material_setting_action_item;
                break;
            case MaterialSettingItem.ItemType.TITLE_ITEM:
                layoutId = R.layout.mal_material_setting_title_item;
                break;
            case MaterialSettingItem.ItemType.CHECKBOX_ITEM:
                layoutId = R.layout.mal_material_setting_checkbox_item;
                break;
            case MaterialSettingItem.ItemType.SWITCH_ITEM:
                layoutId = R.layout.mal_material_setting_switch_item;
                break;
            case MaterialSettingItem.ItemType.RADIOBUTTON_ITEM:
                layoutId = R.layout.mal_material_setting_radiobutton_item;
                break;
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId,parent,false);
        view.setFocusable(true);
        return new MaterialSettingItemViewHolder(view,viewType);
    }

    @Override
    public void onBindViewHolder(MaterialSettingItemViewHolder holder, int position) {
        switch (holder.viewType){
            case MaterialSettingItem.ItemType.ACTION_ITEM:
                setupActionItem(holder,position);
                break;
            case MaterialSettingItem.ItemType.TITLE_ITEM:
                setupTitleItem(holder,position);
                break;
            case MaterialSettingItem.ItemType.CHECKBOX_ITEM:
                setupCompoundButtonItem(holder,position,holder.checkBox);
                break;
            case MaterialSettingItem.ItemType.SWITCH_ITEM:
                setupCompoundButtonItem(holder,position,holder.aSwitch);
                break;
            case MaterialSettingItem.ItemType.RADIOBUTTON_ITEM:
                setupCompoundButtonItem(holder,position,holder.radioButton);
        }
    }

    private void setupCompoundButtonItem(final MaterialSettingItemViewHolder holder, int position,CompoundButton compoundButton) {
        final MaterialSettingCompoundButtonItem item = (MaterialSettingCompoundButtonItem) data.get(position);
        CharSequence text = item.getDefText();
        int textRes = item.getDefTextRes();
        CharSequence subText = item.getSubDefText();
        int subTextRes = item.getSubDefTextRes();
        setText(holder.text,holder.subText,null,text,textRes,subText,subTextRes,null,0);
        boolean defValue = item.getDefValue();
        String key = item.getKey();
        if (key == null)
            key = context.getString(R.string.app_name);
        boolean isChecked = sharedPreferences.getBoolean(key,defValue);
        compoundButton.setChecked(isChecked);
        setButtonText(holder,item,isChecked);
        if (item.getOnCheckedChangeListener() != null){
            holder.onCheckedChangeListener = item.getOnCheckedChangeListener();
        }
        final String finalKey = key;
        compoundButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (holder.onCheckedChangeListener != null) {
                    sharedPreferences.putBoolean(finalKey,isChecked);
                    Log.d("TAG","CompoundButton:" + finalKey);
                    setButtonText(holder,item,isChecked);
                    holder.onCheckedChangeListener.onCheckedChanged(finalKey,isChecked);
                }
            }
        });
    }

    private void setText(TextView text, TextView subText,ImageView icon,
                         CharSequence textStr, int textRes, CharSequence subTextStr, int subTextRes,
                         Drawable drawable,int drawableRes) {
        if (text != null){
            text.setVisibility(View.VISIBLE);
            if (textStr != null)
                text.setText(textStr);
            else if (textRes != 0)
                text.setText(textRes);
            else
                text.setVisibility(View.GONE);
        }
        if (subText != null){
            subText.setVisibility(View.VISIBLE);
            if (subTextStr != null)
                subText.setText(subTextStr);
            else if (subTextRes != 0)
                subText.setText(subTextRes);
            else
                subText.setVisibility(View.GONE);
        }
        if (icon != null){
            icon.setVisibility(View.VISIBLE);
            if (drawable != null)
                icon.setImageDrawable(drawable);
            else if (drawableRes != 0)
                icon.setImageResource(drawableRes);
            else
                icon.setVisibility(View.GONE);
        }
    }

    public void setButtonText(MaterialSettingItemViewHolder holder,MaterialSettingCompoundButtonItem item,boolean isChecked){
        if (isChecked){
            CharSequence changeOnText = item.getChangeOnText();
            CharSequence changeOnSubText = item.getChangeOnSubText();
            int changeOnTextRes = item.getChangeOnTextRes();
            int changeOnSubTextRes = item.getChangeOnSubTextRes();
            if (changeOnText != null)
                holder.text.setText(changeOnText);
            else if (changeOnTextRes != 0)
                holder.text.setText(changeOnTextRes);
            if (changeOnSubText != null)
                holder.subText.setText(changeOnSubText);
            else if (changeOnSubTextRes != 0)
                holder.subText.setText(changeOnSubTextRes);
        }else {
            CharSequence changeOffText = item.getChangeOffText();
            CharSequence changeOffSubText = item.getChangeOffSubText();
            int changeOffTextRes = item.getChangeOffTextRes();
            int changeOffSubTextRes = item.getChangeOffSubTextRes();
            if (changeOffText != null)
                holder.text.setText(changeOffText);
            else if (changeOffTextRes != 0)
                holder.text.setText(changeOffTextRes);
            if (changeOffSubText != null)
                holder.subText.setText(changeOffSubText);
            else if (changeOffSubTextRes != 0)
                holder.subText.setText(changeOffSubTextRes);
        }
    }

    private void setupActionItem(MaterialSettingItemViewHolder holder, int position) {
        MaterialSettingActionItem item = (MaterialSettingActionItem) data.get(position);
        CharSequence text = item.getText();
        int textRes = item.getTextRes();
        CharSequence subText = item.getSubText();
        int subTextRes = item.getSubTextRes();
        holder.icon.setVisibility(View.VISIBLE);
        Drawable drawable = item.getIcon();
        int drawableRes = item.getIconRes();
        setText(holder.text,holder.subText,holder.icon,text,textRes,subText,subTextRes,drawable,drawableRes);
        int paddingLeft = 0,paddingRight = 0,paddingTop = 0,paddingBottom = 0;
        if (Build.VERSION.SDK_INT < 21){
            paddingLeft = holder.view.getPaddingLeft();
            paddingRight = holder.view.getPaddingRight();
            paddingTop = holder.view.getPaddingTop();
            paddingBottom = holder.view.getPaddingBottom();
        }
        if (item.getOnClickListener() != null){
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground,typedValue,true);
            holder.view.setBackgroundResource(typedValue.resourceId);
            holder.onClickListener = item.getOnClickListener();
        }else {
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground,typedValue,false);
            holder.view.setBackgroundResource(typedValue.resourceId);
            holder.onClickListener = item.getOnClickListener();
            holder.onClickListener = null;
        }

        if (Build.VERSION.SDK_INT < 21)
            holder.view.setPadding(paddingLeft,paddingTop,paddingRight,paddingBottom);
    }

    private void setupTitleItem(MaterialSettingItemViewHolder holder,int position){
        MaterialSettingTitleItem item = (MaterialSettingTitleItem) data.get(position);
        CharSequence text = item.getText();
        int textRes = item.getTextRes();
        Drawable drawable = item.getIcon();
        int drawableRes = item.getIconRes();
        setText(holder.text,null,holder.icon,text,textRes,null,0,drawable,drawableRes);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getType();
    }

    public int getItemType(int position){
        return data.get(position).getType();
    }

    public void swapData(ArrayList<MaterialSettingItem> newData){
        data = newData;
        notifyDataSetChanged();
    }

    public ArrayList<MaterialSettingItem> getData(){
        return data;
    }

    class MaterialSettingItemViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{
        private View view;
        private ImageView icon;
        private CheckBox checkBox;
        private Switch aSwitch;
        private RadioButton radioButton;
        private TextView text;
        private TextView subText;
        private int viewType;
        private MaterialSettingActionItem.OnClickListener onClickListener;
        private MaterialSettingCompoundButtonItem.OnCheckedChangeListener onCheckedChangeListener;

        public MaterialSettingItemViewHolder(View itemView,int viewType) {
            super(itemView);
            this.view = itemView;
            this.viewType = viewType;
            text = (TextView) view.findViewById(R.id.mal_text_item_text);
            subText = (TextView) view.findViewById(R.id.mal_text_item_subText);
            switch (viewType){
                case MaterialSettingItem.ItemType.ACTION_ITEM:
                    icon = (ImageView) view.findViewById(R.id.mal_action_item_icon);
                    view.setOnClickListener(this);
                    this.onClickListener = null;
                    break;
                case MaterialSettingItem.ItemType.TITLE_ITEM:
                    icon = (ImageView) view.findViewById(R.id.mal_title_item_icon);
                    text = (TextView) view.findViewById(R.id.mal_title_item_text);
                    break;
                case MaterialSettingItem.ItemType.CHECKBOX_ITEM:
                    checkBox = (CheckBox) view.findViewById(R.id.mal_checkbox_item_checkbox);
                    break;
                case MaterialSettingItem.ItemType.SWITCH_ITEM:
                    aSwitch = (Switch) view.findViewById(R.id.mal_switch_item_switch);
                    break;
                case MaterialSettingItem.ItemType.RADIOBUTTON_ITEM:
                    radioButton = (RadioButton) view.findViewById(R.id.mal_radiobutton_item_radiobutton);
                    break;
            }
        }

        @Override
        public void onClick(View v) {
            if (onClickListener != null)
                onClickListener.onClick();
        }
    }
}
