package com.example.foodapp.model;

public class OrderItem {
    private String itemId;
    private String itemName;
    private double price;
    private byte[] itemImage; // Hình ảnh của mặt hàng

    public OrderItem(String itemId, String itemName, double price, byte[] itemImage) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
        this.itemImage = itemImage;
    }

    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }

    public byte[] getItemImage() {
        return itemImage;
    }
}
