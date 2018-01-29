package com.example.giangdam.cleanarchitecturesearchingsample.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.giangdam.cleanarchitecturesearchingsample.di.scope.PerActivity;
import com.example.giangdam.cleanarchitecturesearchingsample.mapper.UserModelDataMapper;
import com.example.giangdam.cleanarchitecturesearchingsample.view.SearchUserView;
import com.example.giangdam.domain.interactor.DefaultObserver;
import com.example.giangdam.domain.interactor.SearchUserListByUserName;
import com.example.giangdam.domain.model.User;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by cpu11326-local on 29/01/2018.
 */
@PerActivity
public class SearchUserPresenter implements Presenter{

    private SearchUserView viewSearchUser;

    private SearchUserListByUserName searchUserListByUserName;
    private UserModelDataMapper userModelDataMapper;

    @Inject
    public SearchUserPresenter(SearchUserListByUserName searchUserListByUserName,
                               UserModelDataMapper userModelDataMapper) {
        this.searchUserListByUserName = searchUserListByUserName;
        this.userModelDataMapper = userModelDataMapper;
    }

    public void setView(@NonNull SearchUserView view){
        this.viewSearchUser = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.searchUserListByUserName.dispose();
        this.viewSearchUser = null;
    }

    public void search(String userName) {
        this.showViewLoading();
        this.startSearch(userName);
    }

    private void showViewLoading() {
        this.viewSearchUser.showLoading();
    }

    private void hideViewLoading() {
        this.viewSearchUser.hideLoading();
    }

    private void showErrorMessage(String message) {
        this.viewSearchUser.showError(message);
    }

    private void startSearch(String userName) {
        this.searchUserListByUserName.execute(new UserListObserver(), SearchUserListByUserName.Params.forUser(userName));
    }

    private class UserListObserver extends DefaultObserver<List<User>> {
        @Override
        public void onNext(List<User> users) {
            Log.d("GDD", "on Next");
            SearchUserPresenter.this.updateViewWithResults(users);
        }

        @Override
        public void onError(Throwable e) {
            Log.d("GDD", "on Error");
            SearchUserPresenter.this.hideViewLoading();
            SearchUserPresenter.this.showErrorMessage(e.getMessage());
        }

        @Override
        public void onComplete() {
            Log.d("GDD", "on Complete");
            SearchUserPresenter.this.hideViewLoading();
            super.onComplete();
        }
    }

    private void updateViewWithResults(List<User> users) {
        if(users.size() > 0) {
            this.viewSearchUser.updateUI(userModelDataMapper.transform(users));
        }else {
            this.viewSearchUser.showEmptyView();
        }
    }
}
