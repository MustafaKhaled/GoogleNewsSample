package com.example.mustafakhaled.googlenews.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.mustafakhaled.googlenews.interfaces.GetNews;
import com.example.mustafakhaled.googlenews.R;
import com.example.mustafakhaled.googlenews.adapter.NewsRecyclerViewAdapter;
import com.example.mustafakhaled.googlenews.helper.RecyclerViewPagination;
import com.example.mustafakhaled.googlenews.helper.Utility;
import com.example.mustafakhaled.googlenews.models.NewResposeModel;
import com.example.mustafakhaled.googlenews.retrofit.RetrofitClient;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    NewsRecyclerViewAdapter newsRecyclerViewAdapter;
    NewResposeModel newResposeModel ;
    LinearLayoutManager layoutManager;
    HashMap<String,String> params = new HashMap<>();
    HashMap<String,Integer> intParams = new HashMap<>();
    Context mContext;
    View view ;
    ProgressBar progressBar;
    RecyclerView newsRecyclerView;
    boolean isLoading = false;
    int page =1;

    private String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsRecyclerView =  (RecyclerView) findViewById(R.id.recycler_view);
        view = (View) findViewById(R.id.view);
        progressBar = (ProgressBar) findViewById(R.id.main_progress);
        mContext = this;
        loadFirstPage();

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.favorite:
                startActivity(new Intent(MainActivity.this,Favorites.class));
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    private void loadFirstPage(){
        params.put("sources","usa-today");
        params.put("language","en");
        params.put("sortBy","publishedAt");
        intParams.put("page",page);

        Utility.showProgressBar(mContext,"Loading");
        Call<NewResposeModel> apiCall;
        GetNews getNews = RetrofitClient.retrofitClientApi().create(GetNews.class);
        apiCall = getNews.getAllNews(params,intParams);
        apiCall.enqueue(new Callback<NewResposeModel>() {
            @Override
            public void onResponse(Call<NewResposeModel> call, Response<NewResposeModel> response) {
                Utility.removeProgressDialog();
                Log.e(TAG,"onResponse(): "+ response.body().toString());
                newResposeModel = response.body();
                Log.e(TAG,"Description Test: "+newResposeModel.getArticalModels().get(1).getDescription());

                newsRecyclerViewAdapter = new NewsRecyclerViewAdapter(newResposeModel,mContext);
                layoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
                newsRecyclerView.setLayoutManager(layoutManager);
                newsRecyclerView.setItemAnimator(new DefaultItemAnimator());
                newsRecyclerView.setAdapter(newsRecyclerViewAdapter);
                setRecyclerViewDivider(layoutManager);
                newsRecyclerView.addOnScrollListener(new RecyclerViewPagination(layoutManager,mContext) {
                    @Override
                    protected void loadMoreItems() {
                        Log.e(TAG,"addOnScrollListener(): Load More items");
                        isLoading=true;
                        page+=1;

                        loadNextPage();
                    }

                    @Override
                    public boolean isLoading() {
                        return isLoading;
                    }
                });
            }

            @Override
            public void onFailure(Call<NewResposeModel> call, Throwable t) {
                Log.e(TAG,"onFailure(): Response Failed");
                Utility.removeProgressDialog();
            }
        });

    }

    private void loadNextPage(){
        params.put("sources","usa-today");
        params.put("language","en");
        params.put("sortBy","publishedAt");
        intParams.put("page",page);
        Log.e(TAG,"loadNextPage(): "+ "Current Page is: "+ page);
        Call<NewResposeModel> apiCall;
        GetNews getNews = RetrofitClient.retrofitClientApi().create(GetNews.class);
        apiCall = getNews.getAllNews(params,intParams);
        apiCall.enqueue(new Callback<NewResposeModel>() {
            @Override
            public void onResponse(Call<NewResposeModel> call, Response<NewResposeModel> response) {
                Log.e(TAG,"onResponse(): "+ response.body().toString());
                //New Model for the new network call.
                NewResposeModel tempModel = new NewResposeModel();
                tempModel = response.body();

                //Append(add) the new artical list to the main model
                newResposeModel.getArticalModels().addAll(tempModel.getArticalModels());
                newsRecyclerViewAdapter.notifyDataSetChanged();
                isLoading=false;

                view.setVisibility(View.GONE);
                progressBar.setVisibility(View.INVISIBLE);


            }

            @Override
            public void onFailure(Call<NewResposeModel> call, Throwable t) {
                Log.e(TAG,"onFailure(): Response Failed");
                //Maybe Network Issue occurs
                view.setVisibility(View.GONE);
                progressBar.setVisibility(View.INVISIBLE);
            }
        });

    }
    private void setRecyclerViewDivider(LinearLayoutManager layoutManager){

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(newsRecyclerView.getContext(), layoutManager.getOrientation());
        newsRecyclerView.addItemDecoration(dividerItemDecoration);
    }
}
