package com.example.giangdam.data.mimic;

import com.example.giangdam.data.log.BaseLog;
import com.example.giangdam.data.log.GLog;
import com.example.giangdam.domain.config.DeveloperConfig;

/**
 * Created by cpu11326-local on 01/02/2018.
 */

public class MimicInternetDelay {
    private static final long DELAY_TIME = 1000; // milliseconds

    public static void delay(Thread thread) throws InterruptedException, BaseLog.CanNotLogException {
        if(DeveloperConfig.ableToMimicInternetDelay()) {
            GLog.d("mimic internet delay: " + DELAY_TIME + "milliseconds");
            thread.sleep(DELAY_TIME);
        }
    }
}
