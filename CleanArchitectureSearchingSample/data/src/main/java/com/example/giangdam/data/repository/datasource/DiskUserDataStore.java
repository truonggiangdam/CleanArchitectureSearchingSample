package com.example.giangdam.data.repository.datasource;

import com.example.giangdam.data.cache.UserCache;
import com.example.giangdam.data.entity.UserEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by cpu11326-local on 30/01/2018.
 */

public class DiskUserDataStore implements UserDataStore {

    private final UserCache userCache;

    DiskUserDataStore(UserCache userCache) {
        this.userCache = userCache;
    }

    @Override
    public Observable<List<UserEntity>> userEntityList() {
        return this.userCache.get();
    }

    @Override
    public Observable<UserEntity> userEntityDetails(int userId) {
        throw new UnsupportedOperationException("Operation is not available");
    }
}
