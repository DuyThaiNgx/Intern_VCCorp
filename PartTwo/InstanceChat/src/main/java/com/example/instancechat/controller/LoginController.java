package com.example.instancechat.controller;

import com.example.instancechat.entity.AccessToken;
import com.example.instancechat.entity.User;
import com.example.instancechat.request.AccountRequest;
import com.example.instancechat.service.AccessTokenService;
import com.example.instancechat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.Optional;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private AccessTokenService accessTokenService;


    //    @PostMapping("/login")
//    public ResponseEntity<?> login(@ModelAttribute AccountRequest loginRequest) {
//        return userService.getUser(loginRequest)
//                .map(user -> {
//                    AccessToken accessToken = accessTokenService.generateAccessToken(user.getUsername());
//                    return ResponseEntity.ok(accessToken);
//                })
//                .orElse((ResponseEntity<AccessToken>) ResponseEntity.status(429));
//    }
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody AccountRequest loginRequest) {
        Optional<User> userOptional = userService.getUser(loginRequest);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            AccessToken accessToken = accessTokenService.generateAccessToken(user.getUsername());
            return ResponseEntity.ok(accessToken);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Tài khoản không hợp lệ");
    }

}
