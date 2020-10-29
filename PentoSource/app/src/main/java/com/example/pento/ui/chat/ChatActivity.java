package com.example.pento.ui.chat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pento.R;
import com.example.pento.SettingsActivity;
import com.example.pento.ui.chat.MessageAdapter;
import com.example.pento.ui.chat.ResponseMessage;

import java.util.ArrayList;
import java.util.List;
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
        Timer timer = new Timer();
        class checkTask extends TimerTask {

            @Override
            public void run() {
                ResponseMessage responseMessage2 = new ResponseMessage("are you there?", "Autobot",false);
                messageAdapter.list.add(responseMessage2);
                runOnUiThread (new Thread(new Runnable() {
                    public void run() {
                        messageAdapter.notifyDataSetChanged();
                        if (!isLastVisible())
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
        timer.scheduleAtFixedRate(new checkTask(),100, 2000);

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
}
