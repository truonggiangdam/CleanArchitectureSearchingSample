package com.example.giangdam.data.repository;

import com.example.giangdam.data.entity.UserEntity;
import com.example.giangdam.data.entity.mapper.UserEntityDataMapper;
import com.example.giangdam.data.repository.datasource.UserDataStore;
import com.example.giangdam.data.repository.datasource.UserDataStoreFactory;
import com.example.giangdam.domain.model.User;
import com.example.giangdam.domain.repository.UserRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by cpu11326-local on 29/01/2018.
 */

public class UserDataRepository implements UserRepository{
    private final UserDataStoreFactory userDataStoreFactory;
    private final UserEntityDataMapper userEntityDataMapper;

    @Inject
    UserDataRepository(UserDataStoreFactory dataStoreFactory, UserEntityDataMapper userEntityDataMapper) {
        this.userDataStoreFactory = dataStoreFactory;
        this.userEntityDataMapper = userEntityDataMapper;
    }


    @Override
    public Observable<List<User>> users(String userName) {
        final UserDataStore userDataStore = this.userDataStoreFactory.create();

        return userDataStore.userEntityList(userName).map(new Function<List<UserEntity>, List<User>>() {
            @Override
            public List<User> apply(List<UserEntity> userEntities) throws Exception {
                return userEntityDataMapper.transform(userEntities);
            }
        });
    }
}
