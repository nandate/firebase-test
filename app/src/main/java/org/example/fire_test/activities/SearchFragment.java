package org.example.fire_test.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.example.fire_test.R;
import org.example.fire_test.adapters.SwipeDismissListViewTouchListener;

import java.util.ArrayList;

/**
 * Created by takuya on 2/18/17.
 */

public class SearchFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.search_fragment,container,false);

        return v;
    }


}
