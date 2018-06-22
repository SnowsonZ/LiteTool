package com.example.snowsonz.litetool.mine;

import android.Manifest;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.snowsonz.litetool.R;
import com.example.snowsonz.litetool.utils.CodeHelper;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class MineActivity extends AppCompatActivity {

    private MinePresenter mPresenter;
    private String BASE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()
            + "/test";
    private List<Disposable> disposables = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);

        //上传照片
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            RxPermissions rxPermissions = new RxPermissions(this);
            disposables.add(rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe(granted -> {
                        if (granted) {
                            uploadFile();
                        } else {
                            CodeHelper.showToast(MineActivity.this, "读取SD卡权限被禁止，" +
                                    "部分功能可能无法使用");
                        }
                    })
            );
        } else {
            uploadFile();
        }
    }

    private void uploadFile() {
        mPresenter = new MinePresenter(this);
        File photoFile = new File(BASE_PATH, "avatar.jpg");
//        File descriptionFile = new File(BASE_PATH, "description.json");
//        RequestBody photo = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("file", "avatar.jpg",
//                        RequestBody.create(MediaType.parse("image/jpg"), photoFile))
//                .build();
//        RequestBody description = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("description", "description.json",
//                        RequestBody.create(MediaType.parse("text/json; charset=utf-8"), descriptionFile))
//                .build();
        RequestBody photo
                = RequestBody.create(MediaType.parse("image/jpg"), photoFile);
//        Request request = new Request.Builder().
//        RequestBody description
//                = RequestBody.create(MediaType.parse("text/json; charset=utf-8"),
//                descriptionFile);
        mPresenter.uploadFileSingle(photo);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.disposableAll();
        if (disposables != null && disposables.size() > 0) {
            for (Disposable d : disposables) {
                d.dispose();
            }
        }
    }
}
