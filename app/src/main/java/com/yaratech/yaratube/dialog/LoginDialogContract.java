package com.yaratech.yaratube.dialog;

public interface LoginDialogContract {

    interface steps {

        void goToLoginPhone();
        void goToLoginCode(String phoneNumber);
    }
}
