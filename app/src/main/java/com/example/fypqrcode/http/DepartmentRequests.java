package com.example.fypqrcode.http;

import com.example.fypqrcode.classes.DataItem;
import com.example.fypqrcode.http.requests.ValueRequest;
import com.example.fypqrcode.http.responses.SuccessResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface DepartmentRequests {
    @GET("http://192.168.0.103:80/php/controllers/department-controller.php?f=getAllDepartments")
    Call<DataItem[]> getAllDepartments();

    @POST("http://192.168.0.103:80/php/controllers/department-controller.php?f=insertNewDepartment")
    Call<SuccessResponse> insertNewDepartment(@Body ValueRequest value);

    @PUT("http://192.168.0.103:80/php/controllers/department-controller.php?f=updateDepartment")
    Call<SuccessResponse> updateDepartment(@Body DataItem DepartmentDataItem);

    @POST("http://192.168.0.103:80/php/controllers/department-controller.php?f=deleteDepartment")
    Call<SuccessResponse> deleteDepartment(@Body DataItem DepartmentDataItem);
}
