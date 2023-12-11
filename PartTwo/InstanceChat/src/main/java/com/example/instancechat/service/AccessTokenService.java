package com.example.instancechat.service;

import com.example.instancechat.entity.AccessToken;
import com.example.instancechat.entity.User;
import com.example.instancechat.repository.AccessTokenRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class AccessTokenService {
    @Autowired
    public AccessTokenRepo accessTokenRepo;
    private static final ObjectMapper objectMapper = new ObjectMapper();


    public AccessToken generateAccessToken(String user) {
        String token = generateTokenRandom();
        String processedToken = hashString(token);
        long createdTime = System.currentTimeMillis();
        long expireTime = createdTime + 3600000; // Thời gian sống 1 giờ (1 giờ = 3600000 milliseconds)

        AccessToken accessToken = new AccessToken(processedToken, user, createdTime, expireTime);
        accessTokenRepo.save(accessToken);
        return accessToken;
    }
    //
    public String generateTokenRandom(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
    private String hashString(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public AccessToken checkAccessToken(String tokenString){
        Optional<AccessToken> tokenOptional= accessTokenRepo.findByToken(tokenString);
        if(tokenOptional.isPresent()){
            AccessToken token = tokenOptional.get();
            long currentTime = System.currentTimeMillis();
            if(currentTime > token.getCreatedTime() && currentTime < token.getExpireTime()){
                return token;
            }
            else {
                accessTokenRepo.delete(token);
            }
        }
        return null;
    }


}
