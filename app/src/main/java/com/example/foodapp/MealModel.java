package com.example.foodapp;

public class MealModel {
    private String hotelName;
    private byte [] avatar;
    private String meal;
    private int price;
    private String availability;
    private boolean isSelect;

    public MealModel(String hotelName, byte[] avatar, String meal, int price, String availability) {
        this.hotelName = hotelName;
        this.avatar = avatar;
        this.meal = meal;
        this.price = price;
        this.availability = availability;
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

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public boolean isSelect(){
        return isSelect;
    }
    public void setSelect(boolean select){
        isSelect = select;
    }
}
