package com.example.foodapp.model;

public class Order {
    private Integer order;
    private String nameFood;
    private String img;
    private String des;
    private Integer price;
    private Integer quanity;

    public Order(Integer order, String nameFood, String img, String des, Integer price, Integer quanity) {
        this.order = order;
        this.nameFood = nameFood;
        this.img = img;
        this.des = des;
        this.price = price;
        this.quanity = quanity;
    }

    public Order(String nameFood, String img, String des, Integer price, Integer quanity) {
        this.order = order;
        this.nameFood = nameFood;
        this.img = img;
        this.des = des;
        this.price = price;
        this.quanity = quanity;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
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

    public Integer getQuanity() {
        return quanity;
    }

    public void setQuanity(Integer quanity) {
        this.quanity = quanity;
    }
}
