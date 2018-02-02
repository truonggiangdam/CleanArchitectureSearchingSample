package com.example.giangdam.domain.interactor;

import com.example.giangdam.domain.thread.ObserveOnThread;
import com.example.giangdam.domain.thread.SubcribeOnThread;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by cpu11326-local on 29/01/2018.
 * Xây dựng một UseCase chung, cơ sở để các useCase cụ thể kế thừa và thực thi logic.
 */

public abstract class UseCase<T, Params> {
    // Thread mà Observer chạy trên.
    private final ObserveOnThread observeOnThread;
    //Thread mà Observable chạy trên.
    private final SubcribeOnThread subcribeOnThread;

    // Container quản lý giữa các observer và observable.
    private final CompositeDisposable disposables;

    UseCase(ObserveOnThread observeOnThread, SubcribeOnThread subcribeOnThread) {
        this.observeOnThread = observeOnThread;
        this.subcribeOnThread  = subcribeOnThread;

        this.disposables = new CompositeDisposable();
    }

    abstract Observable<T> buildUseCaseObservable(Params params);

    /**
     * Khởi tạo Observable với các thread cho subscribe/observe
     * @param observer
     * @param params
     */
    public void execute(DisposableObserver<T> observer, Params params) {
        if(observer == null) {
            return;
        }

        // Khởi tạo observable
        final Observable<T> observable = this.buildUseCaseObservable(params)
                // set subscribe chạy trên thread executor.
                .subscribeOn(Schedulers.from(subcribeOnThread))
                // set observe trên MainThread.
                .observeOn(observeOnThread.getScheduler());

        // Thêm observer vào luồng quản lý.
        addDisposable(observable.subscribeWith(observer));
    }


    /**
     * Loại bỏ hết các observer đang quản lý
     */
    public void dispose() {
        if(!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    /**
     * Thêm observer vào luồng quản lý.
     * @param observer
     */
    private void addDisposable(DisposableObserver<T> observer) {
        if(disposables != null && observer != null) {
            disposables.add(observer);
        }
    }
}
