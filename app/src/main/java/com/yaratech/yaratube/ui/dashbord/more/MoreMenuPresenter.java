package com.yaratech.yaratube.ui.dashbord.more;

import android.content.Context;

import com.yaratech.yaratube.data.source.UserRepository;
import com.yaratech.yaratube.data.source.local.AppDatabase;

public class MoreMenuPresenter implements MoreMenuContract.Presenter {
    private UserRepository userRepository;

    public MoreMenuPresenter(Context context) {
        userRepository = new UserRepository(context);
        userRepository.setDatabase(AppDatabase.getAppDatabase(context));
    }

    @Override
    public boolean isLogin() {
        return userRepository.isLogin();
    }
}
