package com.yaratech.yaratube.ui.login.loginselect;


import android.content.Intent;
import android.content.IntentSender;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;

import android.support.v4.app.DialogFragment;

import com.google.android.gms.tasks.Task;
import com.yaratech.yaratube.R;
import com.yaratech.yaratube.data.source.local.AppDatabase;
import com.yaratech.yaratube.ui.login.LoginDialogContract;


public class SelectLoginMethodFragment extends Fragment implements SelectLoginMethodContract.View {


    private LoginDialogContract.steps listener;
    private Button mButtonPhoneNumber, mButtonGoogleLogin;
    private static final String TAG = "SelectLMFragment";
    private static final int RC_SIGN_IN = 1;
    private GoogleSignInClient mGoogleSignInClient;
    private boolean mIsResolving = false;
    private boolean mShouldResolve = false;
    private SelectLoginMethodContract.Presenter presenter;

    public void setListener(LoginDialogContract.steps listener) {
        this.listener = listener;
    }

    public SelectLoginMethodFragment() {
        // Required empty public constructor
    }

    public static SelectLoginMethodFragment newInstance() {

        Bundle args = new Bundle();

        SelectLoginMethodFragment fragment = new SelectLoginMethodFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                // .requestScopes(new Scope(Scopes.DRIVE_APPFOLDER))
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_login_method, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mButtonPhoneNumber = view.findViewById(R.id.phonenumber);
        mButtonGoogleLogin = view.findViewById(R.id.google_method_butt);
        final AppDatabase database = AppDatabase.getAppDatabase(getActivity());
        presenter = new SelectLoginMethodPresenter(getContext(), this, database);
        mButtonPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.goToLoginPhone();
            }
        });

        mButtonGoogleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
                listener.hideDialogFragment();


            }
        });
    }

    @Override
    public void showLoginPhoneNumber() {

    }


    @Override
    public void showMessage(String str) {
        Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dismissDialog() {
        assert getParentFragment() != null;
        ((DialogFragment) getParentFragment()).dismiss();
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(@Nullable Task<GoogleSignInAccount> completedTask) {
        try {
            // Signed in successfully, show authenticated U
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            String deviceId = Settings.Secure.getString(getContext().getContentResolver()
                    , Settings.Secure.ANDROID_ID);
            String deviceModel = Build.MODEL;
            String deviceOs = "Android " + Build.VERSION.SDK_INT;
            presenter.sendLoginGoogleAPI(account.getIdToken(), deviceId, deviceOs, deviceModel);

        } catch (ApiException e) {
            // Signed out, show unauthenticated UI.

        }
    }

}

