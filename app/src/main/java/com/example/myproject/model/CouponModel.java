package com.example.myproject.model;
public class CouponModel{
    public String _id;
    public String c_code;
    public String c_desc;
    public String c_discount;
    public String c_max_amt;
    public String c_pic;
    public boolean status;

    public CouponModel(String _id, String c_code, String c_desc, String c_discount, String c_max_amt, String c_pic, boolean status) {
        this._id = _id;
        this.c_code = c_code;
        this.c_desc = c_desc;
        this.c_discount = c_discount;
        this.c_max_amt = c_max_amt;
        this.c_pic = c_pic;
        this.status = status;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getC_code() {
        return c_code;
    }

    public void setC_code(String c_code) {
        this.c_code = c_code;
    }

    public String getC_desc() {
        return c_desc;
    }

    public void setC_desc(String c_desc) {
        this.c_desc = c_desc;
    }

    public String getC_discount() {
        return c_discount;
    }

    public void setC_discount(String c_discount) {
        this.c_discount = c_discount;
    }

    public String getC_max_amt() {
        return c_max_amt;
    }

    public void setC_max_amt(String c_max_amt) {
        this.c_max_amt = c_max_amt;
    }

    public String getC_pic() {
        return c_pic;
    }

    public void setC_pic(String c_pic) {
        this.c_pic = c_pic;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
