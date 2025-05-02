package com.example.myproject.model;

public class BannerModel{
    public String _id;
    public String img;
    public int status;


    public BannerModel(String _id, String img, int status) {
        this._id = _id;
        this.img = img;
        this.status = status;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int isStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}