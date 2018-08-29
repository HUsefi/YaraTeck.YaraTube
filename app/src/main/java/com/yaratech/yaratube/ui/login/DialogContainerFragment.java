package com.yaratech.yaratube.ui.login;


import android.annotation.SuppressLint;
import android.support.v4.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.source.local.AppDatabase;
import com.yaratech.yaratube.ui.login.loginphone.LoginPhoneFragment;
import com.yaratech.yaratube.ui.login.loginselect.SelectLoginMethodFragment;
import com.yaratech.yaratube.ui.login.verification.VerificationFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogContainerFragment extends DialogFragment implements LoginDialogContract.steps {


        private SharedPreferences.Editor editor;

        private AppDatabase database;
    public static final String DIALOG_CONTAINER_FRAGMENT_TAG = "dialogContainer";


    public static DialogContainerFragment newInstance() {
        
        Bundle args = new Bundle();
        
        DialogContainerFragment fragment = new DialogContainerFragment();
        fragment.setArguments(args);
        return fragment;
    }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            database = AppDatabase.getAppDatabase(getActivity());
        }

        @SuppressLint("CommitPrefEdits")
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container
                , @Nullable Bundle savedInstanceState) {
            View result =  inflater.inflate(R.layout.fragment_dialog_container, container, false);
            SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
            int loginStep = sharedPreferences.getInt("Login Step", 1);
            if(loginStep == 2)
                goToLoginPhone();
            else if(loginStep == 3)
                goToLoginCode(database.userDao().getPhoneNumber());
            else
                goToLoginMethod();
            return result;
        }

        public void goToLoginMethod() {
            SelectLoginMethodFragment selectLoginMethodFragment = SelectLoginMethodFragment.newInstance();
            selectLoginMethodFragment.setListener(this);
           getChildFragmentManager().beginTransaction().replace(R.id.login_container,selectLoginMethodFragment).commit();

        }


        @SuppressLint("CommitPrefEdits")
        @Override
        public void goToLoginPhone() {

            LoginPhoneFragment loginPhoneFragment = LoginPhoneFragment.newInstance();
            loginPhoneFragment.setListener(this);
            getChildFragmentManager().beginTransaction().replace(R.id.login_container, loginPhoneFragment).commit();
        }

    @Override
    public void goToLoginCode(String phoneNumber) {
        editor.clear();
        editor.putInt("Login Step", 3);
        editor.commit();
        VerificationFragment verificationFragment = VerificationFragment.newInstance();
        verificationFragment.setListener(this);
        getChildFragmentManager().beginTransaction().replace(R.id.login_container, verificationFragment).commit();
    }

    }
  

