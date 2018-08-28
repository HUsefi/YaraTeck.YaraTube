package com.yaratech.yaratube.dialog.verification;

import android.content.Context;

import com.yaratech.yaratube.data.model.Activation;
import com.yaratech.yaratube.data.source.UserRepository;
import com.yaratech.yaratube.data.source.local.AppDatabase;
import com.yaratech.yaratube.data.source.local.UserEntity;
import com.yaratech.yaratube.data.source.remote.APIResult;

public class VerificationPresenter implements VerificationContract.Presenter{

    private VerificationContract.View view;
    private Context context;
    private UserRepository repository;

    public VerificationPresenter(VerificationContract.View view, Context context, AppDatabase database) {
        this.view = view;
        this.context = context;
        repository = new UserRepository(context);
        repository.setDatabase(database);
    }

    @Override
    public void onSendVerificationCode(String phoneNumber, String deviceId, int verificationCode) {
        repository.sendVerificationCode(new APIResult<Activation>() {
            @Override
            public void onSuccess(Activation result) {

                view.activationDone();
                UserEntity userEntity = repository.getUser();
                userEntity.setToken(result.getToken());
                userEntity.setPhoneNumber(repository.phoneNumber());
                repository.updateUser(userEntity);
                view.dismissDialog();
            }

            @Override
            public void onFail(String errorMessage) {

                view.showErrorMessage(errorMessage);
            }
        }, phoneNumber, deviceId, verificationCode);
    }

    @Override
    public String phoneNumber() {
        return repository.phoneNumber();
    }
}
