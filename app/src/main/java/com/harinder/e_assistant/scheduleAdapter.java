package com.harinder.e_assistant;


import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class scheduleAdapter extends ArrayAdapter<scheduleItem> {
    public scheduleAdapter(Context context, List<scheduleItem> objects) {
        super(context,0, objects);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view= convertView;
        if(view==null)
        {
            view= LayoutInflater.from(getContext()).inflate(R.layout.schedule_item, parent, false);

        }

        scheduleItem currentScheduleItem=getItem(position);
        final TextView subject=(TextView) view.findViewById(R.id.subject);
        Button room= view.findViewById(R.id.roomnum);
        TextView time= view.findViewById(R.id.time);
        TextView klass= view.findViewById(R.id.klass);

        room.setText(currentScheduleItem.getRoom());
        subject.setText(currentScheduleItem.getSubject());
        time.setText(currentScheduleItem.getTime());
        klass.setText(currentScheduleItem.getKlass());



        return view;
    }
}
