package com.yaratech.yaratube.dialog.loginphone;

import android.content.Context;

import com.yaratech.yaratube.data.model.MobileLoginStep1;
import com.yaratech.yaratube.data.source.Repository;
import com.yaratech.yaratube.data.source.remote.APIResult;

public class LoginPhoneNumberPresenter implements PhoneNumberContract.Presenter {


    PhoneNumberContract.View view;
    Context context;
    Repository phoneRepository;

    public LoginPhoneNumberPresenter(Context context, PhoneNumberContract.View view) {
        this.context = context;
       phoneRepository = new Repository();
    }

    @Override
    public void loginByMobile(String mobile, String deviceId, String deviceModel, String deviceOs, String gcm) {
       phoneRepository.sendMobileLoginStep1(new APIResult<MobileLoginStep1>() {
           @Override
           public void onSuccess(MobileLoginStep1 result) {
               view.dismissDialog();
           }

           @Override
           public void onFail() {
               view.dismissDialog();
           }
       },mobile, deviceId, deviceModel, deviceOs, gcm);
    }
}
