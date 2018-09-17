package com.example.mustafakhaled.googlenews.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import com.example.mustafakhaled.googlenews.models.NewResposeModel;

import java.util.HashMap;

import static com.example.mustafakhaled.googlenews.helper.Constants.NEWS_END_POINT;


/*
 * Created by Mustafa Khaled on 9/15/2018.
 *
 */ public interface GetNews {
     @GET(NEWS_END_POINT)
     Call<NewResposeModel> getAllNews(@QueryMap HashMap<String,String> params , @QueryMap HashMap<String,Integer> intParams);
}
