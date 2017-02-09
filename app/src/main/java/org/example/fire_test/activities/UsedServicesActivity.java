package org.example.fire_test.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
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

/**
 * Created by takuya on 2/5/17.
 */

public class UsedServicesActivity extends AppCompatActivity{

    private ListView used_services_ListView;

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
        ArrayList<String> selected_genres = User.getInstance().getSelected_genres();

        used_services_ListView = (ListView)findViewById(R.id.select_used_services_list_view);
        try{
            for(int i=0;i<User.getInstance().getSelected_genres().size();i++){
                mDatabase.child("genres").child(selected_genres.get(i)).child("services").limitToFirst(3).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot serviceSnapshot:dataSnapshot.getChildren()){
                            final String service_name = serviceSnapshot.getValue(String.class);
                            mDatabase.child("services").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                                        Service service = snapshot.getValue(Service.class);
                                        if(service.getName().equals(service_name)){
                                            System.out.println(service);
                                            services.add(service);
                                        }
                                    }
                                }
                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            UsedServiceListAdapter usedServiceListAdapter = new UsedServiceListAdapter(this,getSampleServices());
            used_services_ListView.setAdapter(usedServiceListAdapter);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private ArrayList<Service> getSampleServices(){
        ArrayList<Service> services = new ArrayList<>();


        Service recme_service = new Service();
        recme_service.setName("Recme");
        recme_service.setTitle("Leading Mark, Inc.");
        ArrayList<String> recme_screen_shots = new ArrayList<>();
        recme_screen_shots.add("recme_scrn_1");
        recme_screen_shots.add("recme_scrn_2");
        recme_screen_shots.add("recme_scrn_3");
        recme_service.setPics(recme_screen_shots);
        services.add(recme_service);


        Service airbnb_service = new Service();
        airbnb_service.setName("Airbnb");
        airbnb_service.setTitle("Airbnb, Inc.");
        ArrayList<String> airbnb_screen_shots = new ArrayList<>();
        airbnb_screen_shots.add("airbnb_scrn_1");
        airbnb_screen_shots.add("airbnb_scrn_2");
        airbnb_screen_shots.add("airbnb_scrn_3");
        airbnb_service.setPics(airbnb_screen_shots);
        services.add(airbnb_service);


        return services;
    }

}
