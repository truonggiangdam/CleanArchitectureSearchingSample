package com.example.giangdam.data.api;

import com.example.giangdam.data.entity.UserEntity;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by cpu11326-local on 01/02/2018.
 */

public class RetrofitClient implements ApiClient {

    private final RetrofitRestApi retrofitRestApi;

    @Inject
    RetrofitClient(RetrofitRestApi retrofitRestApi) {
        this.retrofitRestApi = retrofitRestApi;
    }

    @Override
    public Observable<List<UserEntity>> userEntityList() {
        return retrofitRestApi.getUsers();
    }

    @Override
    public Observable<UserEntity> userEntityById(int userId) {
        return retrofitRestApi.getUserDetails(String.valueOf(userId));
    }
}
