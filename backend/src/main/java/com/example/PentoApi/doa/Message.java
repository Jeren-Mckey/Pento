package com.example.PentoApi.doa;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Message {
    private String content;
    private String member_id;
    private long message_id;

    public Message() {}
    public Message(String content, String member) {
        this.content = content;
        this.member_id = member;
        this.message_id = 0;
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
}
