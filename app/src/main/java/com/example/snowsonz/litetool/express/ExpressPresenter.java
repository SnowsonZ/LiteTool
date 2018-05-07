package com.example.snowsonz.litetool.express;

import android.annotation.SuppressLint;
import android.widget.Toast;

import com.example.snowsonz.litetool.network.RetrofitConfig;
import com.example.snowsonz.litetool.utils.CodeHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * author: SnowsonZ
 * created on: 2018/5/7 21:31
 * description:
 */
public class ExpressPresenter {
    private ExpressActivity activity;

    public void setView(ExpressActivity activity) {
        this.activity = activity;
    }

    public String getExpressType() {
        return null;

    }

    @SuppressLint("CheckResult")
    public void getExpressInfo(String expressNum) {
        CodeHelper.showToast(activity, "获取中...");
        RetrofitConfig.getExpressService().getExpressType("1", expressNum)
                .flatMap(new Function<ExpressTypeModel, Observable<String>>() {
                    @Override
                    public Observable<String> apply(ExpressTypeModel expressTypeModel)
                            throws Exception {
                        List<String> types = new ArrayList<String>();
                        for (ItemType type : expressTypeModel.getAuto()) {
                            types.add(type.getComCode());
                        }
                        return Observable.fromIterable(types);
                    }
                })
                .flatMap(new Function<String, ObservableSource<ExpressInfoModel>>() {
                    @Override
                    public ObservableSource<ExpressInfoModel> apply(String s) throws Exception {
                        return RetrofitConfig.getExpressService().getExpressInfo(s, expressNum);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ExpressInfoModel>() {
                    @Override
                    public void accept(ExpressInfoModel expressInfoModel) throws Exception {
                        if (expressInfoModel.getStatus().equals("200")) {
                            activity.addExpressView(expressInfoModel.getData());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        CodeHelper.showToast(activity, "获取失败");
                    }
                });
    }
}
