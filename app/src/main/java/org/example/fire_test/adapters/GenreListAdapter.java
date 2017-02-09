package org.example.fire_test.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

import org.example.fire_test.models.Genre;
import org.example.fire_test.models.User;
import org.example.fire_test.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by takuya on 1/27/17.
 */

public class GenreListAdapter extends ArrayAdapter<Genre>{

    public GenreListAdapter(Context context, List<Genre> genres){
        super(context,R.layout.genre_listview_item,genres);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Genre genre =getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.genre_listview_item,parent,false);
        }

        convertView.setVisibility(View.VISIBLE);
        convertView.setTag(genre.getName());
        convertView.setOnClickListener(new GenreRowClick());

        String genre_name = genre.getName();
        String imageName = genre.getImage();
        int identifier = getContext().getResources().getIdentifier(imageName,"drawable",getContext().getPackageName());
        ImageView genre_image = (ImageView)convertView.findViewById(R.id.genre_imageView);
        TextView genre_name_text_View = (TextView) convertView.findViewById(R.id.genre_name_textView);

        genre_name_text_View.setText(genre_name);
        genre_image.setImageResource(identifier);
        LinearLayout service_logos_linearLayout = (LinearLayout)convertView.findViewById(R.id.service_logos_linearLayout);
        service_logos_linearLayout.removeAllViews();

        Collections.shuffle(genre.getServices());

        if(User.getInstance().getSelected_genres().indexOf(genre_name) >= 0 )
        {
            convertView.findViewById(R.id.done_imageView).setVisibility(View.VISIBLE);
        }else
        {
            convertView.findViewById(R.id.done_imageView).setVisibility(View.INVISIBLE);
        }

        return convertView;
    }

    private class GenreRowClick implements View.OnClickListener{
        @Override
        public void onClick(View v){

            String genreName = (String)v.getTag();
            ImageView done_image = (ImageView) v.findViewById(R.id.done_imageView);
            User user=User.getInstance();

            if(user.hasGenre(genreName)) {
                user.removeSelectedGenre(genreName);
                done_image.setVisibility(View.INVISIBLE);
            }else{
                user.addSelectedGenre(genreName);
                done_image.setVisibility(View.VISIBLE);
            }

        }

    }

}
