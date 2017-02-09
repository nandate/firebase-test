package org.example.fire_test.models;

/**
 * Created by takuya on 1/25/17.
 */

public class Item {
    private String title;

    public Item(){}

    public Item(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }
}
