package com.example.giangdam.cleanarchitecturesearchingsample.thread;

import com.example.giangdam.domain.thread.ObserveOnThread;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by cpu11326-local on 29/01/2018.
 * provide Main Thread.
 */

public class UIThread implements ObserveOnThread {

    @Inject
    UIThread() {}

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
