package com.example.pento.ui.chat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;

public class ResponseMessageList {
//    I think a REsponse MEssage list should be a model that updates from the database constantly
    List<ResponseMessage> responseMessageList;

    public ResponseMessageList(List<ResponseMessage> responseMessageList) {
        this.responseMessageList = responseMessageList;
    }

    public ResponseMessageList(){
        responseMessageList = new ArrayList<>();
    }

    public void add(ResponseMessage responseMessage){
        responseMessageList.add(responseMessage);
    }

        public ResponseMessage get(int position) {
            return responseMessageList.get(position);
        }
        public int size(){
            return responseMessageList.size();
        }
    }
