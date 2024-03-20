package com.example.healthshastradr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        LinearLayout chekapp = findViewById(R.id.scheduleAp);
        LinearLayout statistics = findViewById(R.id.statistics);

        LinearLayout onoff = findViewById(R.id.opdtimings);


        statistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, Profile.class);
                startActivity(intent);

            }
        });



        chekapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, Schedules.class);
                startActivity(intent);
            }
        });

        LinearLayout opd = findViewById(R.id.opdtimings);

        chekapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, Schedules.class);
                startActivity(intent);
            }
        });

//        LinearLayout statistics = findViewById(R.id.statistics);

        chekapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, Schedules.class);
                startActivity(intent);
            }
        });


        onoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, ChatBot.class);
                startActivity(intent);
            }
        });


        findViewById(R.id.activeClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, ActiveClose.class);
                startActivity(intent);
            }
        });



    }
}