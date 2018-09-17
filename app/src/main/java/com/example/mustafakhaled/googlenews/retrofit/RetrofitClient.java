package com.example.mustafakhaled.googlenews.retrofit;

import com.example.mustafakhaled.googlenews.helper.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/*
 * Created by Mustafa Khaled on 9/15/2018.
 *
 */ public class RetrofitClient {
     private String TAG = "RetrofitClient";
     public static Retrofit retrofitClientApi(){
         HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
         HeaderInterceptor headerInterceptor = new HeaderInterceptor();

         httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

         OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                 .addInterceptor(httpLoggingInterceptor)
                 .addInterceptor(headerInterceptor)
                 .build();

         Retrofit retrofit = new Retrofit.Builder()
                 .baseUrl(Constants.BASE_URL)
                 .client(okHttpClient)
                 .addConverterFactory(GsonConverterFactory.create())
                 .build();

         return retrofit;

     }
}
