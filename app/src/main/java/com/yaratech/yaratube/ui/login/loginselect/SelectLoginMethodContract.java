package com.yaratech.yaratube.ui.login.loginselect;

public interface SelectLoginMethodContract {

    interface View {
        void showLoginPhoneNumber();

        void showMessage(String str);

        void dismissDialog();
    }

    interface Presenter {
        void sendLoginGoogleAPI(String tokenId, String deviceId, String deviceos, String deviceModle);
    }


}
