package org.example.fire_test.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.example.fire_test.R;
import org.example.fire_test.models.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by takuya on 3/12/17.
 */

public class RecyclearViewAdapter extends RecyclerView.Adapter<RecyclearViewAdapter.ServiceViewHolder>{
    List<Service> recommend = new ArrayList<>();

    public RecyclearViewAdapter(DatabaseReference ref){
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot service:dataSnapshot.getChildren()){
                    recommend.add(service.getValue(Service.class));
                }
                notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public int getItemCount(){
        return recommend.size();
    }

    @Override
    public ServiceViewHolder onCreateViewHolder(ViewGroup viewGroup,int position){
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recommend_item,viewGroup,false);
        ServiceViewHolder svh = new ServiceViewHolder(v);
        return svh;
    }

    @Override
    public void onBindViewHolder(ServiceViewHolder serviceViewHolder,int position){
        serviceViewHolder.name.setText(recommend.get(position).getName());
        serviceViewHolder.company.setText(recommend.get(position).getTitle());
        serviceViewHolder.description.setText(recommend.get(position).getDescription());
    }

    public static class ServiceViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView name;
        TextView company;
        TextView description;

        ServiceViewHolder(View itemView){
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.card_view);
            name = (TextView)itemView.findViewById(R.id.name);
            company = (TextView)itemView.findViewById(R.id.company);
            description = (TextView)itemView.findViewById(R.id.description);

        }
    }
}
