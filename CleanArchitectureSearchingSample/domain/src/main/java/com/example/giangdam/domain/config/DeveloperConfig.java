package com.example.giangdam.domain.config;

/**
 * Created by cpu11326-local on 31/01/2018.
 * Config file dùng để On/Of các setting.
 */

public class DeveloperConfig {

    // Có được phép in log hay không?
    private static final boolean ABLE_TO_LOG = true;

    // Bật tắt giả lập trễ mạng.
    private static final boolean ABLE_TO_MIMIC_INTERNET_DELAY = false;

    // Danh sách các Client Library dùng để gọi tới RestAPI.
    public enum CLOUD_CLIENT_LIBRARY {
        CALLABLE, RETROFIT
    }

    public static boolean ableToLog(){
        return ABLE_TO_LOG;
    }

    public static boolean ableToMimicInternetDelay() {
        return ABLE_TO_MIMIC_INTERNET_DELAY;
    }


    /*
    * Client library đang được sử dụng để gọi RestAPI.
     */
    public static CLOUD_CLIENT_LIBRARY currentCloudClientLibrary() {
        return CLOUD_CLIENT_LIBRARY.RETROFIT;
    }
}
