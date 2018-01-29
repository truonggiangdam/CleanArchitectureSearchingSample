package com.example.giangdam.cleanarchitecturesearchingsample.di.components;

import android.app.Activity;

import com.example.giangdam.cleanarchitecturesearchingsample.di.modules.ActivityModule;
import com.example.giangdam.cleanarchitecturesearchingsample.di.scope.PerActivity;
import com.example.giangdam.cleanarchitecturesearchingsample.view.activity.BaseActivity;
import com.example.giangdam.cleanarchitecturesearchingsample.view.activity.SearchActivity;

import dagger.Component;

/**
 * Created by cpu11326-local on 29/01/2018.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SearchActivity activity);

    // Exposed
    Activity activity();
}
