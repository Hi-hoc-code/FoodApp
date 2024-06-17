package com.example.foodapp.model;

public class Order {
    private String orderID;
    private int total;
    private int userID;
    private String status;
    private String shippingAddress;
    private String phoneNumber;
    private String name;

    public Order(String orderID, int total, int userID, String status, String shippingAddress) {
        this.orderID = orderID;
        this.total = total;
        this.userID = userID;
        this.status = status;
        this.shippingAddress = shippingAddress;
    }

    public Order(String orderID, String status,int userID, int total, String name , String phoneNumber, String shippingAddress) {
        this.orderID = orderID;
        this.total = total;
        this.userID = userID;
        this.status = status;
        this.shippingAddress = shippingAddress;
        this.phoneNumber = phoneNumber;
        this.name = name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
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
