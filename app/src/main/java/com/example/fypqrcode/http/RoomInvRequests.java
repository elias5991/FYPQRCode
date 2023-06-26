package com.example.fypqrcode.http;

import com.example.fypqrcode.http.requests.RoomInvRequest;
import com.example.fypqrcode.http.requests.RoomRequest;
import com.example.fypqrcode.http.responses.InventoryResponse;
import com.example.fypqrcode.http.responses.RoomInvResponse;
import com.example.fypqrcode.http.responses.RoomResponse;
import com.example.fypqrcode.http.responses.SuccessResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface RoomInvRequests {
    @GET("http://192.168.0.103:80/php/controllers/room-inventories-controller.php?f=getCurrentRoomInv")
    Call<RoomInvResponse[]> getCurrentRoomInv(@Query("roomId") String roomId);

    @GET("http://192.168.0.103:80/php/controllers/room-inventories-controller.php?f=getAllInventories")
    Call<InventoryResponse[]> getAllInventories();

    @POST("http://192.168.0.103:80/php/controllers/room-inventories-controller.php?f=insertNewRoomInv")
    Call<SuccessResponse> insertNewRoomInv(@Body RoomInvRequest roomInvRequest);

    @PUT("http://192.168.0.103:80/php/controllers/room-inventories-controller.php?f=updateRoomInv")
    Call<SuccessResponse> updateRoomInv(@Body RoomInvRequest roomInvRequest);

    @POST("http://192.168.0.103:80/php/controllers/room-inventories-controller.php?f=deleteRoomInv")
    Call<SuccessResponse> deleteRoomInv(@Body RoomInvRequest roomInvRequest);


}
