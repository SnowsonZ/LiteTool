package com.example.snowsonz.litetool.mine;

import com.example.snowsonz.litetool.base.CommonObserver;
import com.example.snowsonz.litetool.network.FileService;
import com.example.snowsonz.litetool.network.RetrofitConfig;
import com.example.snowsonz.litetool.utils.CodeHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class MinePresenter {
    private List<Disposable> disposables = new ArrayList<>();
    private MineActivity activity;

    public MinePresenter(MineActivity activity) {
        this.activity = activity;
    }

    public void uploadPhoto(RequestBody photo, RequestBody description) {
        if (photo == null || description == null) {
            return;
        }
        FileService fileService = RetrofitConfig.getFileService();
        fileService.fileUpload(photo, description)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CommonObserver<String>(disposables, activity, "") {
                    @Override
                    public void onNext(String s) {
                        CodeHelper.showToast(activity, s);
                    }
                });
    }

    public void disposableAll() {
        for (Disposable disposable : disposables) {
            disposable.dispose();
        }
    }
}
