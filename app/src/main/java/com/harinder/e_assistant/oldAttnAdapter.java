package com.harinder.e_assistant;



import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class oldAttnAdapter extends ArrayAdapter<oldAttnItem> {

    private Context context;

    public oldAttnAdapter(Context context,List<oldAttnItem> objects) {
        super(context,0, objects);
        this.context=context;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view= convertView;
        if(view==null)
        {
            view= LayoutInflater.from(getContext()).inflate(R.layout.attnrecord_item, parent, false);

        }

        oldAttnItem currentItem=getItem(position);
        de.hdodenhof.circleimageview.CircleImageView circleState= view.findViewById(R.id.circleState);
        TextView name=(TextView) view.findViewById(R.id.studname);
        ImageView mail= (ImageView) view.findViewById(R.id.mail);
        ProgressBar progressBar= view.findViewById(R.id.progressBar);

        name.setText(currentItem.getName());
        if(currentItem.getState().equals("present"))
        {
            circleState.setBorderColor(0xFF00FF00);
        }
        else
        {
            circleState.setBorderColor(0xFFFF0000);
        }

        if(currentItem.getProgress()>70) {
            progressBar.setProgress(currentItem.getProgress());
            progressBar.setProgressTintList(ColorStateList.valueOf(Color.GREEN));
        }else
        {
            progressBar.setProgress(currentItem.getProgress());
            progressBar.setProgressTintList(ColorStateList.valueOf(Color.RED));
        }

        final Intent intent= new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Warning about low attendance");
        intent.putExtra(Intent.EXTRA_TEXT, "This is to warn you that your attendance is lower than 70%.");

        if(currentItem.getProgress()<70) {
            mail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (intent.resolveActivity(context.getPackageManager()) != null) {
                        context.startActivity(intent);
                    }
                }
            });
        }


        return view;
    }
}
