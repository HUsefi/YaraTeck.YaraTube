package com.yaratech.yaratube.data.source;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.Toast;

import com.yaratech.yaratube.data.model.Activation;
import com.yaratech.yaratube.data.model.CaptureProfile;
import com.yaratech.yaratube.data.model.CommentPostResponse;
import com.yaratech.yaratube.data.model.GoogleLoginResponse;
import com.yaratech.yaratube.data.model.MobileLoginStep1;
import com.yaratech.yaratube.data.model.Profile;
import com.yaratech.yaratube.data.source.local.AppDatabase;
import com.yaratech.yaratube.data.source.local.UserEntity;
import com.yaratech.yaratube.data.source.remote.APIClient;
import com.yaratech.yaratube.data.source.remote.APIInterface;
import com.yaratech.yaratube.data.source.remote.APIResult;
import com.yaratech.yaratube.ui.login.DialogContainerFragment;
import com.yaratech.yaratube.util.Constant;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private APIClient apiClient;
    private APIInterface service;
    private Context context;
    private AppDatabase database;

    public UserRepository(Context context) {

        apiClient=new APIClient();
        service = apiClient.getClient().create(APIInterface.class);
        this.context = context;
    }

    public void setDatabase(AppDatabase database) {
        this.database = database;
    }

    public void updateUser(UserEntity userEntity) {
        database.userDao().update(userEntity);
    }

    public UserEntity getUser() {
        return database.userDao().getUser();
    }

    public boolean isLogin() {
        return database.userDao().getToken() != null;
    }

    public void login(FragmentManager fragmentManager) {

        DialogContainerFragment dialogContainerFragment = DialogContainerFragment.newInstance();
  //      dialogContainerFragment.setCancelable(false);
        dialogContainerFragment.show(fragmentManager, dialogContainerFragment.getClass().getName());
    }

    public void logout(UserEntity user) {
        database.userDao().deleteToken();
    }

    public String phoneNumber() {
        return database.userDao().getPhoneNumber();
    }

    public String token() {
        return database.userDao().getToken();
    }

    public void sendPhoneNumber(final APIResult<MobileLoginStep1> callback
            , String phoneNumber
            , String deviceId
            , String deviceModel
            , String deviceOs) {

        Call<MobileLoginStep1> call = service.sendMobileLoginStep1(phoneNumber, deviceId, deviceModel, deviceOs);

        if(Constant.isNetworkAvailable(context)) {
            call.enqueue(new Callback<MobileLoginStep1>() {
                @Override
                public void onResponse(Call<MobileLoginStep1> call, Response<MobileLoginStep1> response) {
                    if(response.isSuccessful()) {
                        callback.onSuccess(response.body());
                    } else {
                        callback.onFail(response.message());
                    }
                }

                @Override
                public void onFailure(Call<MobileLoginStep1> call, Throwable t) {

                    callback.onFail(t.getMessage());
                }
            });
        } else {
            toastNetworkNotAvailable(context);
        }
    }



    public void sendVerificationCode(final APIResult<Activation> callback
            , String phoneNumber
            , String deviceId
            , int verificationCode) {
        Log.e("TAGG",""+phoneNumber);
        Call<Activation> call = service.activateStep2(phoneNumber, deviceId, verificationCode,"");

        if(Constant.isNetworkAvailable(context)) {
            call.enqueue(new Callback<Activation>() {
                @Override
                public void onResponse(Call<Activation> call, Response<Activation> response) {
                    if(response.isSuccessful()) {
                        callback.onSuccess(response.body());
                    } else {
                        callback.onFail(response.message());
                    }
                }

                @Override
                public void onFailure(Call<Activation> call, Throwable t) {

                    callback.onFail(t.getMessage());
                }
            });
        }
    }

    public void sendUsercomment(int score, String commentText, int productId, String token,
                                final APIResult<CommentPostResponse> callback) {

        Call<CommentPostResponse> call = service.sendComment("", score, commentText, productId, token);

        if(Constant.isNetworkAvailable(context)) {
            call.enqueue(new Callback<CommentPostResponse>() {
                @Override
                public void onResponse(Call<CommentPostResponse> call, Response<CommentPostResponse> response) {
                    if(response.isSuccessful()) {
                        callback.onSuccess(response.body());
                    } else {
                        callback.onFail(response.message());
                    }
                }

                @Override
                public void onFailure(Call<CommentPostResponse> call, Throwable t) {

                    callback.onFail(t.getMessage());
                }
            });
        }
    }


    public void sendGoogleLogin(String tokenId, String deviceId, String deviceos, String deviceModle, final
                                APIResult<GoogleLoginResponse> callback){
        Call<GoogleLoginResponse> call = service.sendGoogleLogin(tokenId,deviceId,deviceos,deviceModle);
        if(Constant.isNetworkAvailable(context)){
            call.enqueue(new Callback<GoogleLoginResponse>() {
                @Override
                public void onResponse(Call<GoogleLoginResponse> call, Response<GoogleLoginResponse> response) {
                    if(response.isSuccessful()) {
                        callback.onSuccess(response.body());
                    } else {
                        callback.onFail(response.message());
                    }
                }

                @Override
                public void onFailure(Call<GoogleLoginResponse> call, Throwable t) {
                    callback.onFail(t.getMessage());
                }
            });
        }
    }

    public CaptureProfile getProfile(String token, final APIResult<CaptureProfile> callback) {

        Call<CaptureProfile> call = service.getProfile("Token " + token);
        Log.d("token for get profile", "getProfile: "+token);

        if (Constant.isNetworkAvailable(context)) {
            call.enqueue(new Callback<CaptureProfile>() {
                @Override
                public void onResponse(Call<CaptureProfile> call, Response<CaptureProfile> response) {
                    if (response.isSuccessful()) {
                        callback.onSuccess(response.body());
                    } else {
                        callback.onFail(response.message());
                    }
                }

                @Override
                public void onFailure(Call<CaptureProfile> call, Throwable t) {

                    callback.onFail(t.getMessage());
                }
            });
        } else {
            toastNetworkNotAvailable(context);
        }
        return null;
    }

    public void sendProfile( String birthDate, String gender,
                            String tokenId, final APIResult<Profile> callback) {

        Call<Profile> call = service.sendProfile( birthDate, gender, "Token " + tokenId);

        if (Constant.isNetworkAvailable(context)) {
            call.enqueue(new Callback<Profile>() {
                @Override
                public void onResponse(Call<Profile> call, Response<Profile> response) {
                    if(response.isSuccessful()) {
                        callback.onSuccess(response.body());
                    } else {
                        callback.onFail(response.message());
                    }
                }

                @Override
                public void onFailure(Call<Profile> call, Throwable t) {
                    callback.onFail(t.getMessage());
                }
            });
        } else {
            toastNetworkNotAvailable(context);
        }
    }

    public void sendProfileImage(File image, String token, final APIResult<Profile> callback) {

        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), image);
        MultipartBody.Part body = MultipartBody.Part.createFormData("avatar", image.getName(), reqFile);

        Call<Profile> call = service.postImage(body, "Token " + token);

        if (Constant.isNetworkAvailable(context)) {
            call.enqueue(new Callback<Profile>() {
                @Override
                public void onResponse(Call<Profile> call, Response<Profile> response) {
                    if (response.isSuccessful())
                        callback.onSuccess(response.body());
                    else
                        callback.onFail(response.message());
                }

                @Override
                public void onFailure(Call<Profile> call, Throwable t) {

                    callback.onFail(t.getMessage());
                }
            });
        } else {
            toastNetworkNotAvailable(context);
        }
    }


    private void toastNetworkNotAvailable(Context context) {

        Toast.makeText(context, Constant.INTERNET_ERROR_MESSAGE, Toast.LENGTH_SHORT).show();
    }



}