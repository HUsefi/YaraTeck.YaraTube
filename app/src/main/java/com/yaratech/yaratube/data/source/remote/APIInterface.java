package com.yaratech.yaratube.data.source.remote;


import com.yaratech.yaratube.data.model.Activation;
import com.yaratech.yaratube.data.model.CategoryList;
import com.yaratech.yaratube.data.model.Comment;
import com.yaratech.yaratube.data.model.CommentPostResponse;
import com.yaratech.yaratube.data.model.MobileLoginStep1;
import com.yaratech.yaratube.data.model.Product;
import com.yaratech.yaratube.data.model.ProductDetails;
import com.yaratech.yaratube.data.model.Store;
import com.yaratech.yaratube.util.Constant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.yaratech.yaratube.util.Constant.STORE_ID;

public interface APIInterface {

    @GET("/store/" + STORE_ID)
    Call<Store> getStore();

    @GET("category/" + Constant.STORE_ID + "/463")
    Call<List<CategoryList>> getCategoryData();

    @GET("/listproducts/{product_id}")
    Call<List<Product>> getProductListData(@Path("product_id") int productId
            ,@Query("offset") int offset
            ,@Query("limit") int limit);

    @GET("/product/{product_id}")
    Call<Product> getProductDetailData(@Path("product_id") int productId);

    @GET("/comment/{product_id}")
    Call<List<Comment>> getCommentData(@Path("product_id") int productId);

    @FormUrlEncoded
    @POST("mobile_login_step1/" + Constant.STORE_ID )
    Call<MobileLoginStep1> sendMobileLoginStep1(
            @Field("mobile") String mobile,
            @Field("device_id") String deviceId,
            @Field("device_model") String deviceModel,
            @Field("device_os") String deviceOs);
           // @Field("gcm") String gcm);

    // send activation code and get token
    @POST("mobile_login_step2/" + STORE_ID)
    @FormUrlEncoded
    Call<Activation> activateStep2(@Field("mobile") String phoneNumber,
                                   @Field("device_id") String deviceId,
                                   @Field("verification_code") int verificationCode,
                                   @Field("nickname") String nickname);

    // send user comment to server
    @POST("comment/{productId}")
    @FormUrlEncoded
    Call<CommentPostResponse> sendComment(@Field("title") String title,
                                          @Field("score") int score,
                                          @Field("comment_text") String commnetText,
                                          @Path("productId") int productId,
                                          @Header("Authorization") String token);
}
