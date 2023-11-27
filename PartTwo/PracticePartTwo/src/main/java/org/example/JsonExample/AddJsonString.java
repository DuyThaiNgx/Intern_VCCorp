package org.example.JsonExample;

import com.google.gson.JsonObject;

public class AddJsonString {
    public static void main(String[] args) {
        // Tạo đối tượng JsonObject để xây dựng chuỗi JSON
        JsonObject jsonObject = new JsonObject();

        // Thêm các thành phần vào JsonObject
        jsonObject.addProperty("name", "John");
        jsonObject.addProperty("age", 30);
        jsonObject.addProperty("city", "New York");

        // Chuyển đối tượng JsonObject thành chuỗi JSON
        String jsonString = jsonObject.toString();

        // In chuỗi JSON
        System.out.println(jsonString);
    }
}
