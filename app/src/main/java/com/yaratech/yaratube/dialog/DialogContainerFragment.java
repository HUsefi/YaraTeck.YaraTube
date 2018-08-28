package com.yaratech.yaratube.dialog;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.source.local.AppDatabase;
import com.yaratech.yaratube.dialog.loginphone.LoginPhoneFragment;
import com.yaratech.yaratube.dialog.loginselect.SelectLoginMethodFragment;

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
            addButtonToDialogTitle(getDialog());
            //getDialog().setCancelable(false);
            SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
            int loginStep = sharedPreferences.getInt("Login Step", 1);
            if(loginStep == 2)
                goToLoginPhone();
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


        @SuppressLint("ClickableViewAccessibility")
        public static void addButtonToDialogTitle(final Dialog mdialog) {


            final TextView title = mdialog.findViewById(android.R.id.title);

            title.setCompoundDrawablesWithIntrinsicBounds(0, 0, android.R.drawable.ic_delete, 0);

            title.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        if (event.getRawX() >= title.getRight() - title.getTotalPaddingRight()) {
                            mdialog.cancel();

                            return true;
                        }
                    }
                    return true;
                }
            });
        }


    }
  

