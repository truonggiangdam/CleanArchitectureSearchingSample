package com.example.giangdam.cleanarchitecturesearchingsample.di.modules;

import com.example.giangdam.cleanarchitecturesearchingsample.MyApplication;
import com.example.giangdam.cleanarchitecturesearchingsample.thread.JobExecutor;
import com.example.giangdam.cleanarchitecturesearchingsample.thread.UIThread;
import com.example.giangdam.data.repository.UserDataRepository;
import com.example.giangdam.domain.repository.UserRepository;
import com.example.giangdam.domain.thread.ObserveOnThread;
import com.example.giangdam.domain.thread.SubcribeOnThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by cpu11326-local on 29/01/2018.
 */
@Module
public class ApplicationModule {
    private final MyApplication application;

    public ApplicationModule(MyApplication application) {
        this.application = application;
    }

    @Provides @Singleton
    ObserveOnThread provideObserveOnThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides @Singleton
    SubcribeOnThread provideSubcribeOnThread(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides @Singleton
    UserRepository provideUserRepository(UserDataRepository userDataRepository) {
        return userDataRepository;
    }

}
