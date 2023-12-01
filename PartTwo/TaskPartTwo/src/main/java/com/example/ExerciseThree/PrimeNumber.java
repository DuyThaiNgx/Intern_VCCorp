package com.example.ExerciseThree;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.get;
import static spark.Spark.port;

public class PrimeNumber {
    public static void main(String[] args) {
        port(6868);
        //http://localhost:6868/prime?number=999 để hiện các ố nguyên tố từ 1 đến 999
        get("/prime", (request, response) -> {
            int n = Integer.parseInt(request.queryParams("number"));
            List<Integer> primesList = getPrimeNumbers(n);
            return new Gson().toJson(primesList);
        });
    }

    public static List<Integer> getPrimeNumbers(int n) {
        List <Integer> primesList = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (isPrime(i)) {
                primesList.add(i);
            }
        }
        return primesList;
    }

    private static boolean isPrime(int num) {
        if (num < 2) {
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
