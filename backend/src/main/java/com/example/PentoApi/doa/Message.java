package com.example.PentoApi.doa;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Message {

    private String content;
    private String member;
    private String timestamp;
    private String group;

    public Message() {}
    public Message(String content, String member, String timestamp, String group) {
        this.content = content;
        this.member = member;
        this.timestamp = timestamp;
        this.group = group;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public String getMember() {
        return this.member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getTimestamp() { return timestamp; }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setGroup(String group) { this.group = group; }

    public String getGroup() { return this.group; }
}
