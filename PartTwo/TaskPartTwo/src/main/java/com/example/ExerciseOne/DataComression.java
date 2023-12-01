//package com.example.ExerciseOne;
//
//import ch.qos.logback.classic.Level;
//import ch.qos.logback.classic.Logger;
//import org.json.JSONObject;
//import org.slf4j.LoggerFactory;
//
//import java.io.IOException;
//import java.nio.file.*;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
//public class DataCompression {
//
//    private static final String API_URL = "http://news.admicro.vn:10002/api/realtime?domain=kenh14.vn";
//    private static final double THRESHOLD_PERCENTAGE = 0.5;
//    private static final String LOG_FOLDER_PATH = "logs";
//    private static final long MAX_LOG_FOLDER_SIZE_MB = 20;
//    private static final long MAX_LOG_FILE_SIZE_MB = 1;
//    private static final long MAX_LOG_RETENTION_DAYS = 1;
//
//    private static long lastLoggedUserCount = 0;
//
//    public static void main(String[] args) {
//        // Setup logback
//        setupLogging();
//
//        // Setup scheduled executor service
//        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//        scheduler.scheduleAtFixedRate(DataCompression::getDataAndCompress, 0, 2, TimeUnit.SECONDS);
//    }
//
//    private static void setupLogging() {
//        System.setProperty("log.folder.path", LOG_FOLDER_PATH);
//        System.setProperty("max.log.folder.size.mb", String.valueOf(MAX_LOG_FOLDER_SIZE_MB));
//        System.setProperty("max.log.file.size.mb", String.valueOf(MAX_LOG_FILE_SIZE_MB));
//        System.setProperty("max.log.retention.days", String.valueOf(MAX_LOG_RETENTION_DAYS));
//    }
//
//    private static void getDataAndCompress() {
//        try {
//            JSONObject jsonData = fetchDataFromApi();
//
//            int currentUserCount = jsonData.getInt("user");
//
//            double percentageChange = calculatePercentageChange(lastLoggedUserCount, currentUserCount);
//
//            Logger logger = (Logger) LoggerFactory.getLogger(DataCompression.class);
//
//            if (percentageChange > THRESHOLD_PERCENTAGE) {
//                logUserCount(logger, currentUserCount, Level.INFO);
//            } else {
//                ScheduledExecutorService delayedLogger = Executors.newSingleThreadScheduledExecutor();
//                delayedLogger.schedule(() -> logUserCount(logger, currentUserCount, Level.DEBUG), 12, TimeUnit.SECONDS);
//                delayedLogger.shutdown();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void logUserCount(Logger logger, int userCount, Level level) {
//        logger.setLevel(level);
//        logger.log(level, "User count: {}", userCount);
//        lastLoggedUserCount = userCount;
//    }
//
//    private static JSONObject fetchDataFromApi() throws IOException {
//        // Fetch data from the API and parse the JSON response
//        // Use your preferred method to fetch data from the API (e.g., HttpURLConnection, HttpClient)
//        // Here, I'm using the org.json library to parse the JSON data
//        // You may need to add the org.json dependency to your project
//        // (https://mvnrepository.com/artifact/org.json/json)
//        String apiResponse = ""; // Fetch API data here
//        return new JSONObject(apiResponse);
//    }
//
//    private static double calculatePercentageChange(long oldValue, long newValue) {
//        if (oldValue == 0) {
//            return 100; // Initial value, consider as 100% change
//        }
//        return Math.abs(((double) (newValue - oldValue) / oldValue) * 100);
//    }
//}
