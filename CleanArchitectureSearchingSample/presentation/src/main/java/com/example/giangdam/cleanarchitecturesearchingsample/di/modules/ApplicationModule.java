package com.example.giangdam.cleanarchitecturesearchingsample.di.modules;

import android.content.Context;

import com.example.giangdam.cleanarchitecturesearchingsample.MyApplication;
import com.example.giangdam.cleanarchitecturesearchingsample.thread.JobExecutor;
import com.example.giangdam.cleanarchitecturesearchingsample.thread.UIThread;
import com.example.giangdam.data.api.ApiClient;
import com.example.giangdam.data.api.CallableClient;
import com.example.giangdam.data.api.CallableRestApi;
import com.example.giangdam.data.api.CallableRestApiImpl;
import com.example.giangdam.data.api.RetrofitClient;
import com.example.giangdam.data.api.RetrofitProvideResources;
import com.example.giangdam.data.api.RetrofitRestApi;
import com.example.giangdam.data.cache.UserCache;
import com.example.giangdam.data.cache.UserCacheImpl;
import com.example.giangdam.data.repository.UserDataRepository;
import com.example.giangdam.domain.config.DeveloperConfig;
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
    Context provideApplicationContext() {
        return this.application;
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
    UserCache provideUserCache(UserCacheImpl userCache){
        return userCache;
    }

    @Provides @Singleton
    UserRepository provideUserRepository(UserDataRepository userDataRepository) {
        return userDataRepository;
    }


    @Provides @Singleton
    CallableRestApi provideCallableRestApi(CallableRestApiImpl callableRestApi) {
        return callableRestApi;
    }

    @Provides @Singleton
    RetrofitRestApi provideRetrofitRestApi(RetrofitProvideResources provideResources) {
        return provideResources.provideApi();
    }

    @Provides @Singleton
    ApiClient provideApiClient(CallableClient callableClient, RetrofitClient retrofitClient) {
        switch (DeveloperConfig.currentCloudClientLibrary()) {
            case CALLABLE:
                return callableClient;
            case RETROFIT:
                return retrofitClient;
            default:
                return callableClient;
        }
    }
}
