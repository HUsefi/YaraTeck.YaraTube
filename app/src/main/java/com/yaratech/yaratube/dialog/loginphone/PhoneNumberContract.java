package com.yaratech.yaratube.dialog.loginphone;

public interface PhoneNumberContract {
    interface View {
        void dismissDialog();

        void showMessage(String msg);
    }

    interface Presenter {
        void loginByMobile(String mobile, String deviceId, String deviceModel,
                           String deviceOs, String gcm);
    }
}
