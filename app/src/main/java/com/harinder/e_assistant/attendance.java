package com.harinder.e_assistant;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class attendance extends AppCompatActivity {

    ListView attnList;
    EditText studName;
    ImageView add,refresh;
    static int count=-1;
    android.widget.Toolbar toolbar;
    DatabaseReference myref= FirebaseDatabase.getInstance().getReferenceFromUrl("https://calci-b1a59.firebaseio.com/");

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        attnList= (ListView) findViewById(R.id.attnList);
        refresh= (ImageView) findViewById(R.id.refresh);
        studName= (EditText) findViewById(R.id.studName);
        add= (ImageView) findViewById(R.id.add);
        toolbar= (android.widget.Toolbar) findViewById(R.id.classtitileBar);


        final ArrayList<studentItem> list= new ArrayList<>();


        final studentAdapter adapter= new studentAdapter(attendance.this,list);
        attnList.setAdapter(adapter);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        myref.child("students").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String,String> map = (Map<String,String>) dataSnapshot.getValue();
                count++;
                   list.add(new studentItem(map.get("name"),map.get("state")));
                    adapter.notifyDataSetChanged();
                    attnList.setSelection(adapter.getCount()-1);

               // Toast.makeText(attendance.this, "count: "+count, Toast.LENGTH_SHORT).show();
                System.out.println("OUTPUT:" + map);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Map<String,String> map = (Map<String,String>) dataSnapshot.getValue();
                System.out.println("OUTPUT2:" + map);
                int index=0;
                for(int i=0;i<=count;i++)
                {
                    String name=map.get("name");
                    if(list.get(i).getName().equals(name))
                    {
                        System.out.println(""+name+" "+list.get(i).getName());
                        index=i;
                    }
                }
                list.get(index).setState(map.get("state"));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name=studName.getText().toString();
                studentItem newStudent=new studentItem(name,"absent");

                DatabaseReference chi = myref.child("students").child(""+(count+1));
                chi.setValue(newStudent);

                studName.setText("");
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(attendance.this, "Refresh called", Toast.LENGTH_SHORT).show();

                for(int i=0;i<=count;i++)
                {
                    DatabaseReference chi = myref.child("students").child(""+i).child("state");
                    chi.setValue("absent");
                }
            }
        });

    }


}
