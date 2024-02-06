package com.riya.talkfaceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton;
import com.zegocloud.uikit.service.defines.ZegoUIKitUser;

import java.util.Collections;

public class CallActivity extends AppCompatActivity {
    EditText useridedittxt;
    TextView heyuserTextView;
    ZegoSendCallInvitationButton voicecallbtn , videocallbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        getSupportActionBar().hide();

        useridedittxt = findViewById(R.id.user_id_edittxt);
        heyuserTextView = findViewById(R.id.heyusertxtview);
        voicecallbtn = findViewById(R.id.voicecallbtn);
        videocallbtn = findViewById(R.id.videocallbtn);

        String userId = getIntent().getStringExtra("userID");
        heyuserTextView.setText("Hey" + " " + userId);
        useridedittxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
             String  targetUserID = useridedittxt.getText().toString().trim();
             setVideocall(targetUserID);
             setVoicecall(targetUserID);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

   void setVoicecall(String targetUserID){
       voicecallbtn.setIsVideoCall(false);
      voicecallbtn.setResourceID("zego_uikit_call"); // Please fill in the resource ID name that has been configured in the ZEGOCLOUD's console here.
    voicecallbtn.setInvitees(Collections.singletonList(new ZegoUIKitUser(targetUserID,targetUserID)));


    }

    void setVideocall(String targetUserID){
        videocallbtn.setIsVideoCall(true);
       videocallbtn.setResourceID("zego_uikit_call"); // Please fill in the resource ID name that has been configured in the ZEGOCLOUD's console here.
        videocallbtn.setInvitees(Collections.singletonList(new ZegoUIKitUser(targetUserID,targetUserID)));
    }
}