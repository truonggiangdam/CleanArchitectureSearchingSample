package com.example.giangdam.data.entity.mapper;

import com.example.giangdam.data.entity.UserEntity;
import com.example.giangdam.domain.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by cpu11326-local on 29/01/2018.
 */
@Singleton
public class UserEntityDataMapper {

    @Inject
    UserEntityDataMapper() {}

    public User transform(UserEntity userEntity) {
        User user = null;
        if(userEntity != null) {
            user = new User(userEntity.getUserName(), userEntity.getEmail());
        }

        return user;
    }

    public List<User> transform(Collection<UserEntity> userEntityCollection) {
        final List<User> userList = new ArrayList<>();
        for(UserEntity userEntity: userEntityCollection) {
            final User user = transform(userEntity);
            if(user != null) {
                userList.add(user);
            }
        }

        return userList;
    }
}
