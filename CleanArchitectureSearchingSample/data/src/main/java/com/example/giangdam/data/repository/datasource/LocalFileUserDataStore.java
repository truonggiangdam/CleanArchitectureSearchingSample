package com.example.giangdam.data.repository.datasource;

import com.example.giangdam.data.entity.UserEntity;
import com.example.giangdam.data.entity.mapper.UserEntityFileDataMapper;
import com.example.giangdam.data.file.LoadDataFromFileHelper;
import com.example.giangdam.data.file.UserData;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by cpu11326-local on 29/01/2018.
 */

public class LocalFileUserDataStore implements UserDataStore {

    private final LoadDataFromFileHelper helper;
    private final UserEntityFileDataMapper mapper;
    private final String userName;

    LocalFileUserDataStore(LoadDataFromFileHelper helper, UserEntityFileDataMapper userEntityFileDataMapper,
                           String userName){
        this.helper = helper;
        this.mapper = userEntityFileDataMapper;
        this.userName = userName;
    }


    @Override
    public Observable<List<UserEntity>> userEntityList() {
        return this.helper.searchUsersByUserName(userName).map(new Function<List<UserData>, List<UserEntity>>() {
            @Override
            public List<UserEntity> apply(List<UserData> userData) throws Exception {
                return mapper.transform(userData);
            }
        });
    }

    @Override
    public Observable<UserEntity> userEntityDetails(int userId) {
        return null;
    }
}
