package org.example.JsonExample;

import com.google.gson.Gson;


public class JsonStringToObject {
    public static void main(String[] args) {
        // Chuỗi JSON đầu vào
        String jsonString = "[{\"name\":\"Thai\",\"tuoi\":27,\"diaChi\":\"HaDong\",\"thanhPho\":\"HaNoi\"},{\"name\":\"dtngx\",\"tuoi\":20,\"diaChi\":\"ThanhXuan\",\"thanhPho\":\"HaNoi\"},{\"name\":\"VCCORP\",\"tuoi\":15,\"diaChi\":\"ThanhXuan\",\"thanhPho\":\"TPHCM\"}]";

        // Sử dụng Gson để chuyển chuỗi thành đối tượng
        Gson gson = new Gson();
        PersonGuy[] person = gson.fromJson(jsonString, PersonGuy[].class);

        // In thông tin đối tượng
        for (PersonGuy people : person){
            System.out.println("Name: " + people.getName());
            System.out.println("Age: " + people.getTuoi());
            System.out.println("Address: " + people.getDiaChi());
            System.out.println("City: " + people.getThanhPho());
            System.out.println();
        }
    }
}
