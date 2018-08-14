package com.yaratech.yaratube.data.source.remote;


import com.yaratech.yaratube.data.model.CategoryList;
import com.yaratech.yaratube.data.model.Store;
import com.yaratech.yaratube.utils.Kit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

import static com.yaratech.yaratube.utils.Kit.STORE_ID;

public interface APIInterface {

    @GET("/store/" + STORE_ID)
    Call<Store> getStore();
    @GET("category/"+ Kit.STORE_ID +"/463")
    Call<List<CategoryList>> getCategoryData();
}
