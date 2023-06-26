package com.example.fypqrcode.http;

import com.example.fypqrcode.classes.DataItem;
import com.example.fypqrcode.http.requests.RoomRequest;
import com.example.fypqrcode.http.requests.ValueRequest;
import com.example.fypqrcode.http.responses.RoomResponse;
import com.example.fypqrcode.http.responses.SuccessResponse;
import com.example.fypqrcode.http.responses.Valid;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface RoomRequests {
    @GET("http://192.168.0.103:80/php/controllers/room-controller.php?f=getAllRooms")
    Call<RoomResponse[]> getAllRooms();

    @POST("http://192.168.0.103:80/php/controllers/room-controller.php?f=insertNewRoom")
    Call<SuccessResponse> insertNewRoom(@Body RoomRequest roomRequest);

    @PUT("http://192.168.0.103:80/php/controllers/room-controller.php?f=updateRoom")
    Call<SuccessResponse> updateRoom(@Body RoomRequest roomRequest);

    @POST("http://192.168.0.103:80/php/controllers/room-controller.php?f=deleteRoom")
    Call<SuccessResponse> deleteRoom(@Body RoomRequest roomRequest);

    @GET("http://192.168.0.103:80/php/controllers/room-controller.php?f=getAllFaculties")
    Call<DataItem[]> getAllFaculties();
    @GET("http://192.168.0.103:80/php/controllers/room-controller.php?f=roomExists")
    Call<Valid> roomExists(@Query("roomId") String roomId);

    @GET("http://192.168.0.103:80/php/controllers/room-controller.php?f=getCurrentRoom")
    Call<RoomResponse> getCurrentRoom(@Query("roomId") String roomId);

}
