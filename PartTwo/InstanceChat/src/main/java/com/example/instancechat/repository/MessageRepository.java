package com.example.instancechat.repository;

import com.example.instancechat.entity.Message;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
@Repository
public class MessageRepository {
    private String filePath = "src/main/resources/storage/MessageQueue.json";
    private Gson gson = new GsonBuilder().create();
    //DDoc dữ liệu tin nhắn người dùng từ file json
    public Map<String, LinkedList<Message>> getAllMessageQueue() {
        try {
            String json = Files.readString(Path.of(filePath));
            //Nếu tập tin không tồn tại hoặc trống thì trả về 1 Hashmap trống
            if (json.isEmpty()) {
                return new HashMap<>();
            }
            TypeToken<Map<String, LinkedList<Message>>> typeToken = new TypeToken<>() {};
            //Chuyển 1 chuỗi Json thành 1 đối tượng
            return gson.fromJson(json, typeToken.getType());
        } catch (IOException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }
    /*
    Lưu tin nhắn của người dùng vào file json
     */
    public void saveMessage(String username, LinkedList<Message> messageQueue) {
        Map<String, LinkedList<Message>> messageQueueMap = getAllMessageQueue();
        messageQueueMap.put(username, messageQueue);
        try {
            String json = gson.toJson(messageQueueMap);
            Files.writeString(Path.of(filePath), json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
    Lấy tin nhắn của user thông qua username
     */
    public LinkedList<Message> getMessageQueue(String username) {
        Map<String, LinkedList<Message>> messageQueueMap = getAllMessageQueue();
        if (messageQueueMap.containsKey(username)) {
            return messageQueueMap.get(username);
        } else {
            return new LinkedList<>();
        }
    }
    /*
    xóa tin nhắn
     */
    public void removeMessage(String username){
        Map<String, LinkedList<Message>> messageQueueMap = getAllMessageQueue();
        messageQueueMap.remove(username);
        try {
            String json = gson.toJson(messageQueueMap);
            Files.writeString(Path.of(filePath), json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
