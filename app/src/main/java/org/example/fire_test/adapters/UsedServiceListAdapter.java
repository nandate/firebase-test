package org.example.fire_test.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.example.fire_test.R;
import org.example.fire_test.models.Service;
import org.example.fire_test.models.User;

import java.util.List;

/**
 * Created by takuya on 2/7/17.
 */

public class UsedServiceListAdapter extends ArrayAdapter<Service>{


    public UsedServiceListAdapter(Context context, List<Service> services){
        super(context, R.layout.used_services_listview_item,services);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        Service service = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.used_services_listview_item,parent,false);
        }

        String service_name = service.getName();
        convertView.setTag(service);
        convertView.setOnClickListener(new ServiceRowClick());


        TextView used_service_name_text_view = (TextView) convertView.findViewById(R.id.service_name_textView);
        //Button button = (Button)convertView.findViewById(R.id.select_used_service_toggleButton);

        used_service_name_text_view.setText(service.getName());

        /*

        int screen_shot_1_identifier = getContext().getResources().getIdentifier(service.getPics().get(0),"drawable",getContext().getPackageName());
        ImageView screen_shot_1_imageView = (ImageView) convertView.findViewById(R.id.scrn_shot_1_imageView);
        screen_shot_1_imageView.setImageResource(screen_shot_1_identifier);

        int screen_shot_2_identifier = getContext().getResources().getIdentifier(service.getPics().get(1),"drawable",getContext().getPackageName());
        ImageView screen_shot_2_imageView = (ImageView) convertView.findViewById(R.id.scrn_shot_2_imageView);
        screen_shot_2_imageView.setImageResource(screen_shot_2_identifier);

        int screen_shot_3_identifier = getContext().getResources().getIdentifier(service.getPics().get(2),"drawable",getContext().getPackageName());
        ImageView screen_shot_3_imageView = (ImageView) convertView.findViewById(R.id.scrn_shot_3_imageView);
        screen_shot_3_imageView.setImageResource(screen_shot_3_identifier);


        int logo_identifier = getContext().getResources().getIdentifier(service.getName().toLowerCase(),"drawable",getContext().getPackageName());
        ImageView logo_imageView = (ImageView) convertView.findViewById(R.id.logo_imageView);
        logo_imageView.setImageResource(logo_identifier);
        */


        /*if(User.getInstance().getused_services().indexOf(service_name) >= 0 ) {
            convertView.findViewById(R.id.select_used_service_toggleButton).setBackgroundColor(Color.rgb(38, 198, 218));
        }else{
            convertView.findViewById(R.id.select_used_service_toggleButton).setBackgroundColor(Color.rgb(255,255,255));
        }
        */
        return convertView;

    }

    private class ServiceRowClick implements View.OnClickListener{
        @Override
        public void onClick(View v){
            Service service = (Service)v.getTag();
            User user = User.getInstance();
            System.out.println(service);

            System.out.println(user.getUsed_services());
            user.addUsedService(service);


            /*if(user.hasService(service)){
                user.removeUsedService(service);
            }else{
                user.addUsedService(service);
            }
            */

        }

    }

}
