package com.example.mustafakhaled.googlenews.retrofit;

import com.example.mustafakhaled.googlenews.helper.Constants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/*
 * Created by Mustafa Khaled on 9/15/2018.
 *
 */ public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        request =request.newBuilder()
                .header("x-api-key", Constants.API_KEY)
                .build();

        Response response = chain.proceed(request);

        return response;
    }
}
