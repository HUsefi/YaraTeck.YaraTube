package com.yaratech.yaratube.dialog.loginphone;

import android.content.Context;

import com.yaratech.yaratube.data.model.MobileLoginStep1;
import com.yaratech.yaratube.data.source.UserRepository;
import com.yaratech.yaratube.data.source.local.AppDatabase;
import com.yaratech.yaratube.data.source.local.UserEntity;
import com.yaratech.yaratube.data.source.remote.APIResult;

public class LoginPhonePresenter implements LoginPhoneContract.Presenter{

    private LoginPhoneContract.View view;
    private UserRepository repository;
    private Context context;
    private AppDatabase database;


    LoginPhonePresenter(LoginPhoneContract.View view, Context context, AppDatabase database) {

        this.view = view;
        this.context = context;
        this.database = database;
        repository = new UserRepository(context);
    }

    @Override
    public void onSendPhoneNumber(final String phoneNumber, String deviceId, String deviceModel, String deviceOs) {
        UserEntity userEntity = new UserEntity();
        userEntity.setPhoneNumber(phoneNumber);
        database.userDao().insert(userEntity);

        repository.sendPhoneNumber(new APIResult<MobileLoginStep1>() {
            @Override
            public void onSuccess(MobileLoginStep1 response) {

                view.smsReceived(phoneNumber);
            }

            @Override
            public void onFail(String errorMessage) {

                view.showErrorMessage(errorMessage);
            }
        }, phoneNumber, deviceId, deviceModel, deviceOs);
    }

    @Override
    public void savePhoneNumber(String phoneNumber) {
        UserEntity userEntity = new UserEntity();
        userEntity.setPhoneNumber(phoneNumber);
        database.userDao().insert(userEntity);
    }
}
