package com.yaratech.yaratube.data.source;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

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
import com.yaratech.yaratube.util.Constant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Repository {
    private Context context;
    private APIInterface service;
    public Repository(Context context) {

        APIClient apiClient = new APIClient();
        service = apiClient.getClient().create(APIInterface.class);
        this.context = context;
    }


    public void getStore(final APIResult<Store> callBack) {
        Call<Store> store = service.getStore();
        store.enqueue(new Callback<Store>() {
            @Override
            public void onResponse(Call<Store> call, Response<Store> response) {
                if (response.isSuccessful()) {
                    callBack.onSuccess(response.body());
                } else {
                    callBack.onFail(response.message());
                }

            }

            @Override
            public void onFailure(Call<Store> call, Throwable t) {
                callBack.onFail(t.getMessage());
            }
        });

    }


    public void getCategory(final APIResult<List<CategoryList>> callBack) {
        Call<List<CategoryList>> cayegoryList = service.getCategoryData();
        cayegoryList.enqueue(new Callback<List<CategoryList>>() {
            @Override
            public void onResponse(Call<List<CategoryList>> call, Response<List<CategoryList>> response) {
                if (response.isSuccessful()) {
                    callBack.onSuccess(response.body());
                } else {
                    callBack.onFail(response.message());
                }

            }

            @Override
            public void onFailure(Call<List<CategoryList>> call, Throwable t) {
                callBack.onFail(t.getMessage());
            }
        });

    }


    public void getProductList(final APIResult<List<Product>> callBack, int categoryId,int offset, int limit) {
        Call<List<Product>> productList = service.getProductListData(categoryId,offset,limit);
        productList.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    callBack.onSuccess(response.body());
                } else {
                    callBack.onFail(response.message());
                }

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                callBack.onFail(t.getMessage());
            }
        });
    }

    public void getProductDetail(final APIResult<Product> callBack, int productId) {
        Call<Product> productList = service.getProductDetailData(productId);
        productList.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()) {
                    callBack.onSuccess(response.body());
                } else {
                    callBack.onFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                callBack.onFail(t.getMessage());
            }
        });
    }

    public void getComment(final APIResult<List<Comment>> callBack, Product product) {
        Call<List<Comment>> commentList = service.getCommentData(product.getId());
        commentList.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (response.isSuccessful()) {
                    callBack.onSuccess(response.body());
                } else {
                    callBack.onFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                callBack.onFail(t.getMessage());
            }
        });
    }
    private void toastNetworkNotAvailable(Context context) {

        Toast.makeText(context, Constant.INTERNET_ERROR_MESSAGE, Toast.LENGTH_SHORT).show();
    }



}


