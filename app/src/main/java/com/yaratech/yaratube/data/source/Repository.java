package com.yaratech.yaratube.data.source;

import com.yaratech.yaratube.data.model.CategoryList;
import com.yaratech.yaratube.data.model.Store;
import com.yaratech.yaratube.data.source.remote.APIClient;
import com.yaratech.yaratube.data.source.remote.APIInterface;
import com.yaratech.yaratube.data.source.remote.APIResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Repository {

 public void getStore(final APIResult<Store> callBack) {
        APIClient apiClient = new APIClient();
        APIInterface service = apiClient.getClient().create(APIInterface.class);
        Call<Store> store = service.getStore();
        store.enqueue(new Callback<Store>() {
            @Override
            public void onResponse(Call<Store> call, Response<Store> response) {
                if (response.isSuccessful()) {
                    callBack.onSuccess(response.body());
                } else {
                    callBack.onFail();
                }

            }

            @Override
            public void onFailure(Call<Store> call, Throwable t) {
                callBack.onFail();
            }
        });

    }


    public void getCategory(final APIResult<List<CategoryList>> callBack) {
        APIClient apiClient = new APIClient();
        APIInterface service = apiClient.getClient().create(APIInterface.class);
        Call<List<CategoryList>> cayegoryList = service.getCategoryData();
        cayegoryList.enqueue(new Callback<List<CategoryList>>() {
            @Override
            public void onResponse(Call<List<CategoryList>> call, Response<List<CategoryList>> response) {
                if (response.isSuccessful()) {
                    callBack.onSuccess(response.body());
                } else {
                    callBack.onFail();
                }

            }

            @Override
            public void onFailure(Call<List<CategoryList>> call, Throwable t) {
                callBack.onFail();
            }
        });

    }









}


