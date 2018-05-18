package com.example.snowsonz.litetool.express;

import com.example.snowsonz.litetool.express.model.ExpressTypeModel;
import com.example.snowsonz.litetool.network.ExpressService;
import com.example.snowsonz.litetool.network.RetrofitConfig;
import com.example.snowsonz.litetool.utils.CodeHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
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

    public void requestExpressInfo(String expressNum) {
        ExpressService service = RetrofitConfig.getExpressService();
        CodeHelper.showToast(activity, "获取中...");
        service.getExpressType("1", expressNum)
                .flatMap(expressTypeModel -> {
                    List<String> types = new ArrayList<>();
                    for (ExpressTypeModel.ItemType type : expressTypeModel.getAuto()) {
                        types.add(type.getComCode());
                    }
                    return Observable.fromIterable(types);
                })
                .flatMap(s -> service.getExpressInfo(s, expressNum))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(expressInfoModel -> {
                    if (expressInfoModel.getStatus().equals("200")) {
                        activity.addExpressView(expressInfoModel.getData());
                    }
                }, throwable -> CodeHelper.showToast(activity, "获取失败"));
    }
}
