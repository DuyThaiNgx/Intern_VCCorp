package FileAss;

import OOP.Student;
import com.sun.xml.internal.ws.developer.Serialization;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class NhanVien implements Serializable{
    private int age;
    private String fullName, address, phoneNumber;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public NhanVien(int age, String fullName, String address, String phoneNumber) {
        this.age = age;
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Information about staff:\nName: " +fullName +
                "\nAge: " +age+
                "\nAddress: "+address+
                "\nPhone Number: "+phoneNumber;
    }
}

public class WriteObject {
    public static void main(String[] args) throws IOException {
        NhanVien staff1 = new NhanVien(20, "Nguyễn Duy Thái", "Hà Nội", "0987654321");
        NhanVien staff2 = new NhanVien(25, "dtngx", "Hà Đông", "098765432122");
        List<NhanVien> list = new ArrayList<>();
        list.add(staff1);
        list.add(staff2);
//        File file = new File("src/FileAss/ObjectFile.txt");
        FileOutputStream fos = new FileOutputStream("src/FileAss/ObjectFile.txt");
        ObjectOutputStream object = new ObjectOutputStream(fos);
        for(NhanVien staff: list){
            object.writeObject(staff);

        }
        fos.close();
        object.close();


    }
}
