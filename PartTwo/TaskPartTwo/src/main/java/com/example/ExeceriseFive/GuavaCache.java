package com.example.ExeceriseFive;

import com.example.ExerciseFour.CacheTTL;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.example.ExerciseThree.PrimeNumber.getPrimeNumbers;
import static spark.Spark.get;
import static spark.Spark.port;

public class GuavaCache {
        /* Chinh sua tren server may
    client */
    private static long ttl; // Thời gian sống cho từng phần tử (milliseconds)

    public static void main(String[] args) {

        LoadingCache<Integer, List<Integer>> guavaCache = CacheBuilder.newBuilder()
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .expireAfterAccess(20, TimeUnit.SECONDS)
                .build(new CacheLoader<Integer, List<Integer>>() {
                    @Override
                    public List<Integer> load(Integer n) throws Exception {
                        // Define how to load the value when not in the cache
                        return getPrimeNumbers(n);
                    }
                });
//        System.out.println(System.currentTimeMillis());
        port(8999);
        get("/prime", (request, response) -> {
            int n = Integer.parseInt(request.queryParams("numbers"));
            List<Integer> primes;
            // Kiểm tra xem n đã tồn tại rong cache chưa
            primes = guavaCache.get(n);
            if (primes == null) {
                //Sử dụng hàm getPrimeNumbers ở bài 3
                primes = getPrimeNumbers(n);
                // Lưu kết quả vào cache
                guavaCache.put(n, primes);
            }

            return new Gson().toJson(primes);
        });
    }
    // n là thời gian sống của một phần tử trong cache (milliseconds)
    // m là thời gian sống của cache (milliseconds)


}
