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
                            uploadList();
                        } else {
                            CodeHelper.showToast(MineActivity.this, "读取SD卡权限被禁止，" +
                                    "部分功能可能无法使用");
                        }
                    })
            );
        } else {
            uploadList();
        }
    }

    private void uploadList() {
        mPresenter = new MinePresenter(this);
        File photoFile = new File(BASE_PATH, "avatar.jpg");
        File descFile = new File(BASE_PATH, "description.json");
        //ArrayList上传多文件
        RequestBody desc2 = RequestBody.create(MediaType.parse("text/plain"), "多文件上传");

        RequestBody rbPhoto = RequestBody.create(MediaType.parse("multipart/form-data"), photoFile);
        MultipartBody.Part pPhoto                     //name必须与协议一致，否则传输失败
                = MultipartBody.Part.createFormData("files", photoFile.getName(), rbPhoto);
        RequestBody rbDesc = RequestBody.create(MediaType.parse("multipart/form-data"), descFile);
        MultipartBody.Part pDesc
                = MultipartBody.Part.createFormData("files", descFile.getName(), rbDesc);

        //多文件
//        mPresenter.uploadFileMulti(partPhoto, partDesc);
        List<MultipartBody.Part> parts = new ArrayList<>();
        parts.add(pDesc);
        parts.add(pPhoto);
        mPresenter.uploadFileList(desc2, parts);
        //单个文件
//        mPresenter.uploadFileSingle(partPhoto);
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
