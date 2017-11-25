package com.harinder.e_assistant;


import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class announcementAdapter extends ArrayAdapter<announcementItem> {
    public announcementAdapter(Context context,List<announcementItem> objects) {
        super(context,0, objects);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view= convertView;
        if(view==null)
        {
            view= LayoutInflater.from(getContext()).inflate(R.layout.announcement_item, parent, false);

        }

        announcementItem currentItem=getItem(position);
        TextView name=(TextView) view.findViewById(R.id.annName);
        TextView time=(TextView) view.findViewById(R.id.annTime);
        TextView annText=(TextView) view.findViewById(R.id.annText);

        name.setText(currentItem.getName());
        time.setText(currentItem.getTime());
        annText.setText(currentItem.getAnnText());

        return view;
    }
}
