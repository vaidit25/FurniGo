package com.example.myproject.model;

import java.io.Serializable;

public class ProductModel implements Serializable {
    public String _id;
    public String pic1;
    public String pic2;
    public String pic3;
    public String productname;
    public String price;
    public String cid;
    public String desc;
    public String primary_material;
    public String weight;
    public String dimension;
    public String specification;
    String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPic1() {
        return pic1;
    }

    public void setPic1(String pic1) {
        this.pic1 = pic1;
    }

    public String getPic2() {
        return pic2;
    }

    public void setPic2(String pic2) {
        this.pic2 = pic2;
    }

    public String getPic3() {
        return pic3;
    }

    public void setPic3(String pic3) {
        this.pic3 = pic3;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrimary_material() {
        return primary_material;
    }

    public void setPrimary_material(String primary_material) {
        this.primary_material = primary_material;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public ProductModel(String category,String _id, String pic1, String pic2, String pic3, String productname, String price, String cid, String desc, String primary_material, String weight, String dimension, String specification) {
        this._id = _id;
        this.pic1 = pic1;
        this.pic2 = pic2;
        this.pic3 = pic3;
        this.productname = productname;
        this.price = price;
        this.cid = cid;
        this.desc = desc;
        this.primary_material = primary_material;
        this.weight = weight;
        this.dimension = dimension;
        this.specification = specification;
        this.category=category;

    }
}
