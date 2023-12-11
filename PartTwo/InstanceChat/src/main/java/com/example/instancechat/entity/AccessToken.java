package com.example.instancechat.entity;

import lombok.Getter;
import lombok.Setter;



@Setter
@Getter
public class AccessToken {

    private String token;
    private String username;
    private long createdTime;
    private long expireTime;

    public AccessToken(String token, String username, long createdTime, long expireTime) {
        this.token = token;
        this.username = username;
        this.createdTime = createdTime;
        this.expireTime = expireTime;
    }
}
