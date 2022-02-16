package com.example.whateatprojects.Model;

public class Resturantaf {
    private String Name;
    private String Price;
    private String locatinos;
    private String Logo;

    public Resturantaf() {
    }

    public Resturantaf(String NAME, String PRICE, String LOCATION, String LOGO) {
        Name = NAME;
        Price = PRICE;
        locatinos = LOCATION;
        Logo = LOGO;
    }

    public String getName() {
        return Name;
    }

    public String getPrice() {
        return Price + " บาท";
    }

    public String getLocatinos() {
        return locatinos;
    }

    public String getLogo() {
        return Logo;
    }
}
