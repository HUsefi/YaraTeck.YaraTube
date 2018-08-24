package com.yaratech.yaratube.dialog.loginphone;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.yaratech.yaratube.R;

public class EnterPhoneNumberDialog extends DialogFragment implements PhoneNumberContract.View{


    DismissDialog dismissDialog;
    PhoneNumberContract.Presenter presenter;
    public static final String ENTER_PHONE_DIALOG_TAG = "EnterPhone";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_enter_phone_number, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onAttach(Context context) {
        if (context instanceof DismissDialog)
            dismissDialog = (DismissDialog) context;
        else
            throw new ClassCastException("Not Instance OF DismissDialog");
        super.onAttach(context);
    }

    @Override
    public void dismissDialog() {
      //  ((EnterPhoneNumberDialog.DismissDialog) getContext()).dismissPhoneNumberDialog();
    }

    @Override
    public void showMessage(String msg) {
     //   Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
        dismissDialog();
    }


    public interface DismissDialog {
        void dismissPhoneNumberDialog();
    }


}
