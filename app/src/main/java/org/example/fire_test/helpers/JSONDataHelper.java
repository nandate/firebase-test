package org.example.fire_test.helpers;


import android.util.JsonReader;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.example.fire_test.models.Genre;
import org.example.fire_test.models.Service;

/**
 * Created by takuya on 1/28/17.
 */

public class JSONDataHelper {
    private Service readService(){
        ArrayList<Service> services = new ArrayList<>();
        return null;
    }

    private static ArrayList<Genre> readGenresObject(JsonReader reader) {
        ArrayList<Genre> genres = new ArrayList<>();
        try {
            reader.beginObject();
            while (reader.hasNext()){
                //genre1
                Genre genre = new Genre();
                genre.setName(reader.nextName());
                reader.beginObject();
                while (reader.hasNext()){

                    String name = reader.nextName();

                    if(name.equals("image"))
                    {
                        genre.setImage(reader.nextString());
                    }

                    name = reader.nextName();
                    if (name.equals("services")) {
                        ArrayList<String> services = new ArrayList<>();
                        reader.beginArray();
                        while (reader.hasNext()) {
                            services.add(reader.nextString());
                        }
                        reader.endArray();
                        genre.setServices(services);
                    }
                }
                reader.endObject();
                genres.add(genre);
            }
            reader.endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return genres;
    }

}
