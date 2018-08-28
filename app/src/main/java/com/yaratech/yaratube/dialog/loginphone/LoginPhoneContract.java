package com.yaratech.yaratube.dialog.loginphone;

public interface LoginPhoneContract {

    interface View {

        void smsReceived(String phoneNumber);
        void showProgressBar();
        void hideProgressBar();
        void showErrorMessage(String errorMessage);
    }

    interface Presenter {

        void onSendPhoneNumber(String phoneNumber, String deviceId, String deviceModel, String deviceOs);
        void savePhoneNumber(String phoneNumber);
    }
}
