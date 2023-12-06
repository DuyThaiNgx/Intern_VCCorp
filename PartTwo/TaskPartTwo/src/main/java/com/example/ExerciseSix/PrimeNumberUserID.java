package com.example.ExerciseSix;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static com.example.ExerciseThree.PrimeNumber.getPrimeNumbers;
import static spark.Spark.*;


public class PrimeNumberUserID {
    // Đếm số request vào webservice
    private static LoadingCache<String, Integer> requestCountCache = CacheBuilder.newBuilder()
            .expireAfterWrite(1, TimeUnit.MINUTES) // Expiration time for the request count cache
            .build(new CacheLoader<String, Integer>() {
                @Override
                public Integer load(String userId) {
                    return 0; // Default value for a new user
                }
            });

    public static void main(String[] args) {

        LoadingCache<String, List<Integer>> guavaCache = CacheBuilder.newBuilder()
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .expireAfterAccess(20, TimeUnit.SECONDS)
                .build(new CacheLoader<String, List<Integer>>() {
                    @Override
                    public List<Integer> load(String cacheKey) throws Exception {
                        String[] keyParts = cacheKey.split(":");
                        //Param đều là số n
                        int n = Integer.parseInt(keyParts[0]);
                        //Param thứ 2 là userID
                        String userId = keyParts[1];

                        // Kiểm tra số lần request của user
                        int requestCount = requestCountCache.get(userId);
                        if (requestCount >= 10) {
                            throw new RuntimeException("Quota exceeded. Max 10 requests per minute.");
                        }
                        // Tăng số lần request của user
                        requestCountCache.put(userId, requestCount + 1);
                        // Define how to load the value when not in the cache
                        return getPrimeNumbers(n);
                    }
                });

        port(8787);
        get("/prime", (request, response) -> handleRequest(request, response, guavaCache));
    }
// n là thời gian sống của một phần tử trong cache (milliseconds)
// m là thời gian sống của cache (milliseconds)
    public static String handleRequest(Request request, Response response, LoadingCache<String, List<Integer>> guavaCache) throws ExecutionException {
        int n = Integer.parseInt(request.queryParams("n"));
        String userID = request.queryParams("userid");
        //Đếm số request trong 5s
        int numberRequestIn5s = requestCountCache.get(userID+":5s");
        if(numberRequestIn5s > 2){
            halt(429, "Maximum request in 5s is just 2");
        }
        // cập nhật số lần request của mỗi user trong khoảng thời gian 5 giây.
        requestCountCache.put(userID+":5s", numberRequestIn5s +1);
        String cacheKey = n + ":" + userID;
        try {
            // Kiểm tra số lần request của user trong 1 phút

            int requestCount1m = requestCountCache.get(userID + ":1m");
            if (requestCount1m >= 10) {
                halt(429, "Too Many Requests. Max 10 requests per minute.");
            }
            // Tăng số lần request của user trong 1 phút
            requestCountCache.put(userID + ":1m", requestCount1m + 1);
            // Kiểm tra cache để lấy hoặc load giá trị
            List<Integer> primes = guavaCache.get(cacheKey);

            return new Gson().toJson(primes);
        } catch (Exception e) {
            response.status(429);
            return e.getMessage();
        }

    }
}
