package com.yaratech.yaratube.ui.login.verification;


import android.Manifest;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.source.local.AppDatabase;
import com.yaratech.yaratube.ui.login.LoginDialogContract;
import com.yaratech.yaratube.util.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class VerificationFragment extends Fragment implements VerificationContract.View {

    private String phoneNumber;
    private VerificationContract.Presenter presenter;
    private LoginDialogContract.steps listener;
    private SharedPreferences mSharedPreferences;
    private EditText mNumberEditText;
    private static final String PREF_USER_MOBILE_PHONE = "pref_user_mobile_phone";
    private static final int SMS_PERMISSION_CODE = 0;


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

        if (!hasReadSmsPermission()) {
            showRequestPermissionsInfoAlertDialog();
        }

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

        Button submitCodeButton = view.findViewById(R.id.button_submit_code);
        Button editPhoneButton = view.findViewById(R.id.button_edit_code);

        final EditText verificationCode = view.findViewById(R.id.edit_text_enter_verification);

        final String deviceId = Settings.Secure.getString(view.getContext().getContentResolver()
                , Settings.Secure.ANDROID_ID);

//        submitCodeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(Constant.validateActivationCode(verificationCode.getText().toString())) {
//                    // fixme send user instead of fields
//                    presenter.onSendVerificationCode(phoneNumber, deviceId,
//                            Integer.parseInt(Constant.faToEn(verificationCode.getText().toString())));
//                    Constant.hideKeyboardFrom(view.getContext(), view);
//                }
//                else
//                    showErrorMessage(getString(R.string.invalid_code));
//            }
//        });

        editPhoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.goToLoginPhone();
                SharedPreferences mSharedPreferences = (getActivity()).getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = mSharedPreferences.edit();
                editor.clear();
                editor.putInt("Login Step", 2);
                editor.commit();
            }
        });
    }

    /**
     * Checks if stored SharedPreferences value needs updating and updates \o/
//     */
//    private void checkAndUpdateUserPrefNumber(){
//        if (TextUtils.isEmpty(phoneNumber) && !phoneNumber.equals(mNumberEditText.getText().toString())) {
//            mSharedPreferences
//                    .edit()
//                    .putString(PREF_USER_MOBILE_PHONE, mNumberEditText.getText().toString())
//                    .apply();
//        }
//    }



    @Override
    public void activationDone() {
        Toast.makeText(this.getContext(), R.string.success_sign_in, Toast.LENGTH_SHORT).show();
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

    /**
     * Optional informative alert dialog to explain the user why the app needs the Read/Send SMS permission
     */
    private void showRequestPermissionsInfoAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.permission_alert_dialog_title);
        builder.setMessage(R.string.permission_dialog_message);
        builder.setPositiveButton(R.string.action_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                requestReadAndSendSmsPermission();
            }
        });
        builder.show();
    }


    /**
     * Runtime permission shenanigans
     */
    private boolean hasReadSmsPermission() {
        return ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getContext(),
                        Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestReadAndSendSmsPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity()
                , Manifest.permission.READ_SMS)) {
        //    Log.d(TAG, "shouldShowRequestPermissionRationale(), no permission requested");
            return;
        }
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_SMS
                , Manifest.permission.RECEIVE_SMS}, SMS_PERMISSION_CODE);
    }



}
