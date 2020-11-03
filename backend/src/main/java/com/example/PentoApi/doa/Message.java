package com.example.PentoApi.doa;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Message {
    private String content;
    private String member_id;
    private String timestamp;

    public Message() {}
    public Message(String content, String member) {
        this.content = content;
        this.member_id = member;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getMember() {
        return member_id;
    }

    public String getTimestamp() { return timestamp; }
}
