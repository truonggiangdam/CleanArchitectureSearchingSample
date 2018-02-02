package com.example.giangdam.data.log;

import android.util.Log;

import com.example.giangdam.domain.config.DeveloperConfig;

/**
 * Created by cpu11326-local on 01/02/2018.
 * Base Log class.
 */

public abstract class BaseLog {
    private static final String TAG = "GDD";
    private static final String EXCEPTION_MESSAGE = "Need log permission";

    static final int VERBOSE = 2;
    static final int DEBUG = 3;
    static final int INFO = 4;
    static final int WARN = 5;
    static final int ERROR = 6;
    static final int ASSERT = 7;

    static int log(int type, String message, Throwable throwable) throws CanNotLogException {
        if(DeveloperConfig.ableToLog()) {
            switch (type) {
                case VERBOSE:
                    return Log.v(TAG, message, throwable);
                case DEBUG:
                    return Log.d(TAG, message, throwable);
                case INFO:
                    return Log.i(TAG, message, throwable);
                case WARN:
                    return Log.w(TAG, message, throwable);
                case ERROR:
                    return Log.e(TAG, message, throwable);
                case ASSERT:
                    return Log.wtf(TAG, message, throwable);
                default:
                    throw new CanNotLogException(EXCEPTION_MESSAGE);
            }
        } else {
            throw new CanNotLogException(EXCEPTION_MESSAGE);
        }
    }



    public static class CanNotLogException extends Exception {
        CanNotLogException(String message) {
            super(message);
        }
    }
}
