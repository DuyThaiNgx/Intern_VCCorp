package com.example.instancechat.repository;

import com.example.instancechat.entity.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Repository;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
@Repository
public class UserRepository {
    private final String filePath;
    private final Gson gson;


    public UserRepository() {
        this.filePath = "src/main/resources/templates/user.json";
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    //Lấy toàn bộ thông tin của User
    public List<User> getAllUsers() throws IOException {
        try {
            String json = Files.readString(Path.of(filePath));
            List<User> list = gson.fromJson(json, new TypeToken<List<User>>() {
            }.getType());
            if (list != null) {
                return list;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    // Thêm user vào file json
    public void addUser(User user) {
        try {
            List<User> users = getAllUsers();
            users.add(user);

            String json = gson.toJson(users);
            try (FileWriter fileWriter = new FileWriter(filePath)) {
                fileWriter.write(json);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Optional<User> findByUsername(String username) {
        try {
            List<User> users = getAllUsers();
            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    return Optional.of(user);
                }
            }
            return Optional.empty();
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
    public void saveUser(User user) {
        try {
            List<User> users = getAllUsers();
            // Tìm kiếm nếu có trùng username thì loại bỏ
            for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
                User existingUser = iterator.next();
                if (existingUser.getUsername().equals(user.getUsername())) {
                    iterator.remove();
                }
            }
            users.add(user);
            String json = gson.toJson(users);
            try (FileWriter fileWriter = new FileWriter(filePath)) {
                fileWriter.write(json);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<String> getFriends(String username){
        Optional<User> userOptional = findByUsername(username);

        if(userOptional.isPresent()){
            User user = userOptional.get();
            return user.getFriends();
        }

        return new ArrayList<>();
    }

}
