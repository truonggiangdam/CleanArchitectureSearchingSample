package com.example.giangdam.cleanarchitecturesearchingsample;

import android.app.Application;

import com.example.giangdam.cleanarchitecturesearchingsample.di.components.ApplicationComponent;
import com.example.giangdam.cleanarchitecturesearchingsample.di.components.DaggerApplicationComponent;
import com.example.giangdam.cleanarchitecturesearchingsample.di.modules.ApplicationModule;

/**
 * Created by cpu11326-local on 29/01/2018.
 */

public class MyApplication extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
