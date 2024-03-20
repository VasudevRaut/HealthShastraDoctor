package com.example.healthshastradr;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.zegocloud.uikit.prebuilt.call.ZegoUIKitPrebuiltCallConfig;
import com.zegocloud.uikit.prebuilt.call.ZegoUIKitPrebuiltCallFragment;

public class VideoCallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_call);

        String callID = getIntent().getStringExtra("callID");
        String userID = getIntent().getStringExtra("userID");

        long appID = 117767004;  // Replace with your actual Zego app ID
        String appSign = "ae4b01eef2a295bb590673ebb7c592ec9728920668e97abd481a66c717bb3f2a";  // Replace with your actual Zego app sign
//        String callID = "738";
//        String userID = "996";
        ZegoUIKitPrebuiltCallConfig config = ZegoUIKitPrebuiltCallConfig.oneOnOneVideoCall();

        ZegoUIKitPrebuiltCallFragment fragment = ZegoUIKitPrebuiltCallFragment.newInstance(
                appID, appSign, callID, userID, userID, config);  // Using the same user ID as the user name

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}