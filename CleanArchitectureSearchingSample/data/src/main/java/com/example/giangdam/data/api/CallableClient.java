package com.example.giangdam.data.api;

import com.example.giangdam.data.entity.UserEntity;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by cpu11326-local on 01/02/2018.
 */

public class CallableClient implements ApiClient {
    private final CallableRestApi callableRestApi;

    @Inject
    CallableClient(CallableRestApi callableRestApi) {
        this.callableRestApi = callableRestApi;
    }

    @Override
    public Observable<List<UserEntity>> userEntityList() {
        return callableRestApi.userEntityList();
    }

    @Override
    public Observable<UserEntity> userEntityById(int userId) {
        return callableRestApi.userEntityById(userId);
    }
}
