package com.example.foodapp;

public class OrderModel {
    private String hotelName;
    private byte [] avatar;
    private String meal;
    private int price;
    private boolean isSelect;

    public OrderModel(String hotelName, byte[] avatar, String meal, int price) {
        this.hotelName = hotelName;
        this.avatar = avatar;
        this.meal = meal;
        this.price = price;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isSelect(){
        return isSelect;
    }
    public void setSelect(boolean select){
        isSelect = select;
    }
}
