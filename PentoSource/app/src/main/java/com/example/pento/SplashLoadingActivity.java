package com.example.pento;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.Toast;

import com.example.pento.ui.login.LoginActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashLoadingActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        final Button notficationsAndSounds = findViewById(R.id.notificationsAndSounds);

    }

    public void notificationsAndSounds(View v){
        if(v.getId() == R.id.notificationsAndSounds){
            Toast.makeText(SplashLoadingActivity.this, "Notifications and Settings Pressed", Toast.LENGTH_SHORT).show();
        }
    }

}
