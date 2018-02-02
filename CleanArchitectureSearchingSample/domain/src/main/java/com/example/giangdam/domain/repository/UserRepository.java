package com.example.giangdam.domain.repository;

import com.example.giangdam.domain.model.User;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by cpu11326-local on 29/01/2018.
 * Interface mà các implement tại data module cần để giao tiếp với tâng domain.
 */

public interface UserRepository {
    Observable<List<User>> users(final String userName);
}
