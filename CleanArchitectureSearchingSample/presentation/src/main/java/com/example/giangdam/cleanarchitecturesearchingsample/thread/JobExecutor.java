package com.example.giangdam.cleanarchitecturesearchingsample.thread;

import android.support.annotation.NonNull;

import com.example.giangdam.domain.thread.SubcribeOnThread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

/**
 * Created by cpu11326-local on 19/01/2018.
 */

public class JobExecutor implements SubcribeOnThread {
    private final ThreadPoolExecutor threadPoolExecutor;

    @Inject
    JobExecutor() {
        this.threadPoolExecutor = new ThreadPoolExecutor(3, 5, 10,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new JobThreadFactory());
    }

    @Override
    public void execute(@NonNull Runnable command) {
        this.threadPoolExecutor.execute(command);
    }

    private static class JobThreadFactory implements ThreadFactory {

        private int counter = 0;

        @Override
        public Thread newThread(@NonNull Runnable r) {
            return new Thread(r, "android_" + counter++);
        }
    }
}
