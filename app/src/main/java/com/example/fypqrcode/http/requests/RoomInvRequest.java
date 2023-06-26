package com.example.fypqrcode.http.requests;

public class RoomInvRequest {


    Integer inventoryId;
    Integer roomId;
    Integer quantity;

    public RoomInvRequest(Integer inventoryId, Integer roomId, Integer quantity) {
        this.inventoryId = inventoryId;
        this.roomId = roomId;
        this.quantity = quantity;
    }

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

