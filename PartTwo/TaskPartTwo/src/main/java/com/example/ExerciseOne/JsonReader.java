package com.example.ExerciseOne;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Time;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class JsonReader {
    public static int lastCountUser = 0;
    private static long lastTime = 0;
    private static Logger logger = LoggerFactory.getLogger(JsonReader.class);

    public static JSONObject readJsonFromUrl(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            return new JSONObject(response.toString());
        } finally {
            urlConnection.disconnect();
        }
    }

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        // Scheduling the task to fetch data every 2 seconds
        executorService.scheduleAtFixedRate(()->{
            long timestamp = System.currentTimeMillis();

            // Sử dụng java.time.Instant và java.time.LocalDateTime
            Instant instant = Instant.ofEpochMilli(timestamp);
            LocalDateTime dateTime = LocalDateTime.ofInstant(instant, java.time.ZoneId.systemDefault());

            // Định dạng ngày giờ chỉ hiển thị phút và giây
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm:ss");
            String formattedDateTime = dateTime.format(formatter);

            System.out.println("Minutes and Seconds: " + formattedDateTime);
            fetDataURL();
        }, 0, 2, TimeUnit.SECONDS);

//        checkDataUser();
//        fetDataURL();
    }
    public static JSONObject fetDataURL(){
        String url = "http://news.admicro.vn:10002/api/realtime?domain=kenh14.vn"; // Thay thế bằng URL thực tế của bạn
        try {
            JSONObject jsonData = readJsonFromUrl(url);
            if (jsonData != null) {
                int user = jsonData.getInt("user");
//                double perUser = jsonData.getDouble("per_user");
//                int userOldTime = jsonData.getInt("user_old_time");
//                JSONArray sourceArray = jsonData.getJSONArray("source");
//                for (int i = 0; i < sourceArray.length(); i++) {
//                    JSONObject sourceObject = sourceArray.getJSONObject(i);
//                    String name = sourceObject.getString("name");
//                    int number = sourceObject.getInt("number");
//
//                    System.out.println("Name: " + name + ", Number: " + number);
//                }
                long currentTime = System.currentTimeMillis();
                if(Math.abs((user - lastCountUser)) >= lastCountUser*0.002){
                    System.out.println("INFO: "+user);
                    lastCountUser=user;
                    lastTime = currentTime;
                    logger.info("INFO: +"+user);
                }
                long betweenTime = currentTime - lastTime;
                if(TimeUnit.MILLISECONDS.toSeconds(betweenTime)>= 12){
                    System.out.println("DEBUG: "+user);
                    lastTime=currentTime;
                    lastCountUser=user;
                    logger.debug("user: "+user);
                }
            } else {
                System.out.println("Failed to fetch data from the URL.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
//    public static void checkDataUser(){
//        long currentTime = System.currentTimeMillis();
//        long lastTime = 0;
//        JSONObject currentUser = fetDataURL();
//        int currentData = (int) currentUser.get("user");
//        System.out.println("check" +currentUser.getInt("user"));
//        if(Math.abs((currentData - lastCountUser)) >= lastCountUser*0.02){
//            System.out.println("INFO: "+currentData);
//            lastCountUser=currentData;
//            lastTime = currentTime;
//            logger.info("INFO: +"+currentUser);
//        }
//        long betweenTime = currentTime - lastTime;
//        if(TimeUnit.MILLISECONDS.toSeconds(betweenTime)> 12){
//            System.out.println("Debug: "+currentData);
//            lastCountUser=currentData;
//
//        }
//    }
//    public static void loggingCheck(){
//        LOGGER.info("This is an info message.");
//
//        // Ghi log với level DEBUG
//        LOGGER.debug("This is a debug message.");
//
//
//        try {
//            int result = 10 / 0;
//        } catch (Exception e) {
//            LOGGER.error("Error occurred:", e);
//        }
//    }
}
