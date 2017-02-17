package org.example.fire_test.models;

import java.util.ArrayList;
import java.util.Observable;

import org.example.fire_test.models.Genre;
/**
 * Created by takuya on 2/4/17.
 */

public class User extends Observable{

    private ArrayList<String> selected_genres;
    private ArrayList<String> used_services;


    private static User ourInstance = new User();


    public static User getInstance() {
        return ourInstance;
    }

    private User() {
    }


    public ArrayList<String> getSelected_genres() {
        selected_genres = selected_genres == null ? new ArrayList<String>() : selected_genres;
        return selected_genres;
    }

    public void addSelectedGenre(String genreName)
    {
        if (!selected_genres.contains(genreName)){
            selected_genres.add(genreName);
            setChanged();
            notifyObservers(selected_genres.size());
        }
    }

    public void removeSelectedGenre(String genreName)
    {
        selected_genres.remove(genreName);
        setChanged();
        notifyObservers(selected_genres.size());
    }

    public ArrayList<String> getused_services() {
        used_services = used_services == null ? new ArrayList<String>() : used_services;
        return used_services;
    }

    public void addUsedService(String serviceName)
    {
        used_services.add(serviceName);
    }

    public void removeUsedService(String serviceName)
    {
        used_services.remove(serviceName);
    }


    public boolean hasGenre(String genre)
    {
        return selected_genres.contains(genre);
    }

    public boolean hasService(String service){
        return used_services.contains(service);
    }



    @Override
    public void notifyObservers(Object arg) {
        super.notifyObservers(arg);
    }


}
