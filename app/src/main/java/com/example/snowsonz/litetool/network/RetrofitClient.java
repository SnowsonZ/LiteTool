package com.example.snowsonz.litetool.network;

import com.example.snowsonz.litetool.utils.HttpConstants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author: SnowsonZ
 * created on: 2018/5/7 23:42
 * description:
 */
public class RetrofitClient {
    public static Retrofit instance;

    public static Retrofit getInstance() {
        if (instance == null) {
            synchronized (RetrofitClient.class) {
                if (instance == null) {
                    instance = newRetrofit();
                }
            }
        }
        return instance;
    }

    private static Retrofit newRetrofit() {
        instance = new Retrofit.Builder()
                .client(HttpClient.getInstance())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return instance;
    }
}
