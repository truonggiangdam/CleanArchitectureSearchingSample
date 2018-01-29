package com.example.giangdam.data.repository.datasource;


import com.example.giangdam.data.entity.mapper.UserEntityFileDataMapper;
import com.example.giangdam.data.file.LoadDataFromFileHelper;
import com.example.giangdam.data.repository.UserDataRepository;

import javax.inject.Inject;

/**
 * Created by cpu11326-local on 29/01/2018.
 */

public class UserDataStoreFactory {
    @Inject
    UserDataStoreFactory() {

    }

    public UserDataStore create() {
        return createLocalFileUserDataStore();
    }

    private UserDataStore createLocalFileUserDataStore() {
        LoadDataFromFileHelper helper = new LoadDataFromFileHelper();
        UserEntityFileDataMapper userEntityFileDataMapper = new UserEntityFileDataMapper();
        return new LocalFileUserDataStore(helper, userEntityFileDataMapper);
    }
}
