package org.example.fire_test.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import org.example.fire_test.R;
import org.example.fire_test.models.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import de.timroes.android.listview.EnhancedListView;

/**
 * Created by takuya on 2/20/17.
 */

public class RecommendFragment extends Fragment{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.recommend_fragment,container,false);

        return v;
    }




}
