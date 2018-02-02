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
 * Một UseCase, tương ứng với chức năng search một danh sách kết quả dựa trên userName.
 */

public class SearchUserListByUserName extends UseCase<List<User>, SearchUserListByUserName.Params> {

    // Interface cung cấp các hàm giúp giao tiếp với tầng data liên quan tới dữ liệu User.
    private final UserRepository userRepository;

    /**
     * Hàm dựng với các tham số được inject
     * @param userRepository: Interface giúp giao tiếp với tầng data
     * @param observeOnThread: Thread mà Observer chạy.
     * @param subcribeOnThread: Thread mà Observable chạy.
     */
    @Inject
    SearchUserListByUserName(UserRepository userRepository,ObserveOnThread observeOnThread, SubcribeOnThread subcribeOnThread) {
        super(observeOnThread, subcribeOnThread);
        this.userRepository = userRepository;
    }

    /**
     * gọi xuống tầng data để lấy dữ liệu.
     * @param params : Tham số truyền vào cho từng request.
     * @return
     */
    @Override
    Observable<List<User>> buildUseCaseObservable(Params params) {
        return this.userRepository.users(params.userName);
    }

    /**
     * Class đơn giản giúp đóng gói tham số cho từng useCase.
     */
    public static final class Params {
        private final String userName;

        private Params(String userName) {
            this.userName = userName;
        }

        public static Params forUser(String userName) { return new Params(userName);}
    }
}
