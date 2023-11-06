package FileAss;

import java.io.FileOutputStream;
import java.io.IOException;

public class WriteBinaryFile {
    public static void main(String[] args) {

        try {
            FileOutputStream fos = new FileOutputStream("src/File/FileBinary.bin",true);
            byte[] data = { 65, 66, 67, 68, 69 }; // Dữ liệu mẫu
            String binData = "This line is written in Binary";
            fos.write(data);
            fos.write(binData.getBytes());

            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
