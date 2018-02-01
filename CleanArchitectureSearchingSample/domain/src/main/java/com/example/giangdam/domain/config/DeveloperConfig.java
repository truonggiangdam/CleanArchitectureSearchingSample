package com.example.giangdam.domain.config;

/**
 * Created by cpu11326-local on 31/01/2018.
 */

public class DeveloperConfig {
    private static final boolean ABLE_TO_LOG = true;
    private static final boolean ABLE_TO_MIMIC_INTERNET_DELAY = true;

    public enum CLOUD_CLIENT_LIBRARY {
        CALLABLE, RETROFIT
    }

    public static boolean ableToLog(){
        return ABLE_TO_LOG;
    }

    public static boolean ableToMimicInternetDelay() {
        return ABLE_TO_MIMIC_INTERNET_DELAY;
    }

    public static CLOUD_CLIENT_LIBRARY currentCloudClientLibrary() {
        return CLOUD_CLIENT_LIBRARY.RETROFIT;
    }
}
