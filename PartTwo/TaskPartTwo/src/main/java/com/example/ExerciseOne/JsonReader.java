package com.example.ExerciseOne;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonReader {
    public static void main(String[] args) {
        try {
            String apiUrl = "http://news.admicro.vn:10002/api/realtime?domain=kenh14.vn"; // Thay thế bằng URL thực tế của API bạn muốn truy cập

            // Tạo đối tượng URL từ địa chỉ API
            URL url = new URL(apiUrl);

            // Mở kết nối HTTP
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Đặt phương thức request là GET
            connection.setRequestMethod("GET");

            // Lấy InputStream từ kết nối để đọc dữ liệu
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;

                // Đọc từng dòng dữ liệu từ InputStream và ghi vào StringBuilder
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                JSONObject jsonObject = new JSONObject(response.toString());
                // In ra dữ liệu JSON nhận được từ API
                for (String key : jsonObject.keySet()) {
                    Object value = jsonObject.get(key);
                    System.out.println("Key: " + key + ", Value: " + value);
                }
//                System.out.println("API Response:\n" + response.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}