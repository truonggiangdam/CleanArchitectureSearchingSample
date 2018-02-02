package com.example.giangdam.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cpu11326-local on 29/01/2018.
 * Entity class hold the user data.
 */

public class UserEntity {

    public UserEntity() {
    }

    @SerializedName("id")
    private int userId;

    @SerializedName("full_name")
    private String userName;

    @SerializedName("email")
    private String email;

    @SerializedName("cover_url")
    private String coverUrl;

    @SerializedName("description")
    private String description;

    @SerializedName("followers")
    private int followers;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }
}
