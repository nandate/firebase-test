package org.example.fire_test.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.android.volley.RequestQueue;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.example.fire_test.R;
import org.example.fire_test.adapters.RecyclearViewAdapter;
import org.example.fire_test.models.Service;

import java.util.ArrayList;
import java.util.List;

import de.timroes.android.listview.EnhancedListView;

/**
 * Created by takuya on 2/20/17.
 */

public class RecommendFragment extends Fragment{
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabase;
    private FirebaseUser mFirebaseUser;
    private String mUserId;
    private List<Service> recommend = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.recommend_fragment,container,false);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mUserId =mFirebaseUser.getUid();

        RecyclerView mRecyclerView = (RecyclerView)v.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);


        RecyclearViewAdapter madapter = new RecyclearViewAdapter(mDatabase.child("users").child(mUserId).child("recommend"));
        mRecyclerView.setAdapter(madapter);


        return v;
    }




}
