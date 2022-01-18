package com.example.whateatprojects.Model;

public class Menufood {
    private String name,image,CataID;

    public Menufood() {
    }

    public Menufood(String NAME, String IMAGE, String CATAID) {
        name = NAME;
        image = IMAGE;
        CataID = CATAID;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getCataID() {
        return CataID;
    }
}
