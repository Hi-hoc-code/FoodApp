package com.example.foodapp.model;

public class Order {
    private String orderID;
    private double total;
    private int userID;
    private String status;
    private String shippingAddress;

    public Order(String orderID, double total, int userID, String status, String shippingAddress) {
        this.orderID = orderID;
        this.total = total;
        this.userID = userID;
        this.status = status;
        this.shippingAddress = shippingAddress;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
