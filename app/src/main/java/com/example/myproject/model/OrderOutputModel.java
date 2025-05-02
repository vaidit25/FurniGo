package com.example.myproject.model;

import java.util.ArrayList;

public class OrderOutputModel {
    public boolean status;
    public String message;
    public ArrayList<OrderModel> order;

    public OrderOutputModel(boolean status, String message, ArrayList<OrderModel> order) {
        this.status = status;
        this.message = message;
        this.order = order;
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

    public ArrayList<OrderModel> getOrder() {
        return order;
    }

    public void setOrder(ArrayList<OrderModel> order) {
        this.order = order;
    }
}
