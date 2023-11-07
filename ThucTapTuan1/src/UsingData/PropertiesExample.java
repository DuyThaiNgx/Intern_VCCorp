package UsingData;

import java.io.*;
import java.util.Properties;

public class PropertiesExample {
//    FileReader fd = new FileReader("src/UsingData/db.proerties");
    //Create Properties
    public static void main(String[] args) throws IOException {
        Properties ppt = new Properties();
        ppt.setProperty("username", "dtngx");
        ppt.setProperty("pass", "2309");
        ppt.store(new FileOutputStream("src/UsingData/db.properties"),"comment");

        //Load file
        ppt.load(new FileInputStream("src/UsingData/db.properties"));

        String username = ppt.getProperty("username");
        String password = ppt.getProperty("pass");

        System.out.println(username + " : " + password);

        //Set file properties
        ppt.load(new FileInputStream("src/UsingData/db.properties"));
        ppt.setProperty("username","taikhoan");
        ppt.store(new FileOutputStream("src/UsingData/db.properties"),"Update");
        //Get lai cac username vs pass mới
        username = ppt.getProperty("username");
        password = ppt.getProperty("pass");
        System.out.println("DB sau khi đã được update");
        System.out.println(username + " : " + password);
//        ppt.list(System.out);

    }


}
