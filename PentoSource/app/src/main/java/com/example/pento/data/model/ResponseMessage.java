package com.example.pento.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ResponseMessage {

    private String content;
    private String member;
    private String group;
    private String timestamp;

    public ResponseMessage() {}

    public ResponseMessage(String content, String member, String group) {
        this.content = content;
        this.member = member;
        this.group = group;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.timestamp = timestamp.toString();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean getisMe() {
        return false;
    }

    public boolean getisMe(String viewer) {
        return this.member.equals(viewer);
    }


    public void setMember(String member) {
        this.member = member;
    }
    public String getMember(){return this.member;}

    public String getTimestamp() {return this.timestamp;}

    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public String getGroup() {return this.group;}

    public void setGroup(String group) { this.group = group; }
}
