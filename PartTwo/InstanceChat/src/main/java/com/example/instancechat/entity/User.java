package com.example.instancechat.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String username;
    private String password; // Lưu mật khẩu đã được hash
    private List<String> friends;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.friends = new ArrayList<>();
    }
}

