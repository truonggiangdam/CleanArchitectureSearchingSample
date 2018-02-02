package com.example.giangdam.data.api;

import com.example.giangdam.data.log.BaseLog;
import com.example.giangdam.data.log.GLog;
import com.google.gson.GsonBuilder;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cpu11326-local on 01/02/2018.
 * Class util giúp cung cấp các thông tin cần thiết để khởi tạo RetrofitRestApi.
 */

public class RetrofitProvideResources {
    String API_BASE_URL =
            "https://raw.githubusercontent.com/android10/Sample-Data/master/Android-CleanArchitecture/";

    // Http Client
    private final OkHttpClient okHttpClient;
    // ConverterClass giúp chuyển đối qua lại giữa json và object.
    private final GsonConverterFactory gsonConverterFactory;

    @Inject
    RetrofitProvideResources() {
        okHttpClient = provideOkHttpClient();
        gsonConverterFactory = provideGsonConverterFactory();
    }

    /**
     * Khởi tạo retrofit.
     * @return
     */
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(API_BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(provideCallAdapterFactory())
                .build();
    }

    /**
     * Khởi tạo CallAdapterFactory.
     * @return
     */
    private CallAdapter.Factory provideCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    /**
     * Khởi tạo RetrofitRestAPI.
     * @return
     */
    public RetrofitRestApi provideApi() {
        return provideRetrofit().create(RetrofitRestApi.class);
    }

    /**
     * Khởi tạo GsonConverterFactory.
     * @return
     */
    private GsonConverterFactory provideGsonConverterFactory() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return GsonConverterFactory.create(gsonBuilder.create());
    }

    /**
     * Khởi tạo HttpClient.
     * @return
     */
    private OkHttpClient provideOkHttpClient() {
        // Khởi tạo LoggingInterceptor để ghi lại log của request và response.
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(
                new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        try {
                            GLog.d(message);
                        } catch (BaseLog.CanNotLogException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

        // Set log level là phần BODY.
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // trả về HttpClient mới.
        return new OkHttpClient().newBuilder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }
}
