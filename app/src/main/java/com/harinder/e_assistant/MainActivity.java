package com.harinder.e_assistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    View attn,schedule,database;
    TextView subject;
    de.hdodenhof.circleimageview.CircleImageView profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        attn=findViewById(R.id.attendance);
        schedule=findViewById(R.id.schedule);
        subject= (TextView) findViewById(R.id.subject);
        profile= (CircleImageView) findViewById(R.id.profile);
        database=findViewById(R.id.database);

        SimpleDateFormat formatter1 = new SimpleDateFormat("hh:mm a");
        long time=System.currentTimeMillis();
        String dateString=formatter1.format(time);

        Date todayDate=null;

        try {
          todayDate= formatter1.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal1= Calendar.getInstance();
        cal1.setTime(todayDate);

        Date date2 = null;
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");
        String argDate="07:00 PM";
        try {

            date2 = formatter.parse(argDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal2= Calendar.getInstance();
        cal2.setTime(date2);

        if(cal1.before(cal2))
        {subject.setText(dateString);}
        else
        {subject.setText(argDate);}

        final Intent[] intent = new Intent[1];
        attn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent[0] =new Intent(MainActivity.this,attendance.class);
                startActivity(intent[0]);
            }
        });

        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent[0] =new Intent(MainActivity.this,schedule.class);
                startActivity(intent[0]);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent[0] =new Intent(MainActivity.this,profile.class);
                startActivity(intent[0]);
            }
        });

        database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent[0] =new Intent(MainActivity.this, com.harinder.e_assistant.database.class);
                startActivity(intent[0]);
            }
        });
    }
}
