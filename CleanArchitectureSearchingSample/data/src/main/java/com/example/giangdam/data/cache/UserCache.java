package com.example.giangdam.data.cache;



import com.example.giangdam.data.entity.UserEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by cpu11326-local on 30/01/2018.
 */

public interface UserCache {
    Observable<List<UserEntity>> get();

    void put(List<UserEntity> userEntityList);

    boolean isCached();

    boolean isExpired();

    void evictAll();
}
