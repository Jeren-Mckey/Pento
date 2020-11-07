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
import com.example.pento.data.model.ResponseMessage;
import com.example.pento.data.model.ResponseMessageList;


/**
 * Created by deathcode on 26/01/18.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.CustomViewHolder> {

    Context context;
    ResponseMessageList list;
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

    public void update(){
        notifyDataSetChanged();
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent,String input) {
//        Todo
          // ChatRepository.send();
//        Send message to backend
        if (i == EditorInfo.IME_ACTION_SEND) {
            ResponseMessage responseMessage = new ResponseMessage(input, "xxMasterCJ21xx", "Group1");
            list.add(responseMessage);
            notifyDataSetChanged();
        }
        return false;
    }


    @Override
    public int getItemViewType(int position) {
        if(list.get(position).getisMe()){
            return R.layout.me_bubble;
        }
        return R.layout.bot_bubble;
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
//          TODO
//          Backend.USRNMAE
//          holder..setText(PFPIMAGE)
        if(list.get(position).getisMe()) {
//          TODO
//          Backend.getPFP
//          holder.img.setIMageResource(PFPIMAGE)
            holder.pfp.setImageResource(R.drawable.add);
        }
        else{
//          TODO
//          Backend.getPFP
//          holder.img.setIMageResource(PFPIMAGE)
            holder.pfp.setImageResource(R.drawable.disgusted);
        }
    }
}
