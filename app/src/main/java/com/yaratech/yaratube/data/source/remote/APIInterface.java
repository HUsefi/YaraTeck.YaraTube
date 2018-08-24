package com.yaratech.yaratube.data.source.remote;


import com.yaratech.yaratube.data.model.CategoryList;
import com.yaratech.yaratube.data.model.Comment;
import com.yaratech.yaratube.data.model.MobileLoginStep1;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.data.model.ProductDetails;
import com.yaratech.yaratube.data.model.Store;
import com.yaratech.yaratube.utils.Constant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import static com.yaratech.yaratube.utils.Constant.STORE_ID;

public interface APIInterface {

    @GET("/store/" + STORE_ID)
    Call<Store> getStore();

    @GET("category/" + Constant.STORE_ID + "/463")
    Call<List<CategoryList>> getCategoryData();

    @GET("/listproducts/{product_id}")
    Call<List<Product>> getProductListData(@Path("product_id") int productId);

    @GET("/product/{product_id}")
    Call<ProductDetails> getProductDetailData(@Path("product_id") int productId);

    @GET("/comment/{product_id}")
    Call<List<Comment>> getCommentData(@Path("product_id") int productId);

    @FormUrlEncoded
    @POST("mobile_login_step1/" + Constant.STORE_ID )
    Call<MobileLoginStep1> sendMobileLoginStep1(
            @Field("mobile") String mobile,
            @Field("device_id") String deviceId,
            @Field("device_model") String deviceModel,
            @Field("device_os") String deviceOs,
            @Field("gcm") String gcm);
}
