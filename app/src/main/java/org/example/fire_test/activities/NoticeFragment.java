package org.example.fire_test.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.example.fire_test.R;
import org.json.JSONObject;

/**
 * Created by takuya on 2/20/17.
 */

public class NoticeFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.notice_fragment,container,false);

        final TextView mTextView = (TextView) v.findViewById(R.id.text);

        RequestQueue queue = Volley.newRequestQueue(this.getContext());
        String url = "https://mikke-rec.herokuapp.com/";

        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mTextView.setText("Response is: " + response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                     public void onErrorResponse(VolleyError error) {
                        mTextView.setText("That didn't work!");
                 }
             });
        queue.add(getRequest);
        return v;
    }
}
