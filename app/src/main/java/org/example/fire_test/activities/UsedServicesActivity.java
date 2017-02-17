package org.example.fire_test.activities;


import android.os.Bundle;
import android.os.health.SystemHealthManager;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * Created by takuya on 2/5/17.
 */

public class UsedServicesActivity extends AppCompatActivity{

    private ListView used_services_ListView;
    private Button used_services_button;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;

    private List<Service> services=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_used_services);
        Toolbar toolbar = (Toolbar) findViewById(R.id.select_used_services_toolbar);
        setSupportActionBar(toolbar);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        final ArrayList<String> selected_genres = User.getInstance().getSelected_genres();
        System.out.println(User.getInstance().getSelected_genres());


        used_services_ListView = (ListView)findViewById(R.id.select_used_services_list_view);
        used_services_button = (Button)findViewById(R.id.used_service_button);

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



}
