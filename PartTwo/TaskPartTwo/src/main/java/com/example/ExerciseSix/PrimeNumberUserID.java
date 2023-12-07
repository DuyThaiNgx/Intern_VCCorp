package com.example.ExerciseSix;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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
        port(8080);
        get("/prime", (req,res)->{
            int n = Integer.parseInt(req.queryParams("n2"));
            String userID = req.queryParams("userid");
            if(handleRequest(userID)){
                List<Integer> primesList = getPrimeNumbers(n);
                res.type("application/json");
                return new Gson().toJson(primesList);
            }
            else {
                res.status(429);
                res.type("application/json");
                System.out.println("Vượt quá số người quy định");
                return "Da vuot qua so luot request quy dinh";
            }
        });


    }

    // n là thời gian sống của một phần tử trong cache (milliseconds)
// m là thời gian sống của cache (milliseconds)
//    public static String handleRequest(Request request, Response response, LoadingCache<String, List<Integer>> guavaCache) throws ExecutionException {
//        int n = Integer.parseInt(request.queryParams("n"));
//        String userID = request.queryParams("userid");
//        //Đếm số request trong 5s
//        int numberRequestIn5s = requestCountCache.get(userID+":5s");
//        if(numberRequestIn5s > 2){
//            halt(429, "Maximum request in 5s is just 2");
//        }
//        // cập nhật số lần request của mỗi user trong khoảng thời gian 5 giây.
//        requestCountCache.put(userID+":5s", numberRequestIn5s +1);
//        String cacheKey = n + ":" + userID;
//        try {
//            // Kiểm tra số lần request của user trong 1 phút
//
//            int requestCount1m = requestCountCache.get(userID + ":1m");
//            if (requestCount1m >= 10) {
//                halt(429, "Too Many Requests. Max 10 requests per minute.");
//            }
//            // Tăng số lần request của user trong 1 phút
//            requestCountCache.put(userID + ":1m", requestCount1m + 1);
//            // Kiểm tra cache để lấy hoặc load giá trị
//            List<Integer> primes = guavaCache.get(cacheKey);
//
//            return new Gson().toJson(primes);
//        } catch (Exception e) {
//            response.status(429);
//            return e.getMessage();
//        }
    static final Map<String, List<Long>> userRequests = new ConcurrentHashMap<>();
    public static boolean handleRequest(String userId) {
        long currentTime = System.currentTimeMillis();
        long fiveSecondsAgo = currentTime - 5000;
        long oneMinuteAgo = currentTime - 60000;
        userRequests.putIfAbsent(userId, new ArrayList<>());
        List<Long> timeUser = userRequests.get(userId);
        timeUser.add(currentTime);
        // Đếm số lượng request trong 5 giây
        // Lọc các thời điểm trong khoảng 5 fgiaay trở lại đây
        long countFiveSeconds = timeUser.stream()
                .filter(time -> time >= fiveSecondsAgo)
                .count();

        // Đếm số lượng request trong 1 phút
        long countOneMinute = timeUser.stream()
                .filter(time -> time >= oneMinuteAgo)
                .count();

        return countFiveSeconds <= 2 && countOneMinute <= 10;

    }
}
