package com.example.snowsonz.litetool.network;

import com.example.snowsonz.litetool.express.ExpressInfoModel;
import com.example.snowsonz.litetool.express.ExpressTypeModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * author: SnowsonZ
 * created on: 2018/5/7 23:27
 * description:
 */
public interface ExpressService {
    @GET("autonumber/autoComNum")
    Observable<ExpressTypeModel> getExpressType(@Query("resultv2") String resultv2,
                                                       @Query("text") String text);

    @GET("query")
    Observable<ExpressInfoModel> getExpressInfo(@Query("type") String type,
                                                       @Query("postid") String postid);
}
