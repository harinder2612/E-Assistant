package com.harinder.e_assistant;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;

import static com.harinder.e_assistant.R.id.attnList;
import static com.harinder.e_assistant.attendance.totalClasses;

public class oldAttendance extends AppCompatActivity {

    ListView oldAttnList;
    DatabaseReference myref= FirebaseDatabase.getInstance().getReferenceFromUrl("https://calci-b1a59.firebaseio.com/");
    static int counter=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oldattendnace);
        oldAttnList= (ListView) findViewById(R.id.oldAttnList);
        Intent intent= getIntent();
        final String nameofList= intent.getStringExtra("le_chak");
        final int Tclasses= intent.getIntExtra("total",0);

        Toast.makeText(this, ""+nameofList, Toast.LENGTH_SHORT).show();

        final ProgressDialog pd=new ProgressDialog(this);
        pd.setTitle("Loading...");
        pd.show();

        final ArrayList<oldAttnItem> list= new ArrayList<>();


        final oldAttnAdapter adapter= new oldAttnAdapter(this,list);
        oldAttnList.setAdapter(adapter);

        myref.child("attendance_records").child("attn_lists").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                pd.dismiss();
                counter++;
                Map<String,String> map = (Map<String,String>) dataSnapshot.getValue();
                if(nameofList.equals(map.get("name")))
                {myref.child("attendance_records").child("attn_lists").child(""+counter).child("students").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Map<String,String> map2 = (Map<String,String>) dataSnapshot.getValue();
                        int num= Integer.parseInt(map2.get("present"));
                        float num2=(int)num;
                        num2= (num2/Tclasses)*100;
                        list.add(new oldAttnItem(map2.get("name"),map2.get("status"), (int) num2));
                        adapter.notifyDataSetChanged();
                        System.out.println("OUTPUT:>>>>" + map2);
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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

                }
                System.out.println("ehte gaur fermao: "+map);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {}

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onPause()
    {super.onPause();
        counter=-1;
    }
}
