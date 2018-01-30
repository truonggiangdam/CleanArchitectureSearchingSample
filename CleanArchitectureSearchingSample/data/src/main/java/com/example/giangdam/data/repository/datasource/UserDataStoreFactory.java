package com.example.giangdam.data.repository.datasource;


import android.content.Context;
import android.support.annotation.NonNull;

import com.example.giangdam.data.api.RestApi;
import com.example.giangdam.data.api.RestApiImpl;
import com.example.giangdam.data.cache.UserCache;
import com.example.giangdam.data.entity.mapper.UserEntityFileDataMapper;
import com.example.giangdam.data.entity.mapper.UserEntityJsonMapper;
import com.example.giangdam.data.file.LoadDataFromFileHelper;
import com.example.giangdam.data.repository.UserDataRepository;

import javax.inject.Inject;

/**
 * Created by cpu11326-local on 29/01/2018.
 */

public class UserDataStoreFactory {
    private final Context context;
    private final UserCache userCache;

    @Inject
    UserDataStoreFactory(@NonNull Context context, @NonNull UserCache userCache) {
        this.context = context;
        this.userCache = userCache;
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
        final UserEntityJsonMapper userEntityJsonMapper = new UserEntityJsonMapper();
        final RestApi restApi = new RestApiImpl(this.context, userEntityJsonMapper);

        return new CloudUserDataStore(restApi, this.userCache);
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
