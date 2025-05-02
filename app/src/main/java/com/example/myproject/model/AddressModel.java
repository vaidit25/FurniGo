package com.example.myproject.model;

public class AddressModel {
    public String uid;
    public String address;
    public String pincode;
    public String _id;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public AddressModel(String uid, String address, String pincode, String _id) {
        this.uid = uid;
        this.address = address;
        this.pincode = pincode;
        this._id = _id;


    }
}
