package com.example.healthshastradr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PreVideo extends AppCompatActivity {

    EditText etCallID, etUserID;
    Button btnCall;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prevideo);

        etCallID = findViewById(R.id.etCallID);
        etUserID = findViewById(R.id.etUserID);
        btnCall = findViewById(R.id.btnCall);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String callID = etCallID.getText().toString().trim();
                String userID = etUserID.getText().toString().trim();
                if (!callID.isEmpty() && !userID.isEmpty()) {
                    Intent intent = new Intent(PreVideo.this, VideoCallActivity.class);
                    intent.putExtra("callID", callID);
                    intent.putExtra("userID", userID);
                    startActivity(intent);
                } else {
                    // Show a message that fields are empty
                }
            }
        });
    }
}