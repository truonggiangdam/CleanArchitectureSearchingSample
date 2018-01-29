package com.example.giangdam.data.entity;

/**
 * Created by cpu11326-local on 29/01/2018.
 */

public class UserEntity {

    public UserEntity(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    private String userName;

    private String email;

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
