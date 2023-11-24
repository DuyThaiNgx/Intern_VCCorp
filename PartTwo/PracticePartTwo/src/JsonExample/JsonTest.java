package JsonExample;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class JsonTest {
    public static void main(String[] args) {
        List <Person> listPerson = new ArrayList<>();
        Person p1 = new Person("Thai", 27, "HaDong", "HaNoi");
        Person p2 = new Person("dtngx", 20, "ThanhXuan", "HaNoi");
        Person p3 = new Person("VCCORP", 15, "ThanhXuan", "TPHCM");
        listPerson.add(p1);
        listPerson.add(p2);
        listPerson.add(p3);
        Gson gson = new Gson();
        String jsonString = gson.toJson(listPerson);

        // In ra chuỗi JSON
        System.out.println(jsonString);
    }
}
class Person{
    private String name;
    private int tuoi;
    private String diaChi;
    private String thanhPho;

    public Person(String name, int tuoi, String diaChi, String thanhPho) {
        this.name = name;
        this.tuoi = tuoi;
        this.diaChi = diaChi;
        this.thanhPho = thanhPho;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getThanhPho() {
        return thanhPho;
    }

    public void setThanhPho(String thanhPho) {
        this.thanhPho = thanhPho;
    }
}