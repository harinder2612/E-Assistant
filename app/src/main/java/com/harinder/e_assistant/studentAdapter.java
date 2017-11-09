package com.harinder.e_assistant;


import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class studentAdapter extends ArrayAdapter<studentItem>{


    public studentAdapter(Context context,List<studentItem> objects) {
        super(context,0, objects);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view= convertView;
        if(view==null)
        {
            view=LayoutInflater.from(getContext()).inflate(R.layout.attendance_item, parent, false);

        }

       studentItem currentStudentItem=getItem(position);
        TextView name=(TextView) view.findViewById(R.id.studname);
        ImageView state= (ImageView) view.findViewById(R.id.state);

        name.setText(currentStudentItem.getName());
        if(currentStudentItem.getState().equals("present"))
        {
            state.setImageResource(R.drawable.greendot);
        }
        else
        {
            state.setImageResource(R.drawable.reddot);
        }

        return view;
    }
}
