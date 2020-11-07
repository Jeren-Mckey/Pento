package com.example.pento.ui.chat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pento.R;
import com.example.pento.data.LoginRepository;
import com.example.pento.data.MessageDataSource;
import com.example.pento.data.MessageRepository;
import com.example.pento.data.Result;
import com.example.pento.data.model.LoggedInUser;
import com.example.pento.data.model.ResponseMessage;
import com.example.pento.data.model.ResponseMessageList;

import java.util.Timer;
import java.util.TimerTask;

public class ChatActivity extends AppCompatActivity {

    EditText userInput;
    RecyclerView recyclerView;
    MessageAdapter messageAdapter;
    private MessageDataSource messageDataSource = new MessageDataSource();
    private MessageRepository messageRepository = new MessageRepository(messageDataSource);


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
                //ResponseMessage responseMessage2 = new ResponseMessage("are you there?", "Autobot","");
                //messageAdapter.list.add(responseMessage2);
                Result<ResponseMessageList> list = messageRepository.getMessages("Group1");
                if (list instanceof Result.Success) {
                    messageAdapter.list = ((Result.Success<ResponseMessageList>) list).getData();
                }
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
                    Result<String> result = messageRepository.sendMessage(userInput.getText().toString(), "xxMasterCJ21xx", "Group1");
                    if (result instanceof Result.Success) {
                        value = messageAdapter.onEditorAction(textView, i, keyEvent, userInput.getText().toString());
                    }
                    else{
                        System.out.println("Error");
                    }
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
