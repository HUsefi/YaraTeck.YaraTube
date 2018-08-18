package com.yaratech.yaratube.data.source.remote;


import com.yaratech.yaratube.data.model.CategoryList;
import com.yaratech.yaratube.data.model.ProductDetails;
import com.yaratech.yaratube.data.model.ProductList;
import com.yaratech.yaratube.data.model.Store;
import com.yaratech.yaratube.utils.Constant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import static com.yaratech.yaratube.utils.Constant.STORE_ID;

public interface APIInterface {

    @GET("/store/" + STORE_ID)
    Call<Store> getStore();
    @GET("category/"+ Constant.STORE_ID +"/463")
    Call<List<CategoryList>> getCategoryData();
    @GET("/listproducts/{product_id}")
    Call<List<ProductList>> getProductListData(@Path("product_id") int productId);
    @GET ("/product/{product_id}")
    Call<ProductDetails> getProductDetailData(@Path("product_id") int productId);
}
