package com.example.snowsonz.litetool;

import android.app.Application;

/**
 * author: SnowsonZ
 * created on: 2018/5/7 23:18
 * description:
 */
public class MainApplication extends Application {
    public static MainApplication mApp;

    public static MainApplication getContext() {
        return mApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }
}
