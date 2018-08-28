package com.yaratech.yaratube.dialog.verification;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.source.local.AppDatabase;
import com.yaratech.yaratube.dialog.LoginDialogContract;
import com.yaratech.yaratube.util.Constant;

/**
 * A simple {@link Fragment} subclass.
 */
public class VerificationFragment extends Fragment implements VerificationContract.View {

    private String phoneNumber;
    private VerificationContract.Presenter presenter;
    private LoginDialogContract.steps listener;

    public VerificationFragment() {
        // Required empty public constructor
    }

    public static VerificationFragment newInstance() {

        Bundle args = new Bundle();

        VerificationFragment fragment = new VerificationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void setListener(LoginDialogContract.steps listener) {
        this.listener = listener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final AppDatabase database = AppDatabase.getAppDatabase(getActivity());
        presenter = new VerificationPresenter(this, getContext(), database);
        phoneNumber = presenter.phoneNumber();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_verification, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button submitCodeButton = view.findViewById(R.id.button_submit);
        Button editPhoneButton = view.findViewById(R.id.button_edit_code);

        final EditText verificationCode = view.findViewById(R.id.edit_text_enter_verification);

        final String deviceId = Settings.Secure.getString(view.getContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        submitCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Constant.validateActivationCode(verificationCode.getText().toString())) {
                    // fixme send user instead of fields
                    presenter.onSendVerificationCode(phoneNumber, deviceId,
                            Integer.parseInt(Constant.faToEn(verificationCode.getText().toString())));
                    Constant.hideKeyboardFrom(view.getContext(), view);
                }
                else
                    showErrorMessage("کد نامعتبر است");
            }
        });

        editPhoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.goToLoginPhone();
                SharedPreferences sharedPreferences = (getActivity()).getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.putInt("Login Step", 2);
                editor.commit();
            }
        });
    }


        @Override
    public void activationDone() {
        Toast.makeText(this.getContext(), "ورود شما با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void dismissDialog() {
        assert getParentFragment() != null;
        ((DialogFragment) getParentFragment()).dismiss();
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        hideProgressBar();
        Toast.makeText(this.getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }
}
