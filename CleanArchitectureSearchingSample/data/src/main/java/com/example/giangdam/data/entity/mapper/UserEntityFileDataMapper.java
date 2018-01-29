package com.example.giangdam.data.entity.mapper;

import com.example.giangdam.data.entity.UserEntity;
import com.example.giangdam.data.file.UserData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by cpu11326-local on 29/01/2018.
 */

public class UserEntityFileDataMapper {

    @Inject
    public UserEntityFileDataMapper() {}

    public UserEntity transform(UserData userData) {
        UserEntity userEntity = null;
        if(userData != null) {
            userEntity = new UserEntity(userData.userName, userData.email);
        }

        return userEntity;
    }

    public List<UserEntity> transform(Collection<UserData> userDataCollection) {
        final List<UserEntity> userEntityList = new ArrayList<>();
        for(UserData userData: userDataCollection) {
            final UserEntity userEntity = transform(userData);
            if(userEntity != null) {
                userEntityList.add(userEntity);
            }
        }

        return userEntityList;
    }
}
