package com.example.demo;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by User on 3/14/2017.
 */

public class ListAdapter<c> extends ArrayAdapter<customerModel> {

    private static final String TAG = "PersonListAdapter";

    private Context mContext;
    private int mResource;
    Button call,delete;


    private static class ViewHolder {
        TextView name;
        TextView phone;

    }

    /**
     * Default constructor for the PersonListAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public ListAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the persons information
        String name = getItem(position).getName();
        int id = getItem(position).getId();
        String phone = getItem(position).getPhone();


        //Create the person object with the information
        customerModel customerModel1 = new customerModel(id,name,phone);

        //create the view result for showing the animation
        final View result;

        //ViewHolder object
        ViewHolder holder;
        holder= new ViewHolder();


        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);

            holder.name = (TextView) convertView.findViewById(R.id.textView1);
            holder.phone = (TextView) convertView.findViewById(R.id.textView2);


            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();

        }


//        Animation animation = AnimationUtils.loadAnimation(mContext,
//                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
//        result.startAnimation(animation);

        holder.name.setText(customerModel1.getName());
        holder.phone.setText(customerModel1.getPhone());
//        holder.sex.setText(customerModel1.getSex());


        return convertView;
    }
}
