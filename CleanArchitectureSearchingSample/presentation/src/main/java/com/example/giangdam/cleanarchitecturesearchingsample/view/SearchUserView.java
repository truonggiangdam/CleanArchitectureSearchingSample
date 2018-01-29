package com.example.giangdam.cleanarchitecturesearchingsample.view;

import com.example.giangdam.cleanarchitecturesearchingsample.model.UserModel;

import java.util.List;

/**
 * Created by cpu11326-local on 29/01/2018.
 */

public interface SearchUserView {
    void updateUI(List<UserModel> users);
    void showEmptyView();
    void showLoading();
    void hideLoading();
    void showError(String message);
}
