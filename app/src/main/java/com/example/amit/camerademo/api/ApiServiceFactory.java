package com.example.amit.camerademo.api;

import com.example.amit.camerademo.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ApiServiceFactory {
    private static ApiServiceFactory instance;
    private Retrofit retrofit;
    public static String cid = "cb92564d4062bae4e5cde57650b88c9a";

    private ApiServiceFactory() {
        buildRetrofit();
    }

    public synchronized static ApiServiceFactory getInstance() {
        if (instance == null) instance = new ApiServiceFactory();
        return instance;
    }

    private void buildRetrofit() {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder()
                .readTimeout(50000, TimeUnit.MILLISECONDS)
                .writeTimeout(50000, TimeUnit.MILLISECONDS)
                .connectTimeout(50000, TimeUnit.MILLISECONDS);
        //log url body
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpBuilder.addInterceptor(loggingInterceptor);

        retrofit = new Retrofit.Builder().baseUrl(BuildConfig.ENV)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpBuilder.build())
                .build();
    }

    public ApiTask getApiTask() {
        return retrofit.create(ApiTask.class);
    }

}
