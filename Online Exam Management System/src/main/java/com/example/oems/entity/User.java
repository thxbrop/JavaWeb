package com.example.oems.entity;

public class User {
    public static final int ROLE_DEFAULT = 1;
    public static final int ROLE_ADMIN = 2;
    private int id;
    private String email;
    private String password;
    private String username;
    private int role;

    public User(String email, String password, String username, int role) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.role = role;
    }

    public User(int id, String email, String password, String username, int role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.role = role;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
