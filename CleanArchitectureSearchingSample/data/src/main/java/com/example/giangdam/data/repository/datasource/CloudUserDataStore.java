package com.example.giangdam.data.repository.datasource;

import com.example.giangdam.data.api.ApiClient;
import com.example.giangdam.data.api.ApiClientSwitcher;
import com.example.giangdam.data.api.CallableRestApi;
import com.example.giangdam.data.cache.UserCache;
import com.example.giangdam.data.entity.UserEntity;
import com.example.giangdam.data.log.BaseLog;
import com.example.giangdam.data.mimic.MimicInternetDelay;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by cpu11326-local on 30/01/2018.
 */

public class CloudUserDataStore implements UserDataStore {
    private final UserCache userCache;
    private  final ApiClientSwitcher apiClientSwitcher;

    @Inject
    CloudUserDataStore(UserCache userCache, ApiClientSwitcher apiClientSwitcher) {
        this.userCache = userCache;
        this.apiClientSwitcher = apiClientSwitcher;
    }


    @Override
    public Observable<List<UserEntity>> userEntityList() {
        // mimic delay internet here: 1s
        mimicInternetDelay();

        return apiClientSwitcher.userEntityList().doOnNext(new Consumer<List<UserEntity>>() {
            @Override
            public void accept(List<UserEntity> userEntityList) throws Exception {
                CloudUserDataStore.this.userCache.put(userEntityList);
            }
        });
    }

    @Override
    public Observable<UserEntity> userEntityDetails(int userId) {
        // mimic delay internet here: 1s
        mimicInternetDelay();

        return apiClientSwitcher.userEntityById(userId);
    }

    private void mimicInternetDelay() {
        try {
            MimicInternetDelay.delay(Thread.currentThread());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BaseLog.CanNotLogException e) {
            e.printStackTrace();
        }
    }
}
