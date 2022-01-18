package com.example.whateatprojects.Model;

public class Category {

    private String name, Image;

    public Category() {
    }

    public Category(String tfood, String image) {
        name = tfood;
        Image = image;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return Image;
    }
}
