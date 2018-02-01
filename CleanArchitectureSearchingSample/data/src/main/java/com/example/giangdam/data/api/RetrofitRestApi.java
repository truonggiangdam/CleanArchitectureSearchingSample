package com.example.giangdam.data.api;

import com.example.giangdam.data.entity.UserEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by cpu11326-local on 01/02/2018.
 */

public interface RetrofitRestApi {

    @GET("users.json")
    Observable<List<UserEntity>> getUsers();

    @GET("user_{id}.json")
    Observable<UserEntity> getUserDetails(@Path("id") String id);
}
