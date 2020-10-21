package com.example.pento.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String username;
    private String password;

    public LoggedInUser() {
        this.username = "";
        this.password = "";
    }
    public LoggedInUser(String username, String password) {
        this.username = username;
        this.password = password;
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