package com.harinder.e_assistant;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

public class announcement extends AppCompatActivity {

    ListView annList;
    ImageView addAnn;
    EditText profAnn;
    DatabaseReference myref= FirebaseDatabase.getInstance().getReferenceFromUrl("https://calci-b1a59.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        annList= (ListView) findViewById(R.id.annList);
        addAnn= (ImageView) findViewById(R.id.addAnn);
        profAnn= (EditText) findViewById(R.id.profAnn);
        final ProgressDialog pd=new ProgressDialog(this);
        pd.setTitle("Loading...");
        pd.show();

        SimpleDateFormat formatter1 = new SimpleDateFormat("hh:mm a");
        long time=System.currentTimeMillis();
        final String dateString=formatter1.format(time);

        final ArrayList<announcementItem> list= new ArrayList<>();

        final announcementAdapter adapter= new announcementAdapter(this,list);
        annList.setAdapter(adapter);

        myref.child("announce").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                pd.dismiss();
                Map<String,String> map = (Map<String,String>) dataSnapshot.getValue();
                list.add(new announcementItem(map.get("name"),map.get("time"),map.get("annText")));
                adapter.notifyDataSetChanged();
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

        addAnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=profAnn.getText().toString();
                if(text.equals(""))
                {}
                else {
                    announcementItem newAnn = new announcementItem("Harinder Singh",dateString,text);
                    DatabaseReference chi = myref.child("announce").child(""+list.size());
                    chi.setValue(newAnn);

                    profAnn.setText("");
                }
            }
        });

    }
}
