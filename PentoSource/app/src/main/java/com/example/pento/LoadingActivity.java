package com.example.pento;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.pento.ui.chat.ChatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class LoadingActivity extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);

        Timer timer = new Timer();

        class switchTask extends TimerTask {

            @Override
            public void run() {
                Intent i = new Intent(LoadingActivity.this, ProfileActivity.class);
                startActivity(i);
                finish();
            }
        };

        timer.schedule(new switchTask(),1000);

    }
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.settings);
//
//        final Button notficationsAndSounds = findViewById(R.id.notificationsAndSounds);
//
//    }
//
//    public void notificationsAndSounds(View v){
//        if(v.getId() == R.id.notificationsAndSounds){
//            Toast.makeText(SettingsActivity.this, "Notifications and Settings Pressed", Toast.LENGTH_SHORT).show();
//        }
//    }


}
