package com.example.giangdam.domain.interactor;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by cpu11326-local on 29/01/2018.
 */

public class DefaultObserver<T> extends DisposableObserver<T> {

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
