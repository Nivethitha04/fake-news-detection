package com.rsr.frankly.api;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {

    // ADMIN

    @FormUrlEncoded
    @POST("user_login")
    Call<DefaultResponse> userLogin(
            @Field("user_id") String user_id,
            @Field("password") String password
    );

    // TEMP USERS

    @FormUrlEncoded
    @POST("create_user")
    Call<DefaultResponse> create_user(
            @Field("user_id") String user_id,
            @Field("first_name") String first_name,
            @Field("last_name") String last_name,
            @Field("dob") String dob,
            @Field("mail_id") String mail_id,
            @Field("mobile_no") String mobile,
            @Field("designation") String designation,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("get_user")
    Call<SingleResponse> getSingleUser(
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @PUT("update_user/{user_id}")
    Call<DefaultResponse> updateUser(
            @Path("user_id") String user_id,
            @Field("first_name") String first_name,
            @Field("last_name") String last_name,
            @Field("dob") String dob,
            @Field("mail_id") String mail_id,
            @Field("mobile_no") String mobile,
            @Field("designation") String designation
    );

    @FormUrlEncoded
    @PUT("reset_password")
    Call<DefaultResponse> reset_password(
            @Field("new_password") String new_password,
            @Field("user_id") String user_id
    );

    @GET("all_news")
    Call<AllNewsResponse> getNews();

}
