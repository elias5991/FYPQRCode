package com.example.fypqrcode.classes;

public class DataItem {
    private Integer id;
    private String value;

    public DataItem(Integer id, String value) {
        this.id=id;
        this.value=value;
    }

    public DataItem() {

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
