package FileAss;

import OOP.Student;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class ReadObject {
    public static <Person> void main(String[] args) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("src/FileAss/ObjectFile.txt");
//        FileInputStream fis = new FileInputStream("src/file/object.bin");
        ObjectInputStream ois = new ObjectInputStream(fis);

        List<NhanVien> list = new ArrayList<>();

        while (fis.available() > 0){
            NhanVien staff = (NhanVien) ois.readObject();
            list.add(staff);
            System.out.println(staff.toString());
        }

        ois.close();
        fis.close();
    }
//    public static void main(String[] args) {
//        String fileName = "src/FileAss/ObjectFile.txt";
//
//        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
//            byte[] buffer = new byte[1024];
//            int bytesRead;
//
//            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
//                // Đọc từng dòng dữ liệu vào buffer
//                String data = new String(buffer, 0, bytesRead);
//                System.out.print(data);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
