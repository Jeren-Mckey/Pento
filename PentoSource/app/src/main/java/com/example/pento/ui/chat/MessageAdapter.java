package com.example.pento.ui.chat;

import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.example.pento.R;

import com.example.pento.data.MessageDataSource;
import com.example.pento.data.MessageRepository;
import com.example.pento.data.Result;
import com.example.pento.data.model.ResponseMessage;
import com.example.pento.data.model.ResponseMessageList;


public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.CustomViewHolder> {


    Context context;
    ResponseMessageList list;
    private MessageDataSource messageDataSource = new MessageDataSource();
    private MessageRepository messageRepository = new MessageRepository(messageDataSource);

    class CustomViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        TextView username;
        ImageView pfp;

        public CustomViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textMessage);
            pfp = itemView.findViewById(R.id.image);
            username = itemView.findViewById(R.id.user);
        }
    }

    public MessageAdapter(Context context) {
        this.context = context;
        list = new ResponseMessageList();
    }

    public void update(String value) {
        Result<ResponseMessageList> mlist = messageRepository.getMessages(value);
        if (mlist instanceof Result.Success) {
            list = ((Result.Success<ResponseMessageList>) mlist).getData();
        }
    }


    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent,String input,String groupname) {
        if (i == EditorInfo.IME_ACTION_SEND) {

            Result<String> result = messageRepository.sendMessage(textView.getText().toString(), "xxMasterCJ21xx", groupname);
//            Result<String> result = messageRepository.sendMessage(textView.getText().toString(), user, groupname);
            update(groupname);
            notifyDataSetChanged();
        }
        return false;
    }


    @Override
    public int getItemViewType(int position) {

//        TODO
        // replace with
        // if(list.get(position).getMember().equals(loggedin_user.name)){
        // or something
        if(list.get(position).getisMe()){

            return R.layout.me_bubble;
        }
        return R.layout.other_bubble;
    }



    @Override
    public int getItemCount() {
        return  list.size();
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(viewType, parent, false));
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.textView.setText(list.get(position).getContent());
        holder.username.setText(list.get(position).getMember());
        if(list.get(position).getisMe("")) {
            holder.pfp.setImageResource(R.drawable.cmcircle);
        }
        else{
            holder.pfp.setImageResource(R.drawable.disgusted);
        }
    }
}
