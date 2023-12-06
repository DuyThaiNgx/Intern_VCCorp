package org.example;

import com.google.common.cache.*;
import com.google.gson.Gson;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static spark.Spark.*;

public class Main {
    static Gson gson = new Gson();
//    static final CacheTTL<Integer, List<Integer>> primeCache = new CacheTTL<>(10000, 10000);

    static final LoadingCache<Integer, List<Integer>> guavaCache = CacheBuilder.newBuilder()
            .expireAfterWrite(20, TimeUnit.SECONDS) // Cache entry expires after m seconds of being written
            .expireAfterAccess(10, TimeUnit.SECONDS) // Cache entry expires after n seconds of not being accessed
            .removalListener((RemovalNotification<Integer, List<Integer>> notification) -> {
                if (notification.getCause() == RemovalCause.EXPIRED) {
                    System.out.println("Primes for n = " + notification.getKey() + " is expired");
                } else if (notification.getCause() == RemovalCause.EXPLICIT) {
                    System.out.println("Element removed from cache: " + notification.getKey());
                }
            })
            .build(new CacheLoader<Integer, List<Integer>>() {
                @Override
                public List<Integer> load(Integer n) throws Exception {
                    return findPrimes(n);
                }
            });

    static final Map<String, List<Long>> userRequests = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        get("/prime", (req, res) -> {
            int n = Integer.parseInt(req.queryParams("n"));
            String userId = req.queryParams("userid");
            LocalTime time = LocalTime.now();
            System.out.println(time.getHour() + ":" + time.getMinute() + ":" + time.getSecond());

            if(isAllowed(userId)){
                List<Integer> listPrime = getPrimes(n);
//                trackRequest(userId);
                res.type("application/json");
                return gson.toJson(listPrime);
            }

            res.status(429);
            System.out.println("Too many requests. Please wait a while before trying again.");
            return "Too many requests. Please wait a while before trying again.";

        });
    }

    /**
     * Kiểm tra xem yser có thể request được không
     * @param userId
     * @return
     */
    public static boolean isAllowed(String userId){
//        long currentTime = System.currentTimeMillis();
//        long twoRequestTime = 5000;
//        long tenRequestTime = 60000;
//        int maxRequestTwoTime = 2;
//        int maxRequestTenTime = 10;
//
//        userRequests.put(userId, currentTime);
//
//        long countFiveSecs = userRequests.values().stream().filter(time -> currentTime-time <= twoRequestTime).count();
//
//        long countOneMin = userRequests.values().stream().filter(time -> currentTime-time <= tenRequestTime).count();
//
//        if(countFiveSecs < maxRequestTwoTime && countOneMin < maxRequestTenTime){
//            return true;
//        }
//
//        return false;

        long currentTime = System.currentTimeMillis();
        long fiveSecondsAgo = currentTime - 5000; // 5 seconds
        long oneMinuteAgo = currentTime - 60000; // 1 minute
        int maxRequestsPerFiveSeconds = 2;
        int maxRequestsPerOneMinute = 10;

        // Cập nhật thời gian cuối cùng user request hoặc thêm mới user
        userRequests.putIfAbsent(userId, new ArrayList<>());
        List<Long> userRequestTimes = userRequests.get(userId);
        userRequestTimes.add(currentTime);

        // Đếm số lượng request trong 5 giây
        long countFiveSeconds = userRequestTimes.stream()
                .filter(time -> time >= fiveSecondsAgo)
                .count();

        // Đếm số lượng request trong 1 phút
        long countOneMinute = userRequestTimes.stream()
                .filter(time -> time >= oneMinuteAgo)
                .count();

        return countFiveSeconds <= maxRequestsPerFiveSeconds && countOneMinute <= maxRequestsPerOneMinute;
    }

    private static void trackRequest(String userId) {
//        userRequests.put(userId, System.currentTimeMillis());
        userRequests.putIfAbsent(userId, new ArrayList<>());
        List<Long> userRequestTimes = userRequests.get(userId);
        userRequestTimes.add(System.currentTimeMillis());
    }

    private static List<Integer> getPrimes(int n) {
//        List<Integer> cachedPrimes = primeCache.get(n);
//        if (cachedPrimes != null) {
//            System.out.println("Retrieved primes from cache for n = " + n);
//            return cachedPrimes;
//        }
//
//        List<Integer> primes = findPrimes(n);
//        primeCache.put(n, primes);
//        System.out.println("Computed primes for n = " + n + " and added to cache");
//        return primes;
        try {
            List<Integer> cachedPrimes = guavaCache.getIfPresent(n);
            if (cachedPrimes != null) {
                System.out.println("Retrieved primes from cache for n = " + n);
                return cachedPrimes;
            }

            return guavaCache.get(n, () -> {
                List<Integer> primes = findPrimes(n);
                guavaCache.put(n, primes); // Add primes to cache
                System.out.println("Computed primes for n = " + n + " and added to cache");
                return primes;
            });
        } catch (ExecutionException e) {
            // Exception handling if needed
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static List<Integer> findPrimes(int n) {
        List<Integer> primes = new ArrayList<>();

        for (int i = 2; i <= n; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }

        return primes;
    }

    private static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}

/*
    Hop nhat 2 commit
 */