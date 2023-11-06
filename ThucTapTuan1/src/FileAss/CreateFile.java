package FileAss;

import java.io.File;
import java.io.IOException;

public class CreateFile {
    public static void main(String[] args) {
        try {
            File file = new File("src/File/file.txt");
            if(file.createNewFile()){
                System.out.println("Created file: " +file.getName());
            }else{
                System.out.println("Không thể tạo tệp");
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}

