package com.yaratech.yaratube.ui.login;

public interface LoginDialogContract {

    interface steps {

        void goToLoginPhone();
        void goToLoginCode(String phoneNumber);
        void hideDialogFragment();
    }
}
