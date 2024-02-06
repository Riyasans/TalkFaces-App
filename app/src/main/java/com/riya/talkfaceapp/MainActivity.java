package com.riya.talkfaceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService;

public class MainActivity extends AppCompatActivity {
    EditText userIdedit;
    Button startbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        userIdedit = findViewById(R.id.user_id_edittxt);
        startbtn = findViewById(R.id.startbtn);

        startbtn.setOnClickListener((v) ->{
            String userID = userIdedit.getText().toString().trim();
            if(userID.isEmpty()){
                return;
            }
            startService(userID);
            Intent intent = new Intent(MainActivity.this , CallActivity.class);
            intent.putExtra("userID", userID);
            startActivity(intent);
        });
    }

    void startService(String userID){
        Application application = getApplication() ; // Android's application context
        long appID = 1405458438;   // yourAppID
        String appSign ="8286bc5e5dc113ba9a097a0042344d20bdb9196799c74f11ed17db7285da6bdf";  // yourAppSign
        String userName = userID;   // yourUserName

        ZegoUIKitPrebuiltCallInvitationConfig callInvitationConfig = new ZegoUIKitPrebuiltCallInvitationConfig();

        ZegoUIKitPrebuiltCallInvitationService.init(getApplication(), appID, appSign, userID, userName,callInvitationConfig);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ZegoUIKitPrebuiltCallInvitationService.unInit();
    }
}