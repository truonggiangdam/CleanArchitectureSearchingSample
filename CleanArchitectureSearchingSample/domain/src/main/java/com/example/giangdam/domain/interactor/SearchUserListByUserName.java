package com.example.giangdam.domain.interactor;

import com.example.giangdam.domain.model.User;
import com.example.giangdam.domain.repository.UserRepository;
import com.example.giangdam.domain.thread.ObserveOnThread;
import com.example.giangdam.domain.thread.SubcribeOnThread;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by cpu11326-local on 29/01/2018.
 */

public class SearchUserListByUserName extends UseCase<List<User>, SearchUserListByUserName.Params> {

    private final UserRepository userRepository;

    @Inject
    SearchUserListByUserName(UserRepository userRepository,ObserveOnThread observeOnThread, SubcribeOnThread subcribeOnThread) {
        super(observeOnThread, subcribeOnThread);
        this.userRepository = userRepository;
    }


    @Override
    Observable<List<User>> buildUseCaseObservable(Params params) {
        return this.userRepository.users(params.userName);
    }

    public static final class Params {
        private final String userName;

        private Params(String userName) {
            this.userName = userName;
        }

        public static Params forUser(String userName) { return new Params(userName);}
    }
}
