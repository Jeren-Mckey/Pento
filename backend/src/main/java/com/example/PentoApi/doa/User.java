package com.example.PentoApi.doa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class User {
    private String username;
    private String password;
    private String region;
    private List<String> groups;

    public User() {}
    public User(String name, String password, String region, List<String> groups) {
        this.username = name;
        this.password = password;
        this.region = region;
        this.groups = groups;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }
}
