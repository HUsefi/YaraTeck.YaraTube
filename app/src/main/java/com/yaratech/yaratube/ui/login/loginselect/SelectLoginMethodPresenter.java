package com.yaratech.yaratube.ui.login.loginselect;

import android.content.Context;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.model.GoogleLoginResponse;
import com.yaratech.yaratube.data.source.UserRepository;
import com.yaratech.yaratube.data.source.local.AppDatabase;
import com.yaratech.yaratube.data.source.local.UserEntity;
import com.yaratech.yaratube.data.source.remote.APIResult;

public class SelectLoginMethodPresenter implements SelectLoginMethodContract.Presenter {

    private SelectLoginMethodContract.View view;
    private UserRepository userRepository;
    private AppDatabase appDatabase;
    Context context;


    public SelectLoginMethodPresenter(Context context, SelectLoginMethodContract.View view, AppDatabase database) {
        this.view = view;
        this.context = context;
        userRepository = new UserRepository(context);
        appDatabase = database;
    }


    @Override
    public void sendLoginGoogleAPI(String tokenId, String deviceId, String deviceOs, String deviceModel) {
        userRepository.sendGoogleLogin(tokenId, deviceId, deviceOs, deviceModel, new APIResult<GoogleLoginResponse>() {
            @Override
            public void onSuccess(GoogleLoginResponse result) {
                UserEntity userEntity = new UserEntity();
                userEntity.setToken(result.getToken());
                appDatabase.userDao().insert(userEntity);
                view.showMessage(context.getString(R.string.success_login_google));
                view.dismissDialog();

            }

            @Override
            public void onFail(String errorMessage) {
                view.showMessage(errorMessage);
            }
        });
    }
}
