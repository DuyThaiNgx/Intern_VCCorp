package com.example.ExerciseTwo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CrawlData {
    public static void main(String[] args) {
        try {
            String url = "http://dantri.com.vn";
            Document document = Jsoup.connect(url).get();

            // Lấy nội dung sau khi đã loại bỏ các thẻ
            String plainText = document.text();

            // Tạo tên file với định dạng yyyy_MM_dd_HH:mm:ss.txt
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
//            String fileName = "src/main/java/com/example/ExerciseTwo/"+ dateFormat.format(new Date()) + ".txt";
            File file = new File("src/main/java/com/example/ExerciseTwo/"+ dateFormat.format(new Date()) + ".txt");

            // Ghi vao file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(plainText);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Crawling and writing to file completed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
