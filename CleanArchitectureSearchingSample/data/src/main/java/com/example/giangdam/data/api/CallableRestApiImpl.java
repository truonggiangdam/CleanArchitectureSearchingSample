package com.example.giangdam.data.api;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.giangdam.data.entity.UserEntity;
import com.example.giangdam.data.entity.mapper.UserEntityJsonMapper;

import java.net.MalformedURLException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by cpu11326-local on 30/01/2018.
 * Implement CallbaleRestApi để hiện thực hóa việc connect tới API.
 */

public class CallableRestApiImpl implements CallableRestApi {
    private final Context context;
    // Mapper class dùng để chuyển đổi dữ liệu qua loại giữa Json và JavaObject.
    private final UserEntityJsonMapper userEntityJsonMapper;

    @Inject
    public CallableRestApiImpl(Context context, UserEntityJsonMapper userEntityJsonMapper) {
        if(context == null || userEntityJsonMapper == null) {
            throw  new IllegalArgumentException("The constructor parameters can not be null");
        }

        this.context = context;
        this.userEntityJsonMapper = userEntityJsonMapper;
    }

    @Override
    public Observable<List<UserEntity>> userEntityList() {
        return Observable.create(new ObservableOnSubscribe<List<UserEntity>>() {
            @Override
            public void subscribe(ObservableEmitter<List<UserEntity>> emitter) throws Exception {
                // kiểm tra kết nối internet
                if(isThereInternetConnection()) {
                    String responseUserEntities = getUserEntitiesFromApi();

                    if(responseUserEntities != null) {
                        // Nếu dữ liệu lấy về thành công, gọi hàm mapper convert json to object và emit
                        emitter.onNext(userEntityJsonMapper.transformUserEntityCollection(responseUserEntities));
                        emitter.onComplete();
                    } else {
                        emitter.onError(new Exception("Response null"));
                    }
                }else {
                    emitter.onError(new NetworkErrorException("There are no connection network"));
                }
            }
        });
    }

    @Override
    public Observable<UserEntity> userEntityById(final int userId) {
        return Observable.create(new ObservableOnSubscribe<UserEntity>() {
            @Override
            public void subscribe(ObservableEmitter<UserEntity> emitter) throws Exception {
                if(isThereInternetConnection()) {
                    String responseUserDetails = getUserDetailsFromApi(userId);
                    if(responseUserDetails != null) {
                        emitter.onNext(userEntityJsonMapper.transformUserEntity(responseUserDetails));
                        emitter.onComplete();
                    } else {
                        emitter.onError(new Exception("Response null"));
                    }
                } else {
                    emitter.onError(new NetworkErrorException("There are no connection network"));
                }
            }
        });
    }

    /**
     * // Khởi tạo client với GET method và request lên server.
     * @return
     * @throws MalformedURLException
     */
    private String getUserEntitiesFromApi() throws MalformedURLException {
        return CallableConnection.createGET(API_URL_GET_USER_LIST).requestSyncCall();
    }

    /**
     *  // Khởi tạo client với GET method và request lên server.
     * @param userId
     * @return
     * @throws MalformedURLException
     */
    private String getUserDetailsFromApi(int userId) throws MalformedURLException {
        String apiUrl = API_URL_GET_USER_DETAILS + userId + ".json";
        return CallableConnection.createGET(apiUrl).requestSyncCall();
    }

    /**
     * Kiểm tra kết nối internet
     * @return
     */
    private boolean isThereInternetConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnectedOrConnecting());
    }
}
