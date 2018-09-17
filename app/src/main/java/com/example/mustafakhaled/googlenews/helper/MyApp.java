package com.example.mustafakhaled.googlenews.helper;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.mustafakhaled.googlenews.models.ArticalModel;
import com.google.gson.Gson;

import java.util.ArrayList;

/*
 * Created by Mustafa Khaled on 9/17/2018.
 *
 */ public class MyApp extends Application {
     private final String TAG = "MyApp";
     public SharedPreferences sharedPreferences,favoritePosts;
     ArrayList<ArticalModel> arrayList = new ArrayList<>();
     public MyApp(Context context){
         sharedPreferences = context.getSharedPreferences("savePost",MODE_PRIVATE);
         favoritePosts = context.getSharedPreferences("favoritePosts",MODE_PRIVATE);

     }

    public MyApp() {
    }

    public void savePost(Context context, ArticalModel articalModel){
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(articalModel);
        prefsEditor.putString("MyObject"+sharedPreferences.getInt("postsCount",0), json);
        prefsEditor.putInt("postsCount",sharedPreferences.getInt("postsCount",0)+1);
        prefsEditor.commit();
        Log.e(TAG,"savePost(): Post has been added to favorite ");


    }
    public ArrayList<ArticalModel> retrievePosts(){
         for(int i=0;i<sharedPreferences.getInt("postsCount",0);i++){
             Gson gson = new Gson();
             String json = sharedPreferences.getString("MyObject"+i,"");
             ArticalModel articalModel = gson.fromJson(json,ArticalModel.class);
             arrayList.add(articalModel);
         }
                Log.e(TAG,"retrievePosts(): "+ "ArrayList Size: "+ arrayList.size());

        return arrayList;


    }
    private void addToFavorite(int postition){
        SharedPreferences.Editor prefsEditor = favoritePosts.edit();
//        prefsEditor.putInt("")

    }

}
