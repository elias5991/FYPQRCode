package com.example.fypqrcode.http;

import com.example.fypqrcode.classes.DataItem;
import com.example.fypqrcode.http.requests.ValueRequest;
import com.example.fypqrcode.http.responses.SuccessResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface RoomTypeRequests {
    @GET("http://10.0.2.2:80/php/controllers/room-type-controller.php?f=getAllRoomTypes")
    Call<DataItem[]> getAllRoomTypes();

    @POST("http://10.0.2.2:80/php/controllers/room-type-controller.php?f=insertNewRoomType")
    Call<SuccessResponse> insertNewRoomType(@Body ValueRequest value);

    @PUT("http://10.0.2.2:80/php/controllers/room-type-controller.php?f=updateRoomType")
    Call<SuccessResponse> updateRoomType(@Body DataItem roomTypeDataItem);

    @POST("http://10.0.2.2:80/php/controllers/room-type-controller.php?f=deleteRoomType")
    Call<SuccessResponse> deleteRoomType(@Body DataItem roomTypeDataItem);
}
