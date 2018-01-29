package com.example.giangdam.data.repository.datasource;

import com.example.giangdam.data.entity.UserEntity;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by cpu11326-local on 29/01/2018.
 */

public interface UserDataStore {
    Observable<List<UserEntity>> userEntityList(final String userName);
}
