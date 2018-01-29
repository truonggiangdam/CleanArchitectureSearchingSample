package com.example.giangdam.cleanarchitecturesearchingsample.di.components;

import com.example.giangdam.cleanarchitecturesearchingsample.di.modules.ApplicationModule;
import com.example.giangdam.domain.repository.UserRepository;
import com.example.giangdam.domain.thread.ObserveOnThread;
import com.example.giangdam.domain.thread.SubcribeOnThread;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by cpu11326-local on 29/01/2018.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    //Exposed
    ObserveOnThread observeOnThread();
    SubcribeOnThread subcribeOnThread();
    UserRepository userRepository();
}
