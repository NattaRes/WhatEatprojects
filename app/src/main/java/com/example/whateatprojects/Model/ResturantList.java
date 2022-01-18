package com.example.whateatprojects.Model;

public class ResturantList {
    private String name,price,foodID;

    public ResturantList() {
    }

    public ResturantList(String NAME, String PRICE, String FOODID) {
        name = NAME;
        price = PRICE;
        foodID = FOODID;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getFoodID() {
        return foodID;
    }
}
