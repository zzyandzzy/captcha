package com.zzy.materialsettinglibrary.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zzy.materialsettinglibrary.R;
import com.zzy.materialsettinglibrary.model.MaterialSettingCard;
import com.zzy.materialsettinglibrary.model.MaterialSettingItem;
import com.zzy.materialsettinglibrary.model.MaterialSettingList;

import java.util.ArrayList;

/**
 * Created by zzyandzzy on 2017/1/13.
 */

public class MaterialSettingListAdapter extends RecyclerView.Adapter<MaterialSettingListAdapter.MaterialSettingListViewHolder>{
    private ArrayList<MaterialSettingCard> data;
    private Context context;
    public MaterialSettingListAdapter(MaterialSettingList list) {
        this.data = list.getCards();
    }

    @Override
    public MaterialSettingListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        if (parent instanceof RecyclerView){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mal_material_setting_list_card,parent,false);
            view.setFocusable(true);
            return new MaterialSettingListViewHolder(view);
        }else {
            throw new RuntimeException("Not bound to RecyclerView");
        }
    }

    @Override
    public void onBindViewHolder(MaterialSettingListViewHolder holder, int position) {
        CharSequence title = data.get(position).getTitle();
        int titleRes = data.get(position).getTitleRes();

        holder.title.setVisibility(View.VISIBLE);
        if (title != null)
            holder.title.setText(title);
        else if (titleRes != 0)
            holder.title.setText(titleRes);
        else
            holder.title.setVisibility(View.GONE);
        holder.adapter.swapData(data.get(position).getItems());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void swapData(MaterialSettingList list){
        this.data = list.getCards();
        notifyDataSetChanged();
    }

    ArrayList<MaterialSettingCard> getData(){
        return data;
    }

    class MaterialSettingListViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final RecyclerView recyclerView;
        private MaterialSettingItemAdapter adapter;

        public MaterialSettingListViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.mal_list_card_title);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.mal_list_card_recycler);
            adapter = new MaterialSettingItemAdapter(new ArrayList<MaterialSettingItem>());
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(adapter);
            recyclerView.setNestedScrollingEnabled(false);
        }
    }
}
