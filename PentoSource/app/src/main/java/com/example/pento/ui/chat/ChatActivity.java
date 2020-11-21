package com.example.pento.ui.chat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pento.R;
import com.example.pento.ui.groups.ProfileActivity;

import java.util.Timer;
import java.util.TimerTask;

public class ChatActivity extends AppCompatActivity {

    EditText userInput;
    RecyclerView recyclerView;
    MessageAdapter messageAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        userInput = findViewById(R.id.userInput);
        recyclerView = findViewById(R.id.conversation);
        messageAdapter = new MessageAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(messageAdapter);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.getAdapter().notifyDataSetChanged();

        Bundle b = getIntent().getExtras();
        String value = ""; // or other values
        if(b != null)
            value = b.getString("GroupName");
        final TextView group_name = findViewById(R.id.GroupName);
        group_name.setText(value);
        Timer timer = new Timer();

        class checkTask extends TimerTask {

            @Override
            public void run() {
                Bundle b = getIntent().getExtras();
                String value = "";
                if (b != null) value = b.getString("GroupName");
                    messageAdapter.update(value);
                runOnUiThread (new Thread(new Runnable() {
                    public void run() {
                        messageAdapter.notifyDataSetChanged();
                        if (!isLastVisible() && messageAdapter.getItemCount() > 0)
                            recyclerView.smoothScrollToPosition(messageAdapter.getItemCount() - 1);
                            try {
                                Thread.sleep(300);
                            }
                            catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }));
            }
        };
        timer.scheduleAtFixedRate(new checkTask(),1000, 6000);

        userInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean value = false;
                Bundle b = getIntent().getExtras();
                String groupname = "";
                if (b != null) groupname = b.getString("GroupName");
                if (i == EditorInfo.IME_ACTION_SEND && userInput.getText().toString().length()>0) {
                    value = messageAdapter.onEditorAction(textView, i, keyEvent, userInput.getText().toString(),groupname);
                }
                userInput.getText().clear();
                if (!isLastVisible())
                    recyclerView.smoothScrollToPosition(messageAdapter.getItemCount() - 1);
                return value;
            }
        });
    }

    boolean isLastVisible() {
        LinearLayoutManager layoutManager = ((LinearLayoutManager) recyclerView.getLayoutManager());
        int pos = layoutManager.findLastCompletelyVisibleItemPosition();
        int numItems = recyclerView.getAdapter().getItemCount();
        return (pos >= numItems);
    }
    public void onClickSwitchIntent(View v) {
        if (v.getId() == R.id.chat_back) {
            Intent i = new Intent(ChatActivity.this, ProfileActivity.class);
            startActivity(i);
            finish();
        }
    }
}
