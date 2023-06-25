package com.example.fypqrcode.http;

import com.example.fypqrcode.classes.DataItem;
import com.example.fypqrcode.http.requests.LoginRequest;
import com.example.fypqrcode.http.requests.UserRequest;
import com.example.fypqrcode.http.responses.SuccessResponse;
import com.example.fypqrcode.http.responses.UserResponse;
import com.example.fypqrcode.http.responses.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface UserRequests {
        @POST("http://10.0.2.2:80/php/controllers/login-controller.php?f=login")
    Call<LoginResponse> login(@Body LoginRequest request);

    @GET("http://10.0.2.2:80/php/controllers/account-controller.php")
    Call<UserResponse> getCurrentUser(@Query("f") String f, @Query("email") String email);

    @GET("http://10.0.2.2:80/php/controllers/account-controller.php?f=getAllRoleTypes")
    Call<DataItem[]> getAllRoleTypes();

    @GET("http://10.0.2.2:80/php/controllers/account-controller.php?f=getAllAccTypes")
    Call<DataItem[]> getAllAccTypes();

    @GET("http://10.0.2.2:80/php/controllers/account-controller.php?f=getUsers")
    Call<UserResponse[]> getUsers();

    @POST("http://10.0.2.2:80/php/controllers/account-controller.php?f=insertNewUser")
    Call<SuccessResponse> insertNewUser(@Body UserRequest userRequest);

    @PUT("http://10.0.2.2:80/php/controllers/account-controller.php?f=updateUser")
    Call<SuccessResponse> updateUser(@Body UserRequest userRequest);

    @POST("http://10.0.2.2:80/php/controllers/account-controller.php?f=deleteUser")
    Call<SuccessResponse> deleteUser(@Body UserRequest userRequest);

}
