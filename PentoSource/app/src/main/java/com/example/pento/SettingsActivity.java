package com.example.pento;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.Toast;

public class SettingsActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        final Button notficationsAndSounds = findViewById(R.id.notificationsAndSounds);

    }

    public void notificationsAndSounds(View v){
        if(v.getId() == R.id.notificationsAndSounds){
            Toast.makeText(SettingsActivity.this, "Notifications and Settings Pressed", Toast.LENGTH_SHORT).show();
        }
    }


}
