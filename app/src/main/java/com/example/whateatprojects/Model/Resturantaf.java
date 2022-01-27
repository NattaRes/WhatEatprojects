package com.example.whateatprojects.Model;

public class Resturantaf {
    private String Name;
    private String Price;
    private String locatinos;

    public Resturantaf() {
    }

    public Resturantaf(String NAME, String PRICE, String LOCATION) {
        Name = NAME;
        Price = PRICE;
        locatinos = LOCATION;
    }

    public String getName() {
        return Name;
    }

    public String getPrice() {
        return Price;
    }

    public String getLocatinos() {
        return locatinos;
    }
}
