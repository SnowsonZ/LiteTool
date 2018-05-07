package com.example.snowsonz.litetool.express;

import com.example.snowsonz.litetool.network.RetrofitConfig;
import com.example.snowsonz.litetool.utils.CodeHelper;

import java.util.List;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
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

    public void getExpressInfo(String expressNum) {
        RetrofitConfig.getExpressService().getExpressType("1", expressNum)
                .flatMap(new Function<ExpressTypeModel, ObservableSource<ExpressInfoModel>>() {
                    @Override
                    public ObservableSource<ExpressInfoModel> apply(ExpressTypeModel expressTypeModel) throws Exception {

                        for (ItemType itemType : expressTypeModel.getAuto()) {
                            return RetrofitConfig.getExpressService()
                                    .getExpressInfo(itemType.getComCode(), expressNum);
                        }
                        return null;
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ExpressInfoModel>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ExpressInfoModel expressInfoModel) {
                        List<InfoItemModel> infoItems = expressInfoModel.getData();
                        activity.addExpressView(infoItems);
                    }

                    @Override
                    public void onError(Throwable e) {
                        CodeHelper.showToast(activity, "获取失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
