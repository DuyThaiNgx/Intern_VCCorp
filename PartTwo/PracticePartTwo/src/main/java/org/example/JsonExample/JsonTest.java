package org.example.JsonExample;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class JsonTest {
    public static void main(String[] args) {
        List <PersonGuy> listPerson = new ArrayList<>();
        PersonGuy p1 = new PersonGuy("Thai", 27, "HaDong", "HaNoi");
        PersonGuy p2 = new PersonGuy("dtngx", 20, "ThanhXuan", "HaNoi");
        PersonGuy p3 = new PersonGuy("VCCORP", 15, "ThanhXuan", "TPHCM");
        listPerson.add(p1);
        listPerson.add(p2);
        listPerson.add(p3);
        Gson gson = new Gson();
        String jsonString = gson.toJson(listPerson);

        // In ra chuỗi JSON
        System.out.println(jsonString);
    }
}
class PersonGuy {
    private String name;
    private int tuoi;
    private String diaChi;
    private String thanhPho;

    public PersonGuy(String name, int tuoi, String diaChi, String thanhPho) {
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