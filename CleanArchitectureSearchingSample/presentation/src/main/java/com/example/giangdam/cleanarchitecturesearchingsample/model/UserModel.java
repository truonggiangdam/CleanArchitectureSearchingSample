package com.example.giangdam.cleanarchitecturesearchingsample.model;

/**
 * Created by cpu11326-local on 29/01/2018.
 */

public class UserModel {
    private String userName;
    private String email;

    public UserModel(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
