package com.yaratech.yaratube.data.source;

import android.content.Context;
import android.widget.Toast;

import com.yaratech.yaratube.data.model.Activation;
import com.yaratech.yaratube.data.model.MobileLoginStep1;
import com.yaratech.yaratube.data.source.local.AppDatabase;
import com.yaratech.yaratube.data.source.local.UserEntity;
import com.yaratech.yaratube.data.source.remote.APIClient;
import com.yaratech.yaratube.data.source.remote.APIInterface;
import com.yaratech.yaratube.data.source.remote.APIResult;
import com.yaratech.yaratube.util.Constant;

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

    public String phoneNumber() {
        return database.userDao().getPhoneNumber();
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

    private void toastNetworkNotAvailable(Context context) {

        Toast.makeText(context, Constant.INTERNET_ERROR_MESSAGE, Toast.LENGTH_SHORT).show();
    }

    public void sendVerificationCode(final APIResult<Activation> callback
            , String phoneNumber
            , String deviceId
            , int verificationCode) {

        Call<Activation> call = service.activateStep2(phoneNumber, deviceId, verificationCode);

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



}