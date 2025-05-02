package com.example.myproject.model;

public class StoreModel {

    public String store_pic;
    public String store_name;
    public String store_mobileno;
    public String store_address;
    public String store_pincode;
    public String id;

    public StoreModel(String store_pic, String store_name, String store_mobileno, String store_address, String store_pincode, String id) {
        this.store_pic = store_pic;
        this.store_name = store_name;
        this.store_mobileno = store_mobileno;
        this.store_address = store_address;
        this.store_pincode = store_pincode;
        this.id = id;
    }

    public String getStore_pic() {
        return store_pic;
    }

    public void setStore_pic(String store_pic) {
        this.store_pic = store_pic;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getStore_mobileno() {
        return store_mobileno;
    }

    public void setStore_mobileno(String store_mobileno) {
        this.store_mobileno = store_mobileno;
    }

    public String getStore_address() {
        return store_address;
    }

    public void setStore_address(String store_address) {
        this.store_address = store_address;
    }

    public String getStore_pincode() {
        return store_pincode;
    }

    public void setStore_pincode(String store_pincode) {
        this.store_pincode = store_pincode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
