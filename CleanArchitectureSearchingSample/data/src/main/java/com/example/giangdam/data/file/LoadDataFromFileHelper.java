package com.example.giangdam.data.file;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by cpu11326-local on 29/01/2018.
 * Helper class hỗ trợ load data từ class database source.
 */

public class LoadDataFromFileHelper {
    public Observable<List<UserData>> searchUsersByUserName(final String userName) {
        return Observable.create(new ObservableOnSubscribe<List<UserData>>() {
            @Override
            public void subscribe(ObservableEmitter<List<UserData>> emitter) throws Exception {
                // gọi hàm search theo username.
                List<UserData> results = searchUsers(userName);

                if(results != null) {
                    emitter.onNext(searchUsers(userName));
                    emitter.onComplete();
                } else {
                    emitter.onError(new Exception("User not found"));
                }
            }
        });
    }

    private List<UserData> searchUsers(String userName) {
        List<UserData> userDataList = new ArrayList<>();
        for(UserData userData: UserData.UserData) {
            if(userData != null && userData.userName.toLowerCase().contains(userName.toLowerCase())) {
                userDataList.add(userData);
            }
        }
        return userDataList;
    }
}
