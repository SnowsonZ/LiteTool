package com.example.snowsonz.litetool.network;

/**
 * author: SnowsonZ
 * created on: 2018/5/7 23:51
 * description:
 */
public class RetrofitConfig {
    public static ExpressService getExpressService() {
        return RetrofitClient.getInstance().create(ExpressService.class);
    }

    public static FileService getFileService() {
        return RetrofitClient.getInstance().create(FileService.class);
    }
}
