package com.example.mustafakhaled.googlenews.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.mustafakhaled.googlenews.R;
import com.example.mustafakhaled.googlenews.adapter.FavoriteRecyclerViewAdapter;
import com.example.mustafakhaled.googlenews.helper.MyApp;
import com.example.mustafakhaled.googlenews.models.ArticalModel;

import java.util.ArrayList;

public class Favorites extends AppCompatActivity {
    Context mContext;
    MyApp myApp;
    private final String TAG = "TAG";
    ArrayList<ArticalModel> models;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    FavoriteRecyclerViewAdapter favoriteRecyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        mContext = this;
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_favorite);
        myApp = new MyApp(mContext);
        models = myApp.retrievePosts();
        favoriteRecyclerViewAdapter = new FavoriteRecyclerViewAdapter(mContext,models);
        layoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(favoriteRecyclerViewAdapter);
        setRecyclerViewDivider(layoutManager);
        Log.e(TAG,"onResponse(): Adapter has been set");

    }
    private void setRecyclerViewDivider(LinearLayoutManager layoutManager){

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }
}
