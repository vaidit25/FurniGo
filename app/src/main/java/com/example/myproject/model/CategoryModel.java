package com.example.myproject.model;

public class CategoryModel{
    public String _id;
    public String cat_name;
    public String cat_desc;
    public String cat_pic;


    public CategoryModel(String _id, String cat_name, String cat_desc, String cat_pic) {
        this._id = _id;
        this.cat_name = cat_name;
        this.cat_desc = cat_desc;
        this.cat_pic = cat_pic;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getCat_desc() {
        return cat_desc;
    }

    public void setCat_desc(String cat_desc) {
        this.cat_desc = cat_desc;
    }

    public String getCat_pic() {
        return cat_pic;
    }

    public void setCat_pic(String cat_pic) {
        this.cat_pic = cat_pic;
    }
}