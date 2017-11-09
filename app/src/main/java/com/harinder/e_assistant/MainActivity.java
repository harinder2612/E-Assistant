package com.harinder.e_assistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    View attn,schedule;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        attn=findViewById(R.id.attendance);
        schedule=findViewById(R.id.schedule);

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
    }
}
