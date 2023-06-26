package com.example.fypqrcode.http.responses;

public class RoomInvResponse {

    String type;
    String description;
    Integer quantity;
    Integer inventory_id;

    public RoomInvResponse(String type, String description, Integer quantity, Integer inventory_id) {
        this.type = type;
        this.description = description;
        this.quantity = quantity;
        this.inventory_id = inventory_id;
    }

    public RoomInvResponse() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(Integer inventory_id) {
        this.inventory_id = inventory_id;
    }
}
