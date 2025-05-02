package com.example.myproject.model;

import java.util.ArrayList;

public class DataOutputModel{
    public boolean status;
    public String message;
    public ArrayList<BannerModel> banner_data;
    public ArrayList<CategoryModel> category_data;
    public ArrayList<CouponModel> coupon_data;
    public ArrayList<ProductModel> product_data;
    public ArrayList<StoreModel> store_data;

    public DataOutputModel(boolean status, String message, ArrayList<BannerModel> banner_data, ArrayList<CategoryModel> category_data, ArrayList<CouponModel> coupon_data, ArrayList<ProductModel> product_data, ArrayList<StoreModel> store_data) {
        this.status = status;
        this.message = message;
        this.banner_data = banner_data;
        this.category_data = category_data;
        this.coupon_data = coupon_data;
        this.product_data = product_data;
        this.store_data = store_data;
    }

    public ArrayList<StoreModel> getStore_data() {
        return store_data;
    }

    public void setStore_data(ArrayList<StoreModel> store_data) {
        this.store_data = store_data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<BannerModel> getBanner_data() {
        return banner_data;
    }

    public void setBanner_data(ArrayList<BannerModel> banner_data) {
        this.banner_data = banner_data;
    }

    public ArrayList<CategoryModel> getCategory_data() {
        return category_data;
    }

    public void setCategory_data(ArrayList<CategoryModel> category_data) {
        this.category_data = category_data;
    }

    public ArrayList<CouponModel> getCoupon_data() {
        return coupon_data;
    }

    public void setCoupon_data(ArrayList<CouponModel> coupon_data) {
        this.coupon_data = coupon_data;
    }

    public ArrayList<ProductModel> getProduct_data() {
        return product_data;
    }

    public void setProduct_data(ArrayList<ProductModel> product_data) {
        this.product_data = product_data;
    }
}