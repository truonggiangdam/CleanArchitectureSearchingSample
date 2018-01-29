package com.example.giangdam.cleanarchitecturesearchingsample.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.giangdam.cleanarchitecturesearchingsample.MyApplication;
import com.example.giangdam.cleanarchitecturesearchingsample.di.components.ActivityComponent;
import com.example.giangdam.cleanarchitecturesearchingsample.di.components.ApplicationComponent;
import com.example.giangdam.cleanarchitecturesearchingsample.di.components.DaggerActivityComponent;
import com.example.giangdam.cleanarchitecturesearchingsample.di.modules.ActivityModule;

/**
 * Created by cpu11326-local on 29/01/2018.
 */

public abstract class BaseActivity extends Activity {

    protected ActivityComponent activityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(getApplicatioinComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }

    protected ApplicationComponent getApplicatioinComponent() {
        return ((MyApplication)getApplication()).getApplicationComponent();
    }

    protected ActivityComponent getActivityComponent() {
        return activityComponent;
    }
}
