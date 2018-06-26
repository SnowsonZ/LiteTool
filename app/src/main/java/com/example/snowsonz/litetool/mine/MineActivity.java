package com.example.snowsonz.litetool.mine;

import android.Manifest;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.snowsonz.litetool.R;
import com.example.snowsonz.litetool.network.ProgressRequestBody;
import com.example.snowsonz.litetool.network.listener.ProgressListener;
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
    private File photoFile;
    private File descFile;
    private ProgressListener mListener = new ProgressListener() {
        @Override
        public void updateProgress(int precent) {
            mProgressPb.setProgress(precent);
            mProgressTv.setText(String.valueOf(precent + "%"));
        }

        @Override
        public void fileStart(int index) {
            mIndexTv.setText(String.valueOf("正在上传第" + index + "个文件"));
        }

        @Override
        public void fileEnd(int index) {
            CodeHelper.showToast(MineActivity.this,
                    String.valueOf("第" + index + "个文件上传完毕"));
        }

        @Override
        public void completed() {
            CodeHelper.showToast(MineActivity.this, "所有文件上传完毕");
            mIndexTv.setText("");
        }
    };
    private ProgressBar mProgressPb;
    private TextView mProgressTv;
    private TextView mIndexTv;

    private void initView() {
        mProgressPb = findViewById(R.id.pb_progress);
        mProgressTv = findViewById(R.id.tv_progress);
        mIndexTv = findViewById(R.id.tv_index);

        mProgressPb.setMax(100);
        mProgressTv.setText("0%");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        initView();
        mPresenter = new MinePresenter(this);
        photoFile = new File(BASE_PATH, "bg.png");
        descFile = new File(BASE_PATH, "description.json");

        //上传照片
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            RxPermissions rxPermissions = new RxPermissions(this);
            disposables.add(rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            .subscribe(granted -> {
                                if (granted) {
//                            uploadList();
                                    uploadProgressList();
                                } else {
                                    CodeHelper.showToast(MineActivity.this, "读取SD卡权限被禁止，" +
                                            "部分功能可能无法使用");
                                }
                            })
            );
        } else {
//            uploadList();
            uploadProgressList();
        }
    }

    /**
     * 上传多个文件 list type
     */
    private void uploadList() {
        RequestBody desc2 = RequestBody.create(MediaType.parse("text/plain"), "多文件上传");

        RequestBody rbPhoto = RequestBody.create(MultipartBody.FORM, photoFile);
        MultipartBody.Part pPhoto                     //name必须与协议一致，否则传输失败
                = MultipartBody.Part.createFormData("files", photoFile.getName(), rbPhoto);
        RequestBody rbDesc = RequestBody.create(MultipartBody.FORM, descFile);
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


    private void uploadProgressList() {
        List<File> files = new ArrayList<>();
        List<MultipartBody.Part> parts = new ArrayList<>();
        files.add(photoFile);

        ProgressRequestBody rb = ProgressRequestBody.create(files, MultipartBody.FORM, mListener);
        MultipartBody.Part part = MultipartBody.Part.createFormData("files", files.get(0).getName(), rb);
        parts.add(part);
        RequestBody rbDesc = RequestBody.create(MediaType.parse("text/plain"), "多文件上传");
        mPresenter.uploadFileList(rbDesc, parts);
    }

    private void uploadSingleByRequestBody() {
        RequestBody rbPhoto = RequestBody.create(MediaType.parse("image/*"), photoFile);
        mPresenter.uploadFileSingle(rbPhoto);
    }

    private void uploadSingleByMultiBodyPart() {
        RequestBody rbPhoto = RequestBody.create(MultipartBody.FORM, photoFile);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file",
                photoFile.getName(), rbPhoto);
        mPresenter.uploadFileSingle(part);
    }

    private void uploadMutilKnowCount() {
        RequestBody rbPhoto = RequestBody.create(MultipartBody.FORM, photoFile);
        MultipartBody.Part pPhoto = MultipartBody.Part.createFormData("avatar",
                photoFile.getName(), rbPhoto);
        RequestBody rbDesc = RequestBody.create(MultipartBody.FORM, descFile);
        MultipartBody.Part pDesc = MultipartBody.Part.createFormData("desc",
                descFile.getName(), rbDesc);
        mPresenter.uploadFileMulti(pPhoto, pDesc);
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
