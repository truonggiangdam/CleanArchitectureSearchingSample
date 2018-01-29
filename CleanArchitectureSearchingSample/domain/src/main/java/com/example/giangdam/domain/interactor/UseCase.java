package com.example.giangdam.domain.interactor;

import com.example.giangdam.domain.thread.ObserveOnThread;
import com.example.giangdam.domain.thread.SubcribeOnThread;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by cpu11326-local on 29/01/2018.
 */

public abstract class UseCase<T, Params> {
    private final ObserveOnThread observeOnThread;
    private final SubcribeOnThread subcribeOnThread;
    private final CompositeDisposable disposables;

    UseCase(ObserveOnThread observeOnThread, SubcribeOnThread subcribeOnThread) {
        this.observeOnThread = observeOnThread;
        this.subcribeOnThread  = subcribeOnThread;
        this.disposables = new CompositeDisposable();
    }

    abstract Observable<T> buildUseCaseObservable(Params params);

    public void execute(DisposableObserver<T> observer, Params params) {
        if(observer == null) {
            return;
        }

        final Observable<T> observable = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.from(subcribeOnThread))
                .observeOn(observeOnThread.getScheduler());
        addDisposable(observable.subscribeWith(observer));
    }

    public void dispose() {
        if(!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    private void addDisposable(DisposableObserver<T> observer) {
        if(disposables != null && observer != null) {
            disposables.add(observer);
        }
    }
}
