package com.example.foodapp.model;

public class Cart {
    private Integer idCart;
    private String nameFood;
    private String img;
    private String des;
    private Integer price;


    public Cart(Integer idCart, String nameFood, String img, String des, Integer price) {
        this.idCart = idCart;
        this.nameFood = nameFood;
        this.img = img;
        this.des = des;
        this.price = price;
    }

    public Cart(String nameFood, String img, String des, Integer price) {
        this.nameFood = nameFood;
        this.img = img;
        this.des = des;
        this.price = price;
    }

    public Integer getIdCart() {
        return idCart;
    }

    public void setIdUser(Integer idCart) {
        this.idCart = idCart;
    }

    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
