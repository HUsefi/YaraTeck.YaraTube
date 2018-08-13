package com.yaratech.yaratube.data.source.remote;


import com.yaratech.yaratube.data.model.Store;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("store/16")
    Call<Store> getStore();
//
//    @GET("posts")
//    Call<List<Post>> getPosts(@Query("userId") int id) ;
}
