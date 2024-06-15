package com.example.foodapp.model;

public class Member {
    private Integer Id, roleUser;
    private String name, phone, avatar, orderID, passwordUser;

    public Member() {
    }

    public Member(Integer id, String name, String phone, String avatar, String orderID, String passwordUser, Integer roleUser) {
        Id = id;
        this.name = name;
        this.phone = phone;
        this.avatar = avatar;
        this.orderID = orderID;
        this.passwordUser = passwordUser;
        this.roleUser = roleUser;
    }

    public Member(String name, String phone, String passwordUser, Integer roleUser) {
        this.name = name;
        this.phone = phone;
        this.passwordUser = passwordUser;
        this.roleUser = roleUser;
    }

    public Member(Integer id, String name, String phone, String avatar, String passwordUser) {
        Id = id;
        this.name = name;
        this.phone = phone;
        this.avatar = avatar;
        this.passwordUser = passwordUser;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    public Integer getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(Integer roleUser) {
        this.roleUser = roleUser;
    }
}