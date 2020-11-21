package com.example.pento.ui.groups;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pento.R;
import com.example.pento.ui.chat.ChatActivity;
import com.example.pento.ui.login.LoginActivity;

import java.util.Timer;
import java.util.TimerTask;


public class ProfileActivity extends Activity {

    EditText userInput;
    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    GroupAdapter groupAdapter;
    GroupAdapter groupAdapter2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        userInput = findViewById(R.id.searchbar);
        recyclerView = findViewById(R.id.groups);
        groupAdapter = new GroupAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(groupAdapter);

        recyclerView2 = findViewById(R.id.your_groups);
        groupAdapter2 = new GroupAdapter(this);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        recyclerView2.setAdapter(groupAdapter2);
        groupAdapter2.update("admin");
        groupAdapter2.notifyDataSetChanged();

        userInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean value = false;
                if (i == EditorInfo.IME_ACTION_SEND) {
                    value = groupAdapter.onEditorAction(textView, i, keyEvent, userInput.getText().toString());
                }
                userInput.getText().clear();
                if (!isLastVisible())
                    recyclerView.smoothScrollToPosition(groupAdapter.getItemCount() - 1);
                return value;
            }
        });


    }

    public void onClickSwitchIntent(View v){

        if(v.getId() == R.id.profile_back){
            Intent i = new Intent(ProfileActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        }

        if(v.getId() == R.id.profileEdit){
        }


    }
    boolean isLastVisible() {
        LinearLayoutManager layoutManager = ((LinearLayoutManager) recyclerView.getLayoutManager());
        LinearLayoutManager layoutManager2 = ((LinearLayoutManager) recyclerView2.getLayoutManager());
        int pos = layoutManager.findLastCompletelyVisibleItemPosition();
        int numItems = recyclerView.getAdapter().getItemCount();
        return (pos >= numItems);
    }

}
