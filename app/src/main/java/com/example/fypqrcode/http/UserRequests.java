package com.example.fypqrcode.http;

import com.example.fypqrcode.http.requests.LoginRequest;
import com.example.fypqrcode.http.responses.GetCurrentUserResponse;
import com.example.fypqrcode.http.responses.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserRequests {
        @POST("http://10.0.2.2:80/php/controllers/login-controller.php?f=login")
    Call<LoginResponse> login(@Body LoginRequest request);

    @GET("http://10.0.2.2:80/php/controllers/account-controller.php")
    Call<GetCurrentUserResponse> getCurrentUser(@Query("f") String f,@Query("email") String email);
}
