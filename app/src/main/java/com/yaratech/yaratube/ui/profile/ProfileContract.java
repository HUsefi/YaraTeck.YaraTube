package com.yaratech.yaratube.ui.profile;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.yaratech.yaratube.data.model.CaptureProfile;
import com.yaratech.yaratube.data.model.Profile;

import java.io.File;

public interface ProfileContract {
    interface View {

        void toast(String message);
    }

    interface Presenter {

        void fillProfile( EditText name, Spinner gender, TextView birthDate,
                         ImageView profileImage);
        void updateUserInDatabase( String name, String gender, String birthDate,
                                  String profileImagePath);
        void sendProfileToServer( String birthDate, String gender);
        void sendProfileImageToServer(File image);
        void getAndUpdateProfile( Spinner gender, TextView birthDate,
                                 ImageView profileImage);

        void Logout();
    }


}
