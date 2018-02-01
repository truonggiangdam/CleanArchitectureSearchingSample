package com.example.giangdam.data.api;

import com.example.giangdam.data.entity.UserEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by cpu11326-local on 01/02/2018.
 */

public interface ApiClient {
    Observable<List<UserEntity>> userEntityList();

    Observable<UserEntity> userEntityById(final int userId);
}
