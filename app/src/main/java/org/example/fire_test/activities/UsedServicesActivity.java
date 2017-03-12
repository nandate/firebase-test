package org.example.fire_test.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.example.fire_test.R;
import org.example.fire_test.adapters.UsedServiceListAdapter;
import org.example.fire_test.models.Service;
import org.example.fire_test.models.Genre;
import org.example.fire_test.models.User;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by takuya on 2/5/17.
 */

public class UsedServicesActivity extends AppCompatActivity{

    private ListView used_services_ListView;
    private Button used_services_button;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;
    private String mUserId;

    private List<Service> services = new ArrayList<>();
    private HashMap<String,Integer> tags = new HashMap<>();




    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_used_services);
        Toolbar toolbar = (Toolbar) findViewById(R.id.select_used_services_toolbar);
        setSupportActionBar(toolbar);


        mDatabase = FirebaseDatabase.getInstance().getReference();
        final ArrayList<String> selected_genres = User.getInstance().getSelected_genres();
        System.out.println(User.getInstance().getSelected_genres());


        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mUserId =mFirebaseUser.getUid();

        used_services_ListView = (ListView)findViewById(R.id.select_used_services_list_view);
        used_services_button = (Button)findViewById(R.id.used_service_button);

        used_services_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                addTagPoint(User.getInstance().getUsed_services(),mDatabase,mUserId);
                mDatabase.child("users").child(mUserId).child("recommend").setValue(User.getInstance().getUsed_services());
                Intent intent = new Intent(view.getContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        try{
            mDatabase.child("genres").addValueEventListener(new ValueEventListener() {
                ArrayList<String> s =new ArrayList<String>();
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        Genre genre = snapshot.getValue(Genre.class);
                        for(int i=0;i<selected_genres.size();i++){
                            if(selected_genres.get(i).equals(genre.getName())){
                                ArrayList<String> genre_services = genre.getServices();
                                s.add(genre_services.get(0));

                            }
                        }
                    }

                    mDatabase.child("services").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                                String name = (String) snapshot.child("name").getValue();
                                for(int i=0;i<s.size();i++){
                                    if(name.equals(s.get(i))){
                                        Service service = new Service();
                                        service.setName(name);
                                        service.setTitle((String)snapshot.child("title").getValue());
                                        service.setCost((String)snapshot.child("cost").getValue());
                                        service.setDescription((String)snapshot.child("description").getValue());
                                        service.setLink((String)snapshot.child("link").getValue());
                                        ArrayList<String> tags = (ArrayList<String>)snapshot.child("tags").getValue();
                                        service.setTags(tags);

                                        services.add(service);
                                    }
                                }
                                UsedServiceListAdapter usedServiceListAdapter = new UsedServiceListAdapter(UsedServicesActivity.this,services);
                                used_services_ListView.setAdapter(usedServiceListAdapter);
                            }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void addTagPoint(final List<Service> services, final DatabaseReference mDatabase, final String UserId)
    {
        final ArrayList<String> tag_names = new ArrayList<String>();
        for(int i = 0;i < services.size();i++) {
            Service service = services.get(i);
            tag_names.addAll(service.getTags());
        }

        mDatabase.child("tags").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot tagsSnapshot:dataSnapshot.getChildren()){
                    tags.put(tagsSnapshot.getValue(String.class),0);
                }
                for(int i=0;i<tag_names.size();i++){
                    String name =tag_names.get(i);
                    if(tags.containsKey(name)){
                        Integer point = tags.get(name);
                        tags.put(name,point+1);
                    }else{
                        tags.put(name,1);
                    }
                }
                mDatabase.child("users").child(UserId).child("tags_point").setValue(tags);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


}
