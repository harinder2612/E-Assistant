package com.harinder.e_assistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;

public class makeaList extends AppCompatActivity {

    TextView a,b,c,d,e,f,g,h,i,j,k,l;
    ImageView refresh;
    DatabaseReference myref= FirebaseDatabase.getInstance().getReferenceFromUrl("https://calci-b1a59.firebaseio.com/");
    static int size=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makea_list);
        refresh= (ImageView) findViewById(R.id.getdata);
        a= (TextView) findViewById(R.id.a);
        b= (TextView) findViewById(R.id.b);
        c= (TextView) findViewById(R.id.c);
        d= (TextView) findViewById(R.id.d);
        e= (TextView) findViewById(R.id.e);
        f= (TextView) findViewById(R.id.f);
        g= (TextView) findViewById(R.id.g);
        h= (TextView) findViewById(R.id.h);
        i= (TextView) findViewById(R.id.i);
        j= (TextView) findViewById(R.id.j);
        k= (TextView) findViewById(R.id.k);
        l= (TextView) findViewById(R.id.l);
        // String marks[]=new String [12];
        final ArrayList<listsItem> marks= new ArrayList<>();

        myref.child("lists").child("cse one").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String,String> map = (Map<String,String>) dataSnapshot.getValue();
                marks.add(new listsItem(map.get("name"),map.get("networks"),map.get("science"),map.get("wireless")));
                size= (int) dataSnapshot.getChildrenCount();
                System.out.println("OUTPUT2:" + map);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Map<String,String> map = (Map<String,String>) dataSnapshot.getValue();

                switch (map.get("name"))
                {
                    case "Harinder":{a.setText(map.get("networks"));
                        b.setText(map.get("science"));
                        c.setText(map.get("wireless"));}break;

                    case "Amanjeet":{d.setText(map.get("networks"));
                        e.setText(map.get("science"));
                        f.setText(map.get("wireless"));}break;

                    case "Prabhnoor":{g.setText(map.get("networks"));
                        h.setText(map.get("science"));
                        i.setText(map.get("wireless"));}break;

                    case "Rupinder":{j.setText(map.get("networks"));
                        k.setText(map.get("science"));
                        l.setText(map.get("wireless"));}break;
                }

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

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (int i=0;i<size;i++)
                {
                    DatabaseReference chi = myref.child("lists").child("cse one").child(""+i);
                    chi.setValue(new listsItem(marks.get(i).getName(),"","",""));
                }
//                for (int z=0;z<4;z++)
//                {
//                    switch (z)
//                    {
//                        case 0:{ a.setText(marks.get(z).getNetworks());
//                                 b.setText(marks.get(z).getScience());
//                                 c.setText(marks.get(z).getWireless());
//                                }break;
//
//                        case 1:{ d.setText(marks.get(z).getNetworks());
//                            e.setText(marks.get(z).getScience());
//                            f.setText(marks.get(z).getWireless());
//                        }break;
//
//                        case 2:{ g.setText(marks.get(z).getNetworks());
//                            h.setText(marks.get(z).getScience());
//                            i.setText(marks.get(z).getWireless());
//                        }break;
//
//                        case 3:{ j.setText(marks.get(z).getNetworks());
//                            k.setText(marks.get(z).getScience());
//                            l.setText(marks.get(z).getWireless());
//                        }break;
//                    }
//
//                }
            }
        });
    }
}
