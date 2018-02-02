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

    /**
     * Convert from userData to userEntity
     * @param userData
     * @return
     */
    public UserEntity transform(UserData userData) {
        UserEntity userEntity = null;
        if(userData != null) {
            userEntity = new UserEntity();
            userEntity.setUserName(userData.userName);
            userEntity.setEmail(userData.email);
        }

        return userEntity;
    }

    /**
     * Convert from userDataList to userEntityList
     * @param userDataCollection
     * @return
     */
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
