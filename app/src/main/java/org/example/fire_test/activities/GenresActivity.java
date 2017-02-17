package org.example.fire_test.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.example.fire_test.models.Genre;
import org.example.fire_test.R;
import org.example.fire_test.adapters.GenreListAdapter;
import org.example.fire_test.models.User;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by takuya on 1/26/17.
 */

public class GenresActivity extends AppCompatActivity implements Observer{

    private ListView genresListView;
    private Button genres_counter_button;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;

    private HashMap<String,Integer> genres_point = new HashMap<String, Integer>();
    private HashMap<String,Integer> tags = new HashMap<String, Integer>();

    private String mUserId;
    private List<Genre> genres = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genres);
        Toolbar toolbar = (Toolbar) findViewById(R.id.genres_toolbar);
        setSupportActionBar(toolbar);


        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();


        User.getInstance().addObserver(this);
        mUserId =mFirebaseUser.getUid();



        genresListView = (ListView)findViewById(R.id.genres_list_view);
        genres_counter_button = (Button) findViewById(R.id.genres_counter_button);

        updateCounterButton(User.getInstance().getSelected_genres().size());
        genres_counter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),UsedServicesActivity.class);
                startActivity(intent);
            }
        });

        try{

            mDatabase.child("tags").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot tagsSnapshot:dataSnapshot.getChildren()){
                        tags.put(tagsSnapshot.getValue(String.class),0);
                    }
                    makeTagsParams(tags,mDatabase,mUserId);
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            mDatabase.child("genres").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                     for(DataSnapshot genreSnapshot: dataSnapshot.getChildren()){
                         Genre genre =genreSnapshot.getValue(Genre.class);
                         genres_point.put(genre.getName(),0);
                         genres.add(genre);
                     }
                    makeGenreParams(genres_point,mDatabase,mUserId);
                    GenreListAdapter genreListAdapter =new GenreListAdapter(GenresActivity.this,genres);
                    genresListView.setAdapter(genreListAdapter);
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void update(Observable observable, Object o) {
        updateCounterButton((int) o);
    }

    private void updateCounterButton(int amount) {
        if (amount < 5)
        {
            genres_counter_button.setText(String.format(getString(R.string.select_genre_counter), 5 - amount));
            genres_counter_button.setClickable(false);
            genres_counter_button.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
            genres_counter_button.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.colorPrimary));
        }else
        {
            genres_counter_button.setText(getString(R.string.select_genre_done));
            genres_counter_button.setClickable(true);
            genres_counter_button.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_keyboard_arrow_right_white_48dp,0);
            genres_counter_button.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.colorSecondary));
        }

    }

    private void makeGenreParams(HashMap<String,Integer> genre,DatabaseReference mDatabase,String UserId)
    {
        mDatabase.child("users").child(UserId).child("genres_point").setValue(genre);
    }

    private void makeTagsParams(HashMap<String,Integer> tag,DatabaseReference mDatabase, String UserId)
    {
        mDatabase.child("users").child(UserId).child("tags_point").setValue(tag);
    }
}
