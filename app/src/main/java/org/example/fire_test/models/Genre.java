package org.example.fire_test.models;

/**
 * Created by takuya on 1/26/17.
 */
import java.util.ArrayList;

public class Genre {
    private java.lang.String name,image;
    private ArrayList<String> services;

    public java.lang.String getName(){
        return name;
    }

    public void setName(java.lang.String name){
        this.name = name;
    }

    public java.lang.String getImage(){
        return image;
    }

    public void setImage(java.lang.String image){
        this.image = image;
    }

    public ArrayList<String> getServices(){
        return services;
    }

    public void setServices(ArrayList<String> services){
        this.services=services;
    }
}
