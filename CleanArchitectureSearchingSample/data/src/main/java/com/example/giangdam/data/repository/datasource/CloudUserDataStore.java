package com.example.giangdam.data.repository.datasource;

import com.example.giangdam.data.api.RestApi;
import com.example.giangdam.data.cache.UserCache;
import com.example.giangdam.data.entity.UserEntity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by cpu11326-local on 30/01/2018.
 */

public class CloudUserDataStore implements UserDataStore {
    private final RestApi restApi;
    private final UserCache userCache;

    CloudUserDataStore(RestApi restApi, UserCache userCache) {
        this.restApi = restApi;
        this.userCache = userCache;
    }


    @Override
    public Observable<List<UserEntity>> userEntityList() {
        return this.restApi.userEntityList().doOnNext(new Consumer<List<UserEntity>>() {
            @Override
            public void accept(List<UserEntity> userEntityList) throws Exception {
                CloudUserDataStore.this.userCache.put(userEntityList);
            }
        });
    }

    @Override
    public Observable<UserEntity> userEntityDetails(int userId) {
        return this.restApi.userEntityById(userId);
    }
}
