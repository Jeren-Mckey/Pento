package com.example.pento.ui.chat;

/**
 * Created by deathcode on 26/01/18.
 */

public class ResponseMessage {

    String text;
    boolean isMe;
    String username;

    public ResponseMessage(String text, String username, boolean isMe) {
        this.text = text;
        this.isMe = isMe;
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isMe() {
        return isMe;
    }

    public void setMe(boolean me) {
        isMe = me;
    }

    public String getUsername(){return username;}
}
