package com.example.giangdam.data.api;

import com.example.giangdam.data.entity.UserEntity;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by cpu11326-local on 01/02/2018.
 * Quản lý việc switch các client l phù hợp với nhu cầu.ibrary
 */

public class ApiClientSwitcher{

    private ApiClient apiClient;

    @Inject
    public ApiClientSwitcher(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public  Observable<List<UserEntity>> userEntityList() {
        return apiClient.userEntityList();
    }


    public Observable<UserEntity> userEntityById(int userId) {
        return apiClient.userEntityById(userId);
    }

}
