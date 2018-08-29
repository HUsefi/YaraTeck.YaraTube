package com.yaratech.yaratube.ui.login.loginphone;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.source.local.AppDatabase;
import com.yaratech.yaratube.ui.login.LoginDialogContract;
import com.yaratech.yaratube.util.Constant;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginPhoneFragment extends Fragment implements LoginPhoneContract.View{

    private EditText phoneNumber;
    private LoginPhonePresenter presenter;
    private LoginDialogContract.steps listener;
    private ProgressBar mProgressBar;

    public LoginPhoneFragment() {
        // Required empty public constructor
    }

    public void setListener(LoginDialogContract.steps listener) {
        this.listener = listener;
    }

    public static LoginPhoneFragment newInstance() {
        
        Bundle args = new Bundle();
        
        LoginPhoneFragment fragment = new LoginPhoneFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final AppDatabase database = AppDatabase.getAppDatabase(getActivity());
        presenter = new LoginPhonePresenter(this, getContext(), database);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_phone, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        phoneNumber = view.findViewById(R.id.edit_text_phone_number);
        Button submitPhone = view.findViewById(R.id.button_submit);

        final String deviceId = Settings.Secure.getString(view.getContext().getContentResolver()
                , Settings.Secure.ANDROID_ID);
        final String deviceModel = Build.MODEL;
        final String deviceOs = "Android " + Build.VERSION.SDK_INT;

        submitPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                presenter.onSendPhoneNumber(Constant.faToEn(phoneNumber.getText().toString())
                        , deviceId
                        , deviceModel
                        , deviceOs);
            }
        });
    }


    @Override
    public void smsReceived(String phoneNumber) {
       listener.goToLoginCode(phoneNumber);
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        mProgressBar.setVisibility(View.GONE);
        Toast.makeText(this.getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }
}
