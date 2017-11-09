package com.harinder.e_assistant;

import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class schedule extends AppCompatActivity {

    ListView scheduleList;
    android.widget.Toolbar toolbar;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        scheduleList= (ListView) findViewById(R.id.scheduleList);
        toolbar= (android.widget.Toolbar) findViewById(R.id.scheduleBar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        ArrayList<scheduleItem> list= new ArrayList<>();

        list.add(new scheduleItem("Class Name","000","Class","00:00 AM"));
        list.add(new scheduleItem("Class Name","000","Class","00:00 AM"));
        list.add(new scheduleItem("Class Name","000","Class","00:00 AM"));
        list.add(new scheduleItem("Class Name","000","Class","00:00 AM"));
        list.add(new scheduleItem("Class Name","000","Class","00:00 AM"));
        list.add(new scheduleItem("Class Name","000","Class","00:00 AM"));
        list.add(new scheduleItem("Class Name","000","Class","00:00 AM"));
        list.add(new scheduleItem("Class Name","000","Class","00:00 AM"));

        scheduleAdapter adapter= new scheduleAdapter(schedule.this,list);
        scheduleList.setAdapter(adapter);

        scheduleList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(schedule.this);
                builder.setTitle("Edit");

                LinearLayout layout = new LinearLayout(schedule.this);
                layout.setOrientation(LinearLayout.VERTICAL);

// Set up the input
                final EditText room = new EditText(schedule.this);
                final EditText subject= new EditText(schedule.this);
                final EditText time= new EditText(schedule.this);
                final EditText klass= new EditText(schedule.this);

                room.setHint(" Enter Room No.");
                subject.setHint(" Enter Subject Name");
                time.setHint(" Enter Time ");
                klass.setHint(" Enter Class Name");

                room.setInputType(InputType.TYPE_CLASS_TEXT);
                subject.setInputType(InputType.TYPE_CLASS_TEXT);
                time.setInputType(InputType.TYPE_CLASS_TEXT);
                klass.setInputType(InputType.TYPE_CLASS_TEXT);

                layout.addView(room);
                layout.addView(subject);
                layout.addView(time);
                layout.addView(klass);

                builder.setView(layout);

// Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(schedule.this, ""+room.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
                return false;
            }
        });
    }

}
