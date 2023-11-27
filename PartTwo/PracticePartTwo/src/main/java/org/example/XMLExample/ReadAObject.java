package org.example.XMLExample;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class ReadAObject {
    public static void main(String[] args) {
        try {
            // Tạo đối tượng JAXBContext
            JAXBContext context = JAXBContext.newInstance(Person.class);

            // Tạo đối tượng Unmarshaller
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Đọc đối tượng từ file XML
            File file = new File("src/XMLExample/XMLObjectFile.xml");
            Person person = (Person) unmarshaller.unmarshal(file);

            // In thông tin đối tượng
            System.out.println("Name of : " + person.getName());
            System.out.println("Age: " + person.getAge());
            System.out.println("City: " + person.getCity());

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
