package com.example.snowsonz.litetool.network;


import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface FileService {
    //文件上传
    @Multipart
    @POST("user/file")
    Observable<String> fileUpload(@Part("photo") RequestBody file,
                                  @Part("description") RequestBody description);
}
