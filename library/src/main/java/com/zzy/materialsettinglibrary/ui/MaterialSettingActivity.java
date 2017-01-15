package com.zzy.materialsettinglibrary.ui;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.zzy.materialsettinglibrary.R;
import com.zzy.materialsettinglibrary.adapters.MaterialSettingListAdapter;
import com.zzy.materialsettinglibrary.model.MaterialSettingList;

/**
 * Created by zzyandzzy on 2017/1/13.
 */

public abstract class MaterialSettingActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private MaterialSettingListAdapter adapter;
    private MaterialSettingList list = null;

    protected abstract MaterialSettingList getMaterialSettingList(Context context);
    protected abstract CharSequence getActivityTitle();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mal_material_setting_activity);
        CharSequence title = getActivityTitle();
        if (title != null)
            setTitle(title);
        else
            setTitle(R.string.mal_title);
        assignViews();
        initViews();
        ListTask listTask = new ListTask(this);
        listTask.execute();
    }

    private void initViews() {
        setSupportActionBar(toolbar);
        if (NavUtils.getParentActivityIntent(this) != null){
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null)
                actionBar.setDisplayHomeAsUpEnabled(true);
        }
        adapter = new MaterialSettingListAdapter(new MaterialSettingList.Builder().build());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void assignViews() {
        toolbar = (Toolbar) findViewById(R.id.mal_tolbar);
        recyclerView = (RecyclerView) findViewById(R.id.mal_recyclerview);
        recyclerView.setTranslationY(40f);
        recyclerView.setAlpha(0f);
    }

    private class ListTask extends AsyncTask<String ,String ,String >{
        Context context;
        public ListTask(Context context){
            this.context = context;
        }
        @Override
        protected String doInBackground(String... params) {
            list = getMaterialSettingList(context);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            adapter.swapData(list);
            recyclerView.animate().alpha(1f).translationY(0f).setDuration(200).start();
            super.onPostExecute(s);
            context = null;
        }
    }
}
