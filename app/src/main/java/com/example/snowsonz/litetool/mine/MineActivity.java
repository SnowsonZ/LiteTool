package com.example.snowsonz.litetool.mine;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.snowsonz.litetool.R;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class MineActivity extends AppCompatActivity {

    private MinePresenter mPresenter;
    private String BASE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()
            + "/test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);

        //上传照片
        mPresenter = new MinePresenter(this);
        File photoFile = new File(BASE_PATH, "photo.png");
        File descriptionFile = new File(BASE_PATH, "description.json");
        RequestBody photo
                = RequestBody.create(MediaType.parse("File/*"), photoFile);
        RequestBody description
                = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                descriptionFile);
        mPresenter.uploadPhoto(photo, description);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.disposableAll();
    }
}
