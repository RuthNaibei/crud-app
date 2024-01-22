package com.demosite.crudapp;

public class UserUpdateDTO {
    private static String username;
    private static String email;

    public static String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
