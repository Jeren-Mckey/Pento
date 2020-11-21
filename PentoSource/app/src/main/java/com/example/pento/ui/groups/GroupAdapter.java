package com.example.pento.ui.groups;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pento.R;
import com.example.pento.data.GroupDataSource;
import com.example.pento.data.GroupRepository;
import com.example.pento.data.Result;
import com.example.pento.data.model.Group;
import com.example.pento.data.model.GroupList;
import com.example.pento.ui.chat.ChatActivity;

import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.CustomViewHolder> {


    private GroupDataSource groupDataSource = new GroupDataSource();
    private GroupRepository groupRepository = new GroupRepository(groupDataSource);
    Context context;
    GroupList list;

    class CustomViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageButton pfp;

        public CustomViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textMessage);
            pfp = itemView.findViewById(R.id.image);
        }
    }

    public GroupAdapter(Context context) {
        this.context = context;
        list=new GroupList();
    }

    public void update(String input){
        Result<List<Group>> qList = groupRepository.getGroupsByQuery(input);
        if (qList instanceof Result.Success) {
            list = new GroupList(((Result.Success<List<Group>>) qList).getData());
        }
        notifyDataSetChanged();
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent,String input) {
        if (i == EditorInfo.IME_ACTION_SEND) {
           Result<List<Group>> qList = groupRepository.getGroupsByQuery(input);
            if (qList instanceof Result.Success) {
                list = new GroupList(((Result.Success<List<Group>>) qList).getData());
            }
            notifyDataSetChanged();
        }
        return false;
    }


    @Override
    public int getItemViewType(int position) {
        return R.layout.group_button;
    }

    @Override
    public int getItemCount() {
        return  list.size();
    }

    @Override
    public GroupAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GroupAdapter.CustomViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(viewType, parent, false));
    }

    @Override
    public void onBindViewHolder(GroupAdapter.CustomViewHolder holder, final int position) {
        holder.textView.setText(list.get(position).getName());
        holder.pfp.setImageResource(R.drawable.cmcircle);
        holder.pfp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ChatActivity.class);
                Bundle b = new Bundle();
                b.putString("GroupName", list.get(position).getName()); //Your id
                i.putExtras(b); //Put your id to your next Intent
                v.getContext().startActivity(i);
            }
        });
    }
}