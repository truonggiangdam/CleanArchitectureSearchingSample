package com.example.giangdam.data.log;

/**
 * Created by cpu11326-local on 31/01/2018.
 */

public class GLog extends BaseLog {

    // VERBOSE
    public static int v(String message) throws CanNotLogException {
        return log(VERBOSE, message, null);
    }

    public static int v(String message, Throwable throwable) throws CanNotLogException {
        return log(VERBOSE, message, throwable);
    }

    public static int v(Throwable throwable) throws CanNotLogException {
        return log(VERBOSE, "", throwable);
    }


    // DEBUG
    public static int d(String message) throws CanNotLogException {
        return log(DEBUG, message, null);
    }

    public static int d(String message, Throwable throwable) throws CanNotLogException {
        return log(DEBUG, message, throwable);
    }

    public static int d(Throwable throwable) throws CanNotLogException {
        return log(DEBUG, "", throwable);
    }

    // INFO
    public static int i(String message) throws CanNotLogException {
        return log(INFO, message, null);
    }

    public static int i(String message, Throwable throwable) throws CanNotLogException {
        return log(INFO, message, throwable);
    }

    public static int i(Throwable throwable) throws CanNotLogException {
        return log(INFO, "", throwable);
    }

    // WARN
    public static int w(String message) throws CanNotLogException {
        return log(WARN, message, null);
    }

    public static int w(String message, Throwable throwable) throws CanNotLogException {
        return log(WARN, message, throwable);
    }

    public static int w(Throwable throwable) throws CanNotLogException {
        return log(WARN, "", throwable);
    }

    // ERROR
    public static int e(String message) throws CanNotLogException {
        return log(ERROR, message, null);
    }

    public static int e(String message, Throwable throwable) throws CanNotLogException {
        return log(ERROR, message, throwable);
    }

    public static int e(Throwable throwable) throws CanNotLogException {
        return log(ERROR, "", throwable);
    }

    // ASSERT
    public static int wtf(String message) throws CanNotLogException {
        return log(ASSERT, message, null);
    }

    public static int wtf(String message, Throwable throwable) throws CanNotLogException {
        return log(ASSERT, message, throwable);
    }

    public static int wtf(Throwable throwable) throws CanNotLogException {
        return log(ASSERT, "", throwable);
    }
}
