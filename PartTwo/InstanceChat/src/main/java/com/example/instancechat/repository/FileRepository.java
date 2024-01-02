package com.example.instancechat.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Repository
public class FileRepository {
    private String filePath = "src/main/resources/templates/MessageQueue.json";
    private Gson gson = new GsonBuilder().create();
    public Map<String, Set<String>> getAllFilePermission() {
        try {
            String json = Files.readString(Path.of(filePath));
            TypeToken<Map<String, Set<String>>> typeToken = new TypeToken<>() {};
            if(json.equals("")){
                return new HashMap<>();
            }
            return gson.fromJson(json, typeToken.getType());
        } catch (IOException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }
    public void save(String sender, String receiver, String fileName) throws IOException {
        // Lấy toàn bộ thông tin quyền truy cập từ tệp JSON cho vào MAP
        Map<String,Set<String>> fileMap = getAllFilePermission();
        //Lấy hoặc tạo mới một Set quyền truy cập cho tệp tin gốc
        Set<String> filePermission = fileMap.getOrDefault(fileName,new HashSet<>());
        //Thêm người gửi và người nhận vào danh sách quyền truy cập
        filePermission.add(sender);
        filePermission.add(receiver);
        try{
            // Chuyển đổi map thành chuỗi JSON
            String json = gson.toJson(Path.of(filePath));
            // Lưu chuỗi JSON vào tệp tin
            Files.writeString(Path.of(filePath), json);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    /*
    Kiểm tra user có quyền truy cập file hay không
     */
    public boolean checkPermission(String username, String fileName){
        //Lấy toàn bộ thông tin quyền truy cập từ tệp JSON.
        Map<String, Set<String>> filePermission= getAllFilePermission();
        //Kiem tra trong Map nếu có username thì là được truy cập
        if(filePermission.containsKey(username)){
            Set<String> userSet = filePermission.get(fileName);
            return userSet.contains(username);
        }
        return false;
    }


}
