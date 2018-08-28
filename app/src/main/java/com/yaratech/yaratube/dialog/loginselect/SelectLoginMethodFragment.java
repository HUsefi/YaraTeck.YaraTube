package com.yaratech.yaratube.dialog.loginselect;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.yaratech.yaratube.R;
import com.yaratech.yaratube.dialog.LoginDialogContract;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectLoginMethodFragment extends Fragment implements SelectLoginMethodContract.View {


    private LoginDialogContract.steps listener;
    private Button mButtonPhoneNumber;


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
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
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

        mButtonPhoneNumber=view.findViewById(R.id.phonenumber);
        mButtonPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.goToLoginPhone();
            }
        });

    }

    @Override
    public void showLoginPhoneNumber() {

    }
}
