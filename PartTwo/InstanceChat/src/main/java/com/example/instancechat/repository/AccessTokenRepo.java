package com.example.instancechat.repository;

import com.example.instancechat.entity.AccessToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//public class AccessTokenRepo {
//    public static final Gson gson = new Gson();
//
//    // Tìm AccessToken theo token
//    private static String filePath = "src/main/resources/storage/accesstoken.json";
//    public static List<AccessToken> getAllAccessTokens() throws IOException {
//        try {
//            String json = Files.readString(Path.of(filePath));
//            List<AccessToken> list = gson.fromJson(json, new TypeToken<List<AccessToken>>() {}.getType());
//            if(list != null){
//                return list;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return new ArrayList<>();
//    }
//    public static Optional<AccessToken> accessToken(String tokenString) throws IOException {
//        List<AccessToken> tokens = getAllAccessTokens();
//        return tokens.stream()
//                .filter(t -> t.getToken().equals(tokenString))
//                .findFirst();
//    }
//    public void deleteAccessToken(String tokenString) {
//        try {
//            List<AccessToken> accessTokens = readAccessTokensFromFile();
//
//            if (accessTokens != null) {
//                accessTokens.removeIf(token -> token.getToken().equals(tokenString));
//
//                    try {
//                    saveAccessTokensToFile(accessTokens);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    private void saveAccessTokensToFile(List<AccessToken> accessTokens) throws IOException {
//        try (FileWriter writer = new FileWriter("src/main/resources/storage/accesstoken.json")) {
//            gson.toJson(accessTokens, writer);
//        }
//    }
////    private void saveAccessTokenToFile(AccessToken accessToken) {
////        try {
////            List<AccessToken> accessTokensList= getAllAccessTokens();
////            accessTokensList.add(accessToken);
////            String json = gson.toJson(accessTokensList);
////
////            try (FileWriter writer = new FileWriter("src/main/resources/storage/accesstoken.json")) {
////                writer.write(json);
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////    }
//
//    private List<AccessToken> readAccessTokensFromFile() throws IOException {
//        File file = new File("src/main/resources/storage/accesstoken.json");
//        if (file.exists()) {
//            try (FileReader reader = new FileReader(file)) {
//                Type type = new TypeToken<List<AccessToken>>(){}.getType();
//                System.out.println();
//                return gson.fromJson(reader, type);
//
//            }
//        }
//        return null;
//    }
//}
//
// BAI LAM

@Repository
public class AccessTokenRepo {

    private final String filePath;
    private final Gson gson;


    public AccessTokenRepo() {
        this.filePath = "src/main/resources/templates/AccessToken.json";
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public List<AccessToken> getAllAccessTokens() throws IOException {
        try {
            String json = Files.readString(Path.of(filePath));
            List<AccessToken> list = gson.fromJson(json, new TypeToken<List<AccessToken>>() {}.getType());
            if(list != null){
                return list;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void save(AccessToken accessToken){
        try {
            List<AccessToken> tokens = getAllAccessTokens();
            tokens.add(accessToken);
            String json = gson.toJson(tokens);
            try (FileWriter fileWriter = new FileWriter(filePath)) {
                fileWriter.write(json);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Optional<AccessToken> findByToken(String token) {
        try {
            List<AccessToken> tokens = getAllAccessTokens();
            return tokens.stream()
                    .filter(t -> t.getToken().equals(token))
                    .findFirst();
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public void delete(AccessToken token) {
        try {
            List<AccessToken> tokens = getAllAccessTokens();

            tokens.removeIf(t -> t.getToken().equals(token.getToken()));

            String json = gson.toJson(tokens);
            try (FileWriter fileWriter = new FileWriter(filePath)) {
                fileWriter.write(json);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}