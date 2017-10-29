package com.harinder.e_assistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class attendance extends AppCompatActivity {

    ListView attnList;
    EditText studName;
    ImageView add;
    static int count=1;
    DatabaseReference myref= FirebaseDatabase.getInstance().getReferenceFromUrl("https://calci-b1a59.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        attnList= (ListView) findViewById(R.id.attnList);
        studName= (EditText) findViewById(R.id.studName);
        add= (ImageView) findViewById(R.id.add);

        final ArrayList<String> studList= new ArrayList<>();
        final ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,studList);
        attnList.setAdapter(adapter);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studList.add(studName.getText().toString());
                adapter.notifyDataSetChanged();
                attnList.setSelection(adapter.getCount()-1);
                DatabaseReference chi = myref.child(""+count);
                chi.setValue(studName.getText().toString());
                count++;
                studName.setText("");
            }
        });

    }
}
