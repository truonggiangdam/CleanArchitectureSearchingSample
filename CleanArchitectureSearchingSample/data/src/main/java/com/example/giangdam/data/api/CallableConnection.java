package com.example.giangdam.data.api;

import com.example.giangdam.data.log.BaseLog;
import com.example.giangdam.data.log.GLog;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.annotations.Nullable;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by cpu11326-local on 30/01/2018.
 * Class quản lý connection đến server
 */

public class CallableConnection implements Callable<String> {

    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_VALUE_JSON = "application/json; charset=utf-8";

    private URL url;
    private String response;

    private CallableConnection(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    static CallableConnection createGET(String url) throws MalformedURLException {
        return new CallableConnection(url);
    }

    @Nullable
    String requestSyncCall() {
        // Kết nối tới API
        connectToApi();

        // Log response
        logD("Response: " + response);
        return response;
    }

    private void connectToApi() {
        // Khởi tao HTTP Client.
        OkHttpClient okHttpClient = this.createClient();

        // Khởi tạo request .
        final Request request = new Request.Builder()
                .url(this.url)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_VALUE_JSON)
                .get()
                .build();

        // Log request
        logD("Request: " + request.toString());

        try {
            // request và lấy về response.
            this.response = okHttpClient.newCall(request).execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
            logE(e.getCause());
        }
    }

    private void logD(String message) {
        try {
            GLog.d(message);
        } catch (BaseLog.CanNotLogException e) {
            e.printStackTrace();
        }
    }

    private void logE(Throwable throwable) {
        // Log error
        try {
            GLog.e(throwable);
        } catch (BaseLog.CanNotLogException e1) {
            e1.printStackTrace();
        }
    }

    private OkHttpClient createClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(15000, TimeUnit.SECONDS)
                .readTimeout(10000, TimeUnit.SECONDS)
                .build();
    }

    @Override
    public String call() throws Exception {
        return requestSyncCall();
    }
}
