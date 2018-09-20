package com.yaratech.yaratube.ui.profile;

import android.content.Context;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.yaratech.yaratube.data.model.CaptureProfile;
import com.yaratech.yaratube.data.model.Profile;
import com.yaratech.yaratube.data.source.Repository;
import com.yaratech.yaratube.data.source.UserRepository;
import com.yaratech.yaratube.data.source.local.AppDatabase;
import com.yaratech.yaratube.data.source.local.UserEntity;
import com.yaratech.yaratube.data.source.remote.APIResult;
import com.yaratech.yaratube.util.Constant;

import java.io.File;

import static com.yaratech.yaratube.util.Constant.BASE_URL;

public class ProfilePresenter implements ProfileContract.Presenter {

    private ProfileContract.View view;
    private UserRepository mRepository;
    private Context context;
    private AppDatabase database;
    private UserEntity user;


    public ProfilePresenter(Context context, ProfileContract.View view) {
        this.context = context;
        this.view=view;
        mRepository=new UserRepository(context);
        this.database = AppDatabase.getAppDatabase(context);
        mRepository.setDatabase(database);
        user = mRepository.getUser();

    }


    @Override
    public void fillProfile( EditText name, Spinner gender, TextView birthDate, ImageView profileImage) {

        name.setText(user.getName());
        birthDate.setText(user.getBirthDate());

        if (user.getSex() != null && user.getSex().equals("Female"))
            gender.setSelection(0);
        else if (user.getSex() != null && user.getSex().equals("Male"))
            gender.setSelection(1);

        if (user.getPhotoUri() != null)
            Glide.with(context)
                    .load(user.getPhotoUri())
                    .apply(RequestOptions.circleCropTransform())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(profileImage);

        if(Constant.isNetworkAvailable(context))
            getAndUpdateProfile( gender, birthDate, profileImage);

    }

    private String formatBirthDate(String dateOfBirth) {
        return dateOfBirth.replace("-", "/");
    }

    @Override
    public void updateUserInDatabase( String name, String gender, String birthDate, String profileImagePath) {
        user.setPhoneNumber(database.userDao().getPhoneNumber());
        user.setToken(database.userDao().getToken());
        user.setName(name);
        user.setSex(gender);
        user.setBirthDate(birthDate);
        if (profileImagePath != null) {

            user.setPhotoUri(profileImagePath);
        } else
            user.setPhotoUri(database.userDao().getPhotoUri());
        mRepository.updateUser(user);

        view.toast("تغییرات با موفقیت اعمال شد");
    }

    @Override
    public void sendProfileToServer( String birthDate, String gender) {
        mRepository.sendProfile(birthDate
                , gender
                , getTokenId()
                , new APIResult<Profile>() {
                    @Override
                    public void onSuccess(Profile result) {

                    }

                    @Override
                    public void onFail(String errorMessage) {

                    }
                });
    }

    @Override
    public void sendProfileImageToServer(File image) {
        mRepository.sendProfileImage(image
                , getTokenId()
                , new APIResult<Profile>() {
            @Override
            public void onSuccess(Profile result) {

            }

            @Override
            public void onFail(String errorMessage) {

            }
        });

    }


    @Override
    public void getAndUpdateProfile( final Spinner gender
            , final TextView birthDate, final ImageView profileImage) {
        mRepository.getProfile(getTokenId(), new APIResult<CaptureProfile>() {
            @Override
            public void onSuccess(CaptureProfile result) {

                // update gender
                if (result.getGender() != null) {
                    if (result.getGender().equals("Female"))
                        gender.setSelection(0);
                    else if (result.getGender().equals("Male"))
                        gender.setSelection(1);
                }

                // update birth date
                if (result.getDateOfBirth() != null &&
                        !result.getDateOfBirth().equals(birthDate.getText().toString()))
                    birthDate.setText(formatBirthDate(result.getDateOfBirth()));


                // update avatar
                if (result.getAvatar() != null)
                    Glide.with(context)
                            .load(BASE_URL + result.getAvatar())
                            .apply(RequestOptions.circleCropTransform())
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(profileImage);
            }

            @Override
            public void onFail(String errorMessage) {

            }
        });
    }

    @Override
    public void Logout() {
        mRepository.logout(mRepository.getUser());
        view.toast("شما با موفقیت خارج شدید");
    }

    private String getTokenId () {

        return user.getToken();
    }
}
