package com.example.giangdam.data.api;

import com.example.giangdam.data.entity.UserEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by cpu11326-local on 30/01/2018.
 */

public interface CallableRestApi {
    String API_BASE_URL =
            "https://raw.githubusercontent.com/android10/Sample-Data/master/Android-CleanArchitecture/";

    String API_URL_GET_USER_LIST = API_BASE_URL + "users.json";

    String API_URL_GET_USER_DETAILS = API_BASE_URL + "user_";

    Observable<List<UserEntity>> userEntityList();

    Observable<UserEntity> userEntityById(final int userId);
}
