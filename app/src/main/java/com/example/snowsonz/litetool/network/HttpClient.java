package com.example.snowsonz.litetool.network;

import com.example.snowsonz.litetool.MainApplication;
import com.example.snowsonz.litetool.network.interceptor.HttpInfoInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * author: SnowsonZ
 * created on: 2018/5/7 23:09
 * description:
 */
public class HttpClient {
    public static OkHttpClient instance = null;

    public static OkHttpClient getInstance() {
        if (instance == null) {
            synchronized (HttpClient.class) {
                if (instance == null) {
                    instance = newClient();
                }
            }
        }
        return instance;
    }

    private static OkHttpClient newClient() {
        Cache cache = new Cache(new File(MainApplication.getContext().getCacheDir(), "HttpCache"),
                1024 * 1024 * 100);
        instance = new OkHttpClient().newBuilder().cache(cache)
                .retryOnConnectionFailure(true)
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new HttpInfoInterceptor())
                .build();
        return instance;
    }
}
