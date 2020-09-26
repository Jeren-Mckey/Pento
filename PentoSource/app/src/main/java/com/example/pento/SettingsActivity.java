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
        final CheckBox chatHeads = findViewById(R.id.chatHeads);
        final Switch showLocation = findViewById(R.id.showLocation);

    }

    public void notificationsAndSounds(View v){
        if(v.getId() == R.id.notificationsAndSounds){
            Toast.makeText(SettingsActivity.this, "Notifications and Settings Pressed", Toast.LENGTH_SHORT).show();
        }
    }
    public void chatHeads(View v){
        if(v.getId() == R.id.chatHeads){
            boolean checked = ((CheckBox) v).isChecked();
            if(checked) {
                Toast.makeText(SettingsActivity.this, "Chat Heads toggled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void showLocation(View v){
        if(v.getId() == R.id.showLocation){
            Toast.makeText(SettingsActivity.this, "Show Location toggled", Toast.LENGTH_SHORT).show();
        }
    }

}
