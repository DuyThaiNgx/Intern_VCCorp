package org.example.RestFulExample;

import static spark.Spark.*;

public class HelloWorldService {
    public static void main(String[] args) {
        port(8989);

        get("/hello", (req, res)->"Hello, world in port 8989");

        get("/hello/:name", (req,res)->{
            return "Hello, "+ req.params(":name");
        });
    }
}