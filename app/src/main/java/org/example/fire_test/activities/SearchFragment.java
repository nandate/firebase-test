package org.example.fire_test.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.example.fire_test.R;

/**
 * Created by takuya on 2/18/17.
 */

public class SearchFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        return inflater.inflate(R.layout.search_fragment,container,false);
    }
}
