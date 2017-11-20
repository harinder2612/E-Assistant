package com.harinder.e_assistant;

import android.app.ProgressDialog;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class attendance extends AppCompatActivity {

    ListView attnList;
    EditText studName;
    ImageView add,refresh,save;
    android.widget.Toolbar toolbar;
    static int totalClasses=-1;
    DatabaseReference myref= FirebaseDatabase.getInstance().getReferenceFromUrl("https://calci-b1a59.firebaseio.com/");

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        attnList= (ListView) findViewById(R.id.attnList);
        refresh= (ImageView) findViewById(R.id.refresh);
        save= (ImageView) findViewById(R.id.save);
        studName= (EditText) findViewById(R.id.studName);
        add= (ImageView) findViewById(R.id.add);
        toolbar= (android.widget.Toolbar) findViewById(R.id.classtitileBar);

        final ProgressDialog pd=new ProgressDialog(this);
        pd.setTitle("Loading...");
        pd.show();


        final ArrayList<studentItem> list= new ArrayList<>();
        final ArrayList<studentItem2> attnRecords= new ArrayList<>();
        final ArrayList<String> ClassesAttended=new ArrayList<>();

        final studentAdapter adapter= new studentAdapter(attendance.this,list);
        attnList.setAdapter(adapter);



        //childeventListener to child of students in firebase
        myref.child("students").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                pd.dismiss();
                Map<String,String> map = (Map<String,String>) dataSnapshot.getValue();
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
                for(int i=0;i<list.size();i++)
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
                attnList.setSelection(index);
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



        myref.child("attendance_records").child("attn_lists").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               totalClasses++;
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

        myref.child("attendance_records").child("overall_attn").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String,String> map = (Map<String,String>) dataSnapshot.getValue();
                ClassesAttended.add(map.get("presentin"));
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

        //adding new students to the firebase and updating list
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name=studName.getText().toString();
                if(name.equals(""))
                {}
                else {
                    studentItem newStudent = new studentItem(name, "absent");
                    DatabaseReference chi = myref.child("students").child(""+list.size());
                    chi.setValue(newStudent);

                    //adding student to attendance_records
                    DatabaseReference ch = myref.child("attendance_records").child("overall_attn").child(""+list.size());
                    ch.setValue(new overall_attn(name,"0"));


                    studName.setText("");
                }
            }
        });


        //refreshing attendance by marking absent against all students
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(attendance.this, "Attendance Reset", Toast.LENGTH_SHORT).show();
                for(int i=0;i<list.size();i++)
                {
                    DatabaseReference chi = myref.child("students").child(""+i).child("state");
                    chi.setValue("absent");
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Todays date and time
                SimpleDateFormat formatter1 = new SimpleDateFormat("E, dd MMM yyyy, hh:mm:ss a");
                long day=System.currentTimeMillis();
                final String dateString=formatter1.format(day);

                for(int i=0;i<list.size();i++)
                {
                    int num= Integer.parseInt(ClassesAttended.get(i));

                    if(list.get(i).getState().equals("present"))
                    {
                        num++;
                        DatabaseReference chi = myref.child("attendance_records").child("overall_attn").child(""+i).child("presentin");
                        chi.setValue(""+num);
                        ClassesAttended.set(i,""+num);
                    }
                    attnRecords.add(i,new studentItem2(list.get(i).getName(),list.get(i).getState(),""+num));


                }

                Toast.makeText(attendance.this, "Attendance Saved", Toast.LENGTH_SHORT).show();

                DatabaseReference ch = myref.child("attendance_records").child("attn_lists").child(""+(totalClasses+1)).child("name");
                ch.setValue(dateString);

                for(int i=0;i<list.size();i++)
                {
                    DatabaseReference chi = myref.child("attendance_records").child("attn_lists").child(""+(totalClasses+1)).child("students").child(""+i);
                    chi.setValue(attnRecords.get(i));
                }
            }
        });


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

}
