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
import android.widget.Toast;

import com.example.pento.ProfileActivity;
import com.example.pento.R;
import com.example.pento.data.model.ResponseMessage;

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
        TextView group_name = findViewById(R.id.GroupName);
        group_name.setText(value);
        Timer timer = new Timer();
        class checkTask extends TimerTask {

            @Override
            public void run() {
                ResponseMessage responseMessage2 = new ResponseMessage("Scheduled update", "Automatic", false,"");
                ResponseMessage responseMessage3 = new ResponseMessage("Unsynced from user sending a message", "Automatic", false,"");
                messageAdapter.list.add(responseMessage2);
                messageAdapter.list.add(responseMessage3);
                runOnUiThread (new Thread(new Runnable() {
                    public void run() {
                        messageAdapter.notifyDataSetChanged();
                        if (!isLastVisible())
                            recyclerView.smoothScrollToPosition(messageAdapter.getItemCount());
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
                if (i == EditorInfo.IME_ACTION_SEND) {
                    value = messageAdapter.onEditorAction(textView,i,keyEvent,userInput.getText().toString());
                }
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
