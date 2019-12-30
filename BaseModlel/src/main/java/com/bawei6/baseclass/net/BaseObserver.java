package com.bawei6.baseclass.net;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
/**
 * @author fengchen
 * @date 2019/12/28.
 * @description：观察者——Observer的封装
 */
public class BaseObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
