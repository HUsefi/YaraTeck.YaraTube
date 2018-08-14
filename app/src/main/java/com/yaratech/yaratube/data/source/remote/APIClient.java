package com.yaratech.yaratube.data.source.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.yaratech.yaratube.utils.Kit.BASE_URL;


public class APIClient {

    private static Retrofit retrofit=null;
    public Retrofit getClient(){
         if(retrofit== null){
             retrofit = new Retrofit.Builder()
                     .baseUrl(BASE_URL)
                     .addConverterFactory(GsonConverterFactory.create())
                     .build();
         }
         return retrofit;
    }
}
