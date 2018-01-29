package com.example.giangdam.domain.thread;

import io.reactivex.Scheduler;

/**
 * Created by cpu11326-local on 29/01/2018.
 */

public interface ObserveOnThread {
    Scheduler getScheduler();
}
