package com.example.giangdam.data.repository.datasource;


import android.content.Context;
import android.support.annotation.NonNull;

import com.example.giangdam.data.api.CallableRestApi;
import com.example.giangdam.data.api.CallableRestApiImpl;
import com.example.giangdam.data.cache.UserCache;
import com.example.giangdam.data.entity.mapper.UserEntityFileDataMapper;
import com.example.giangdam.data.entity.mapper.UserEntityJsonMapper;
import com.example.giangdam.data.file.LoadDataFromFileHelper;

import javax.inject.Inject;

/**
 * Created by cpu11326-local on 29/01/2018.
 */

public class UserDataStoreFactory {
    private final UserCache userCache;
    private final CloudUserDataStore cloudUserDataStore;
    @Inject
    UserDataStoreFactory(@NonNull UserCache userCache, CloudUserDataStore cloudUserDataStore) {
        this.userCache = userCache;
        this.cloudUserDataStore = cloudUserDataStore;
    }

    public UserDataStore create() {
        UserDataStore userDataStore;

        if(!this.userCache.isExpired() && this.userCache.isCached()) {
            userDataStore = createDiskUserDataStore();
        } else {
            userDataStore = createCloudDataStore();
        }

        return userDataStore;
    }

    public UserDataStore createCloudDataStore() {
        return cloudUserDataStore;
    }

    private UserDataStore createDiskUserDataStore() {
        return new DiskUserDataStore(this.userCache);
    }

    private UserDataStore createLocalFileUserDataStore(String userName) {
        LoadDataFromFileHelper helper = new LoadDataFromFileHelper();
        UserEntityFileDataMapper userEntityFileDataMapper = new UserEntityFileDataMapper();
        return new LocalFileUserDataStore(helper, userEntityFileDataMapper, userName);
    }
}
