package com.example.giangdam.cleanarchitecturesearchingsample.mapper;

import com.example.giangdam.cleanarchitecturesearchingsample.model.UserModel;
import com.example.giangdam.domain.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by cpu11326-local on 29/01/2018.
 */

public class UserModelDataMapper {
    @Inject
    public UserModelDataMapper() {

    }

    public UserModel transform(User user) {
        if(user == null) {
            throw new IllegalArgumentException("Can not transform null value");
        }

        return new UserModel(user.getUserName(), user.getEmail());
    }

    public List<UserModel> transform(Collection<User> userCollection) {
        List<UserModel> userModelList = new ArrayList<>();

        for(User user: userCollection) {
            UserModel userModel = transform(user);
            if(userModel != null) {
                userModelList.add(userModel);
            }
        }

        return userModelList;
    }
}
