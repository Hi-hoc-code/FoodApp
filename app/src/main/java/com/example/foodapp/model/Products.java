package com.example.foodapp.model;

public class Products {
    private Integer id;
    private String name;
    private Integer price;
    private String des;
    private Integer typeId;
    private String typeName;
    private String image;

    public Products(String name, Integer price, String des, String typeName, String image) {
        this.name = name;
        this.price = price;
        this.des = des;
        this.typeName = typeName;
        this.image = image;
    }

    public Products(String name, Integer price, String des, Integer typeId, String typeName, String image) {
        this.name = name;
        this.price = price;
        this.des = des;
        this.typeId = typeId;
        this.typeName = typeName;
        this.image = image;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
