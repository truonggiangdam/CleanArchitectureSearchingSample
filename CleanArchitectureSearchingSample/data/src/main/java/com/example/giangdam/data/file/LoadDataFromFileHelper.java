package com.example.giangdam.data.file;

import android.util.Log;

import com.example.giangdam.data.entity.UserEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by cpu11326-local on 29/01/2018.
 */

public class LoadDataFromFileHelper {
    public Observable<List<UserData>> searchUsersByUserName(final String userName) {
        return Observable.create(new ObservableOnSubscribe<List<UserData>>() {
            @Override
            public void subscribe(ObservableEmitter<List<UserData>> emitter) throws Exception {
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
        Log.d("GDD", "key work in file " + userName);
        List<UserData> userDataList = new ArrayList<>();
        for(UserData userData: UserData.UserData) {
            if(userData != null && userData.userName.toLowerCase().contains(userName.toLowerCase())) {
                userDataList.add(userData);
            }
        }
        return userDataList;
    }
}
