package FileAss;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Buffered_Writer {
    public static void main(String[] args) {
        try (FileWriter fileWriter = new FileWriter("src/FileAss/FileBinary.bin");
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            String data = "This file was written by Java hehe Nov 6th, 2023.";
            bufferedWriter.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
