package FileAss;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class readFileBinary {
    public static void main(String[] args) {
        try {
            File file = new File("src/FileAss/FileBinary.bin");
            FileInputStream fis = new FileInputStream(file);

            int data;
            while ((data = fis.read()) != -1) {
                System.out.print((char) data);
            }

            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
