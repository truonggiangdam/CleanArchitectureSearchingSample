package com.example.giangdam.data.repository;

import com.example.giangdam.data.entity.UserEntity;
import com.example.giangdam.data.entity.mapper.UserEntityDataMapper;
import com.example.giangdam.data.repository.datasource.UserDataStore;
import com.example.giangdam.data.repository.datasource.UserDataStoreFactory;
import com.example.giangdam.domain.model.User;
import com.example.giangdam.domain.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by cpu11326-local on 29/01/2018.
 */

public class UserDataRepository implements UserRepository{
    private final UserDataStoreFactory userDataStoreFactory;
    private final UserEntityDataMapper userEntityDataMapper;

    @Inject
    UserDataRepository(UserDataStoreFactory dataStoreFactory, UserEntityDataMapper userEntityDataMapper) {
        this.userDataStoreFactory = dataStoreFactory;
        this.userEntityDataMapper = userEntityDataMapper;
    }


    @Override
    public Observable<List<User>> users(final String userName) {
        final UserDataStore userDataStoreGetList = this.userDataStoreFactory.create();
        final UserDataStore userDataStoreGetDetails = this.userDataStoreFactory.createCloudDataStore();
        final UserDataStore userDataStoreFromFile = this.userDataStoreFactory.createLocalFileUserDataStore(userName);

        Observable<List<User>> sourceFromServer =  userDataStoreGetList.userEntityList().map(new Function<List<UserEntity>, List<User>>() {
            @Override
            public List<User> apply(List<UserEntity> userEntityList) throws Exception {
                final List<Integer> idList = getIdUserSearchResult(userEntityList, userName);

                final List<User> resultList = new ArrayList<>();

                if( idList != null && idList.size() > 0) {
                    Observable.fromIterable(idList)
                            .flatMap(new Function<Integer, Observable<UserEntity>>() {
                                @Override
                                public Observable<UserEntity> apply(Integer integer) throws Exception {
                                    return userDataStoreGetDetails.userEntityDetails(integer);
                                }
                            })
                            .subscribe(new Observer<UserEntity>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(UserEntity userEntity) {
                                    resultList.add(userEntityDataMapper.transform(userEntity));
                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onComplete() {
                                }
                            });
                }

                return resultList;
            }
        });

        Observable<List<User>> sourceFromFile = userDataStoreFromFile.userEntityList().map(new Function<List<UserEntity>, List<User>>() {
            @Override
            public List<User> apply(List<UserEntity> userEntityList) throws Exception {
                return userEntityDataMapper.transform(userEntityList);
            }
        });

        return Observable.zip(sourceFromServer, sourceFromFile, new BiFunction<List<User>, List<User>, List<User>>() {
            @Override
            public List<User> apply(List<User> users, List<User> users2) throws Exception {
                return union(users, users2) ;
            }
        });

    }

    public static List union(final List list1, final List list2) {
        final ArrayList result = new ArrayList(list1);
        result.addAll(list2);
        return result;
    }

    private List<Integer> getIdUserSearchResult(List<UserEntity> userEntityList, String userName) {
        List<Integer> idList = new ArrayList<>();
        for(UserEntity userEntity : userEntityList) {
            if(userEntity != null && userEntity.getUserName().toLowerCase().contains(userName.toLowerCase())) {
                idList.add(userEntity.getUserId());
            }
        }
        return idList;
    }
}
