package com.yaratech.yaratube.data.source;

import android.util.Log;

import com.yaratech.yaratube.data.model.CategoryList;
import com.yaratech.yaratube.data.model.Comment;
import com.yaratech.yaratube.data.model.MobileLoginStep1;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.data.model.ProductDetails;
import com.yaratech.yaratube.data.model.ProductList;
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


    public void getProductList(final APIResult<List<Product>> callBack, int categoryId) {
        APIClient apiClient = new APIClient();
        APIInterface service = apiClient.getClient().create(APIInterface.class);
        Call<List<Product>> productList = service.getProductListData(categoryId);
        productList.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    callBack.onSuccess(response.body());
                } else {
                    callBack.onFail();
                }

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                callBack.onFail();
            }
        });
    }

    public void getProductDetail(final APIResult<ProductDetails> callBack, int productId) {
        APIClient apiClient = new APIClient();
        APIInterface service = apiClient.getClient().create(APIInterface.class);
        Call<ProductDetails> productList = service.getProductDetailData(productId);
        productList.enqueue(new Callback<ProductDetails>() {
            @Override
            public void onResponse(Call<ProductDetails> call, Response<ProductDetails> response) {
                if (response.isSuccessful()) {
                    callBack.onSuccess(response.body());
                } else {
                    callBack.onFail();
                }
            }

            @Override
            public void onFailure(Call<ProductDetails> call, Throwable t) {
                callBack.onFail();
            }
        });
    }

    public void getComment(final APIResult<List<Comment>> callBack, int productId) {
        APIClient apiClient = new APIClient();
        APIInterface service = apiClient.getClient().create(APIInterface.class);
        Call<List<Comment>> commentList = service.getCommentData(productId);
        commentList.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (response.isSuccessful()) {
                    callBack.onSuccess(response.body());
                } else {
                    callBack.onFail();
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                callBack.onFail();
            }
        });
    }

    public void sendMobileLoginStep1(final APIResult<MobileLoginStep1> callBack, String mobile
            , String deviceId
            , String deviceModel
            , String deviceOs
            , String gcm) {

        APIClient apiClient = new APIClient();
        APIInterface service = apiClient.getClient().create(APIInterface.class);
        Call<MobileLoginStep1> loginPhoneCall = service.sendMobileLoginStep1(mobile,
                deviceId,
                deviceModel,
                deviceOs,
                gcm);
        loginPhoneCall.enqueue(new Callback<MobileLoginStep1>() {
            @Override
            public void onResponse(Call<MobileLoginStep1> call, Response<MobileLoginStep1> response) {
                if (response.isSuccessful()) {
                    callBack.onSuccess(response.body());
                } else {
                    callBack.onFail();
                }
            }

            @Override
            public void onFailure(Call<MobileLoginStep1> call, Throwable t) {
                callBack.onFail();
            }
        });
    }





}


