package com.example.snowsonz.litetool.base;

import android.content.Context;
import android.util.Log;

import com.example.snowsonz.litetool.utils.CodeHelper;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public abstract class CommonObserver<T> implements Observer<T> {
    List<Disposable> disposables;
    Context context;
    String url;

    public CommonObserver(@NonNull List<Disposable> disposables, @NonNull Context context,
                          @NonNull String url) {
        this.disposables = disposables;
        this.context = context;
        this.url = url;
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (CodeHelper.isEmpty(disposables)) {
            return;
        }
        disposables.add(d);
    }

    @Override
    public void onError(Throwable e) {
        CodeHelper.showToast(context, url);
        Log.e(this.getClass().getName(), url + ": " + "请求失败");
    }

    @Override
    public void onComplete() {
        Log.d(this.getClass().getName(), url + ": " + "请求完成");
    }
}
