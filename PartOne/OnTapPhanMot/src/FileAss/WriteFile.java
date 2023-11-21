package FileAss;

import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {
    public static void main(String[] args) {
        try {
            FileWriter writer = new FileWriter("src/File/file.txt", true);

            String data = "This line is written in text file.";
            String data2= "More line after file text";
//            writer.write(data);
            writer.write(data2);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
