package com.example.sosproject;


import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitAPI {
    @GET("user/{id}")
    Call<UserInfo> getMember(@Path("id") String id);


    @FormUrlEncoded
    @POST("user/{id}")
    Call<UserInfo> updateMember(@Path("id") String id, @Field("age") String age, @Field("income_grade") String income_grade, @Field("total_fare") String total_fare);

//    @PUT("user/{id}")
//    Call<UserInfo> updateMember(@Path("id") String id, @Body UserInfo userinfo);
}
