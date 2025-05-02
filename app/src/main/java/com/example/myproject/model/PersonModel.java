package com.example.myproject.model;

public class PersonModel {
    public String username, email, mobileno, password, updated_at, created_at, _id;
    public boolean status;

    public PersonModel(String username, String email, String mobileno, String password, String updated_at, String created_at, String id, boolean status) {
        this.username = username;
        this.email = email;
        this.mobileno = mobileno;
        this.password = password;
        this.updated_at = updated_at;
        this.created_at = created_at;
        this._id = id;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
