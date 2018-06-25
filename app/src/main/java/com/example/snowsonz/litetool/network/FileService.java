package com.example.snowsonz.litetool.network;


import com.example.snowsonz.litetool.mine.model.BackStatus;

import org.json.JSONObject;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface FileService {
    //文件上传
    @Multipart
    @POST("file/uploads")
    Observable<BackStatus> fileUploadMulti(@Url String url,
                                           @Part MultipartBody.Part photo,
                                           @Part MultipartBody.Part desc);

    @Multipart
    @POST("file/upload")
    Observable<BackStatus> fileUploadSingle(@Url String url,
                                            @Part("file\"; filename=\"avatar.jpg") RequestBody file);

    @Multipart
    @POST("file/upload")
    Observable<BackStatus> fileUploadSingle(@Url String url, @Part MultipartBody.Part file);

    @Multipart
    @POST("file/list")
    Observable<BackStatus> fileUploadList(@Url String url,
                                          @Part("desc") RequestBody desc,
                                          @Part List<MultipartBody.Part> files);
}
