package com.example.ExerciseFour;

import com.google.gson.Gson;

import java.util.List;

import static com.example.ExerciseThree.PrimeNumber.getPrimeNumbers;
import static spark.Spark.get;
import static spark.Spark.port;

public class PrimeNumberServiceWithCache {
    //Thời gian sống cho mỗi phần tử trong cache (n) là 5 giây và thời gian sống của cache (m) là 20 giây
    private static final CacheTTL<Integer, List<Integer>> cache = new CacheTTL<>(5, 20);

    public static void main(String[] args) {
        port(8989);
        get("/prime", (request, response) -> {
            int n = Integer.parseInt(request.queryParams("number"));
            List<Integer> primes;
            // Kiểm tra xem n đã tồn tại rong cache chưa
            primes = cache.get(n);
            if (primes == null) {
                //Sử dụng hàm getPrimeNumbers ở bài 3
                primes = getPrimeNumbers(n);
                // Lưu kết quả vào cache
                cache.put(n, primes);
            }

            return new Gson().toJson(primes);
        });
    }
}