package com.example.instancechat.controller;

import com.example.instancechat.entity.AccessToken;
import com.example.instancechat.service.AccessTokenService;
import com.example.instancechat.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

public class FriendController {

    @Autowired
    private AccessTokenService accessTokenService;
    @Autowired
    private FriendService friendService;

    @GetMapping("/friends")
    public ResponseEntity<?> getFriends(@RequestHeader("AccessToken") String accessTokenHeader){
        AccessToken accessToken = accessTokenService.getAccessToken(accessTokenHeader);
        if(accessToken != null){
            return ResponseEntity.ok(friendService.getFriends(accessToken.getUsername()));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}