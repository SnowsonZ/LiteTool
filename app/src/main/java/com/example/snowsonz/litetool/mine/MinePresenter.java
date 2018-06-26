package com.example.snowsonz.litetool.mine;

import com.example.snowsonz.litetool.base.CommonObserver;
import com.example.snowsonz.litetool.mine.model.BackStatus;
import com.example.snowsonz.litetool.network.FileService;
import com.example.snowsonz.litetool.network.RetrofitConfig;
import com.example.snowsonz.litetool.utils.CodeHelper;
import com.example.snowsonz.litetool.utils.HttpConstants;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MinePresenter {
    private List<Disposable> disposables = new ArrayList<>();
    private MineActivity activity;

    public MinePresenter(MineActivity activity) {
        this.activity = activity;
    }

    public void uploadFile(String desc, List<File> files) {

    }

    public void uploadFileMulti(MultipartBody.Part photo, MultipartBody.Part description) {
        if (photo == null || description == null) {
            return;
        }
        FileService fileService = RetrofitConfig.getFileService();
        fileService.fileUploadMulti(photo, description)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CommonObserver<BackStatus>(disposables, activity, "file/upload") {
                    @Override
                    public void onNext(BackStatus s) {
                        CodeHelper.showToast(activity, s.getStatus());
                    }
                });
    }

    public void uploadFileList(RequestBody desc, List<MultipartBody.Part> parts) {
        if (parts == null || parts.size() <= 0) {
            return;
        }
        FileService fileService = RetrofitConfig.getFileService();
        fileService.fileUploadList(desc, parts)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CommonObserver<BackStatus>(disposables, activity, "file/list") {
                    @Override
                    public void onNext(BackStatus s) {
                        CodeHelper.showToast(activity, s.getStatus());
                    }
                });
    }

    public void uploadFileSingle(RequestBody file) {
        if (file == null) {
            return;
        }
        FileService fileService = RetrofitConfig.getFileService();
        fileService.fileUploadSingle(file)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CommonObserver<BackStatus>(disposables, activity, "file/upload") {
                    @Override
                    public void onNext(BackStatus s) {
                        CodeHelper.showToast(activity, s.getStatus());
                    }
                });
    }

    public void uploadFileSingle(MultipartBody.Part part) {
        if (part == null) {
            return;
        }
        FileService fileService = RetrofitConfig.getFileService();
        fileService.fileUploadSingle(part)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CommonObserver<BackStatus>(disposables, activity, "file/upload") {
                    @Override
                    public void onNext(BackStatus s) {
                        CodeHelper.showToast(activity, s.getStatus());
                    }
                });
    }

    public void disposableAll() {
        for (Disposable disposable : disposables) {
            disposable.dispose();
        }
    }
}
