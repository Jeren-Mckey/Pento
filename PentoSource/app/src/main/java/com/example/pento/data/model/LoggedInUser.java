package com.example.pento.data.model;

import java.io.Serializable;
import java.util.List;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser implements Serializable {
    private long id;
    private String username;
    private String password;
    private String region;
    private List<String> groups;

    public LoggedInUser() {
        this.username = "";
        this.password = "";
        this.region = "";
        this.groups = null;
    }
    public LoggedInUser(String username, String password, String region, List<String> groups) {
        this.username = username;
        this.password = password;
        this.region = region;
        this.groups = groups;
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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User {" +
                "username='" + username + '\'' +
                ", password=" + password +
                '}';
    }
}