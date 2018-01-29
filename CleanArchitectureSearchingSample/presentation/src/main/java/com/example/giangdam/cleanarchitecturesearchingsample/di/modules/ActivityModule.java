package com.example.giangdam.cleanarchitecturesearchingsample.di.modules;

import android.app.Activity;

import com.example.giangdam.cleanarchitecturesearchingsample.di.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by cpu11326-local on 29/01/2018.
 */
@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides @PerActivity
    Activity activity() {
        return this.activity;
    }
}
