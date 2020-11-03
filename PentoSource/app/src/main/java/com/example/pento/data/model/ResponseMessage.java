package com.example.pento.data.model;

public class ResponseMessage {

    private String text;
    private boolean isMe;
    private String username;
    private String group_id;
    private String timestamp;

    public ResponseMessage(String text, String username, boolean isMe,String group_id) {
        this.text = text;
        this.username = username;
        this.isMe = isMe;
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

    public String getUsername(){return this.username;}

    public String getTimestamp() {return this.timestamp;}

    public String getGroup_id() {return this.group_id;}
}
