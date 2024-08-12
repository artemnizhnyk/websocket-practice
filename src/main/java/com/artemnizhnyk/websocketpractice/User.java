package com.artemnizhnyk.websocketpractice;

import jakarta.enterprise.inject.Model;

@Model
public class User {

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
