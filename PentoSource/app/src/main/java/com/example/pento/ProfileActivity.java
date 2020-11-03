package com.example.pento;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.pento.ui.chat.ChatActivity;
import com.example.pento.ui.login.LoginActivity;

public class ProfileActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
    }

    public void onClickSwitchIntent(View v){
        if(v.getId() == R.id.group1){
            Intent i = new Intent(ProfileActivity.this, ChatActivity.class);
            Bundle b = new Bundle();
            b.putString("GroupName", "Cabbage Patch Cats"); //Your id
            i.putExtras(b); //Put your id to your next Intent
            startActivity(i);
            finish();
        }

        if(v.getId() == R.id.group2){
            Intent i = new Intent(ProfileActivity.this, ChatActivity.class);
            Bundle b = new Bundle();
            b.putString("GroupName", "Regular Cats"); //Your id
            i.putExtras(b); //Put your id to your next Intent
            startActivity(i);
            finish();
        }

        if(v.getId() == R.id.group3){
            Intent i = new Intent(ProfileActivity.this, ChatActivity.class);
            Bundle b = new Bundle();
            b.putString("GroupName", "Regular Cabbages"); //Your id
            i.putExtras(b); //Put your id to your next Intent
            startActivity(i);
            finish();
        }

        if(v.getId() == R.id.profile_back){
            Intent i = new Intent(ProfileActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        }

        if(v.getId() == R.id.profileEdit){
            Toast.makeText(ProfileActivity.this, "Not implemented yet", Toast.LENGTH_SHORT).show();
        }

        if(v.getId() == R.id.group_search){
            Toast.makeText(ProfileActivity.this, "Not implemented yet", Toast.LENGTH_SHORT).show();
        }

    }

}
