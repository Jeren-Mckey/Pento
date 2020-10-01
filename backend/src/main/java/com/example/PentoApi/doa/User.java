package com.example.PentoApi.doa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class User {
    private String username;
    private String password;

    public User() {}
    public User(String name, String password) {
        this.username = name;
        this.password = password;
    }
    public void setUsername(String name) {
        this.username = name;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }
}
