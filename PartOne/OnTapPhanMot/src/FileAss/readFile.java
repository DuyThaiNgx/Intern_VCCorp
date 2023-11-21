package FileAss;

import java.io.FileReader;
import java.io.IOException;

public class readFile {
    public static void main(String[] args) {
        // Đọc file bằng Scanner
//        try {
//            File file = new File("src/FileAss/file.txt");
//            Scanner scanner = new Scanner(file);
//
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine();
//                System.out.println(line);
//            }
//
//            scanner.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }


        //Đọc dữ liệu bằng FileReader
        int ch;
        try {

            FileReader file_reader = new FileReader("src/FileAss/file.txt");
            while ((ch = file_reader.read()) != -1){
                System.out.print((char)ch);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
