package com.example.giangdam.data.entity.mapper;

import com.example.giangdam.data.entity.UserEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by cpu11326-local on 30/01/2018.
 */

public class UserEntityJsonMapper {
    private final Gson gson;

    @Inject
    public UserEntityJsonMapper() {
        this.gson = new Gson();
    }

    public UserEntity transformUserEntity(String userJsonResponse) {
        final Type userEntityType = new TypeToken<UserEntity>() {}.getType();
        return this.gson.fromJson(userJsonResponse, userEntityType);
    }

    public List<UserEntity> transformUserEntityCollection(String userListJsonResponse) {
        final Type listOfUserEntityType = new TypeToken<List<UserEntity>>() {}.getType();
        return this.gson.fromJson(userListJsonResponse, listOfUserEntityType);
    }
}
