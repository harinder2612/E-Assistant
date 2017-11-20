package com.harinder.e_assistant;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

public class schedule extends AppCompatActivity {

    ListView scheduleList;
    TextView tuesday,monday,wednesday,thursday,friday,saturday;
    Button addPeriod;
    DatabaseReference myref= FirebaseDatabase.getInstance().getReferenceFromUrl("https://calci-b1a59.firebaseio.com/");
    static int flag=0;

    android.widget.Toolbar toolbar;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        scheduleList= (ListView) findViewById(R.id.scheduleList);
        tuesday= (TextView) findViewById(R.id.tuesday);
        monday= (TextView) findViewById(R.id.monday);
        wednesday= (TextView) findViewById(R.id.wednesday);
        thursday= (TextView) findViewById(R.id.thursday);
        friday= (TextView) findViewById(R.id.friday);
        saturday= (TextView) findViewById(R.id.saturday);
        addPeriod= (Button) findViewById(R.id.addPeriod);
        toolbar= (android.widget.Toolbar) findViewById(R.id.scheduleBar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        final ProgressDialog pd=new ProgressDialog(this);
        pd.setTitle("Loading...");
        pd.show();

        SimpleDateFormat formatter1 = new SimpleDateFormat("E");
        long day=System.currentTimeMillis();
        String dateString=formatter1.format(day);


        //Arraylist for 6 days of week
        final ArrayList<scheduleItem> mon= new ArrayList<>();
        final ArrayList<scheduleItem> tue= new ArrayList<>();
        final ArrayList<scheduleItem> wed= new ArrayList<>();
        final ArrayList<scheduleItem> thu= new ArrayList<>();
        final ArrayList<scheduleItem> fri= new ArrayList<>();
        final ArrayList<scheduleItem> sat= new ArrayList<>();

        //6 adapters for 6 lists, one fr each day
        final scheduleAdapter MonAdapter;
        final scheduleAdapter TueAdapter;
        final scheduleAdapter WedAdapter;
        final scheduleAdapter ThuAdapter;
        final scheduleAdapter FriAdapter;
        final scheduleAdapter SatAdapter;

        MonAdapter = new scheduleAdapter(schedule.this,mon);
        TueAdapter = new scheduleAdapter(schedule.this,tue);
        WedAdapter = new scheduleAdapter(schedule.this,wed);
        ThuAdapter = new scheduleAdapter(schedule.this,thu);
        FriAdapter = new scheduleAdapter(schedule.this,fri);
        SatAdapter = new scheduleAdapter(schedule.this,sat);


        //Fetching data of Monday from firebase
        myref.child("schedule").child("monday").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                pd.dismiss();
                Map<String,String> map = (Map<String,String>) dataSnapshot.getValue();
                mon.add(new scheduleItem(map.get("subject"),map.get("room"),map.get("klass"),map.get("time")));
                MonAdapter.notifyDataSetChanged();
                System.out.println("OUTPUT Dekh:" + map);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Map<String,String> map = (Map<String,String>) dataSnapshot.getValue();
                int index=0;
                for(int i=0;i<mon.size();i++)
                {
                    String time=map.get("time");
                    if(mon.get(i).getTime().equals(time))
                    {
                        System.out.println(""+time+" "+mon.get(i).getTime());
                        index=i;
                    }
                }
                mon.set(index,new scheduleItem(map.get("subject"),map.get("room"),map.get("klass"),map.get("time")));
                MonAdapter.notifyDataSetChanged();
                scheduleList.setSelection(index);
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


        //fetching data of Tuesday from firebase
        myref.child("schedule").child("tuesday").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                pd.dismiss();
                Map<String,String> map = (Map<String,String>) dataSnapshot.getValue();
                tue.add(new scheduleItem(map.get("subject"),map.get("room"),map.get("klass"),map.get("time")));
                TueAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Map<String,String> map = (Map<String,String>) dataSnapshot.getValue();
                int index=0;
                for(int i=0;i<tue.size();i++)
                {
                    String time=map.get("time");
                    if(tue.get(i).getTime().equals(time))
                    {
                        System.out.println(""+time+" "+tue.get(i).getTime());
                        index=i;
                    }
                }
                tue.set(index,new scheduleItem(map.get("subject"),map.get("room"),map.get("klass"),map.get("time")));
                TueAdapter.notifyDataSetChanged();
                scheduleList.setSelection(index);
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


        //fetching data of Wednesday from firebase
        myref.child("schedule").child("wednesday").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                pd.dismiss();
                Map<String,String> map = (Map<String,String>) dataSnapshot.getValue();
                wed.add(new scheduleItem(map.get("subject"),map.get("room"),map.get("klass"),map.get("time")));
                WedAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Map<String,String> map = (Map<String,String>) dataSnapshot.getValue();
                System.out.println("OUTPUT Dekh Na:" + map);
                int index=0;
                for(int i=0;i<wed.size();i++)
                {
                    String time=map.get("time");
                    if(wed.get(i).getTime().equals(time))
                    {
                        System.out.println(""+time+" "+wed.get(i).getTime());
                        index=i;
                    }
                }
                wed.set(index,new scheduleItem(map.get("subject"),map.get("room"),map.get("klass"),map.get("time")));
                WedAdapter.notifyDataSetChanged();
                scheduleList.setSelection(index);
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


        //fetching data of Thusday from firebase
        myref.child("schedule").child("thursday").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                pd.dismiss();
                Map<String,String> map = (Map<String,String>) dataSnapshot.getValue();
                thu.add(new scheduleItem(map.get("subject"),map.get("room"),map.get("klass"),map.get("time")));
                ThuAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Map<String,String> map = (Map<String,String>) dataSnapshot.getValue();
                System.out.println("OUTPUT Dekh Na:" + map);
                int index=0;
                for(int i=0;i<thu.size();i++)
                {
                    String time=map.get("time");
                    if(thu.get(i).getTime().equals(time))
                    {
                        System.out.println(""+time+" "+thu.get(i).getTime());
                        index=i;
                    }
                }
                thu.set(index,new scheduleItem(map.get("subject"),map.get("room"),map.get("klass"),map.get("time")));
                ThuAdapter.notifyDataSetChanged();
                scheduleList.setSelection(index);
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



        //fetching data of Friday from firebase
        myref.child("schedule").child("friday").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                pd.dismiss();
                Map<String,String> map = (Map<String,String>) dataSnapshot.getValue();
                fri.add(new scheduleItem(map.get("subject"),map.get("room"),map.get("klass"),map.get("time")));
                FriAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Map<String,String> map = (Map<String,String>) dataSnapshot.getValue();
                System.out.println("OUTPUT Dekh Na:" + map);
                int index=0;
                for(int i=0;i<fri.size();i++)
                {
                    String time=map.get("time");
                    if(fri.get(i).getTime().equals(time))
                    {
                        System.out.println(""+time+" "+fri.get(i).getTime());
                        index=i;
                    }
                }
                fri.set(index,new scheduleItem(map.get("subject"),map.get("room"),map.get("klass"),map.get("time")));
                FriAdapter.notifyDataSetChanged();
                scheduleList.setSelection(index);
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



        //fetching data of Saturday from firebase
        myref.child("schedule").child("saturday").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                pd.dismiss();
                Map<String,String> map = (Map<String,String>) dataSnapshot.getValue();
                sat.add(new scheduleItem(map.get("subject"),map.get("room"),map.get("klass"),map.get("time")));
                SatAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Map<String,String> map = (Map<String,String>) dataSnapshot.getValue();
                System.out.println("OUTPUT Dekh Na:" + map);
                int index=0;
                for(int i=0;i<sat.size();i++)
                {
                    String time=map.get("time");
                    if(sat.get(i).getTime().equals(time))
                    {
                        System.out.println(""+time+" "+sat.get(i).getTime());
                        index=i;
                    }
                }
                sat.set(index,new scheduleItem(map.get("subject"),map.get("room"),map.get("klass"),map.get("time")));
                SatAdapter.notifyDataSetChanged();
                scheduleList.setSelection(index);
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



        monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scheduleList.setAdapter(MonAdapter);
                toolbar.setTitle("Schedule "+"("+"MON"+")");
                flag=1;
            }
        });

        tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scheduleList.setAdapter(TueAdapter);
                toolbar.setTitle("Schedule "+"("+"TUE"+")");
                flag=2;
            }
        });

        wednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scheduleList.setAdapter(WedAdapter);
                toolbar.setTitle("Schedule "+"("+"WED"+")");
                flag=3;
            }
        });

        thursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scheduleList.setAdapter(ThuAdapter);
                toolbar.setTitle("Schedule "+"("+"THU"+")");
                flag=4;
            }
        });

        friday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scheduleList.setAdapter(FriAdapter);
                toolbar.setTitle("Schedule "+"("+"FRI"+")");
                flag=5;
            }
        });

        saturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scheduleList.setAdapter(SatAdapter);
                toolbar.setTitle("Schedule "+"("+"SAT"+")");
                flag=6;
            }
        });


        //To edit the existing periods from the lists and also update on firebase
        scheduleList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                Toast.makeText(schedule.this, ""+i, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(schedule.this, "Period Edited", Toast.LENGTH_SHORT).show();

                        switch (flag)
                        {
                            case 1:{
                                DatabaseReference chi = myref.child("schedule").child("monday").child(""+i);
                                chi.setValue(new scheduleItem(subject.getText().toString(),room.getText().toString(),klass.getText().toString(),time.getText().toString()));
                            }break;

                            case 2:{
                                DatabaseReference chi = myref.child("schedule").child("tuesday").child(""+i);
                                chi.setValue(new scheduleItem(subject.getText().toString(),room.getText().toString(),klass.getText().toString(),time.getText().toString()));
                            }break;

                            case 3:{
                                DatabaseReference chi = myref.child("schedule").child("wednesday").child(""+i);
                                chi.setValue(new scheduleItem(subject.getText().toString(),room.getText().toString(),klass.getText().toString(),time.getText().toString()));
                            }break;

                            case 4:{
                                DatabaseReference chi = myref.child("schedule").child("thursday").child(""+i);
                                chi.setValue(new scheduleItem(subject.getText().toString(),room.getText().toString(),klass.getText().toString(),time.getText().toString()));
                            }break;

                            case 5:{
                                DatabaseReference chi = myref.child("schedule").child("friday").child(""+i);
                                chi.setValue(new scheduleItem(subject.getText().toString(),room.getText().toString(),klass.getText().toString(),time.getText().toString()));
                            }break;

                            case 6:{
                                DatabaseReference chi = myref.child("schedule").child("saturday").child(""+i);
                                chi.setValue(new scheduleItem(subject.getText().toString(),room.getText().toString(),klass.getText().toString(),time.getText().toString()));
                            }break;
                        }

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


        //To add new features to lists as well as on firebase
        addPeriod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(schedule.this);
                builder.setTitle("Add");

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
                        Toast.makeText(schedule.this, "New Period Added", Toast.LENGTH_SHORT).show();

                        switch (flag)
                        {
                            case 1:{
                                DatabaseReference chi = myref.child("schedule").child("monday").child(""+mon.size());
                                chi.setValue(new scheduleItem(subject.getText().toString(),room.getText().toString(),klass.getText().toString(),time.getText().toString()));
                            }break;

                            case 2:{
                                DatabaseReference chi = myref.child("schedule").child("tuesday").child(""+tue.size());
                                chi.setValue(new scheduleItem(subject.getText().toString(),room.getText().toString(),klass.getText().toString(),time.getText().toString()));
                            }break;

                            case 3:{
                                DatabaseReference chi = myref.child("schedule").child("wednesday").child(""+wed.size());
                                chi.setValue(new scheduleItem(subject.getText().toString(),room.getText().toString(),klass.getText().toString(),time.getText().toString()));
                            }break;

                            case 4:{
                                DatabaseReference chi = myref.child("schedule").child("thursday").child(""+thu.size());
                                chi.setValue(new scheduleItem(subject.getText().toString(),room.getText().toString(),klass.getText().toString(),time.getText().toString()));
                            }break;

                            case 5:{
                                DatabaseReference chi = myref.child("schedule").child("friday").child(""+fri.size());
                                chi.setValue(new scheduleItem(subject.getText().toString(),room.getText().toString(),klass.getText().toString(),time.getText().toString()));
                            }break;

                            case 6:{
                                DatabaseReference chi = myref.child("schedule").child("saturday").child(""+sat.size());
                                chi.setValue(new scheduleItem(subject.getText().toString(),room.getText().toString(),klass.getText().toString(),time.getText().toString()));
                            }break;
                        }

                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });


        switch (dateString)
        {
            case "Mon":monday.performClick();
                break;
            case "Tue":tuesday.performClick();
                break;
            case "Wed":wednesday.performClick();
                break;
            case "Thu":thursday.performClick();
                break;
            case "Fri":friday.performClick();
                break;
            case "Sat":saturday.performClick();
                break;
            case "Sun":monday.performClick();
                break;
        }
    }


}
