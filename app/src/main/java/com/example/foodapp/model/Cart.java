package com.example.foodapp.model;

public class Cart {
    private String idUser;
    private String nameFood;
    private String img;
    private String des;
    private Integer price;

    public Cart(String idUser, String nameFood, String img, String des, Integer price) {
        this.idUser = idUser;
        this.nameFood = nameFood;
        this.img = img;
        this.des = des;
        this.price = price;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
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
