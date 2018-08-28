package com.yaratech.yaratube.dialog.verification;

public interface VerificationContract {

    interface View {

        void activationDone();
        void showProgressBar();
        void hideProgressBar();
        void dismissDialog();
        void showErrorMessage(String errorMessage);
    }

    interface Presenter {

        void onSendVerificationCode(String phoneNumber, String deviceId, int verificationCode);
        String phoneNumber();
    }
}
