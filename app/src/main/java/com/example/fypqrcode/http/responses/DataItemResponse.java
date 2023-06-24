package com.example.fypqrcode.http.responses;

public class DataItemResponse {
    private Integer id;
    private String value;

    public DataItemResponse(Integer id, String value) {
        this.id=id;
        this.value=value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}