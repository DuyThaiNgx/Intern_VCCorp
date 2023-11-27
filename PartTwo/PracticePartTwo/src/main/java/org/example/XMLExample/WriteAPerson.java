package org.example.XMLExample;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class WriteAPerson {
    public static void main(String[] args) {
        Person person = new Person();
        person.setName("John");
        person.setAge(30);
        person.setCity("New York");

        try {
            // Tạo đối tượng JAXBContext
            JAXBContext context = JAXBContext.newInstance(Person.class);

            // Tạo đối tượng Marshaller
            Marshaller marshaller = context.createMarshaller();

            // Ghi đối tượng vào file XML
            marshaller.marshal(person, new File("src/XMLExample/XMLObjectFile.xml"));
            System.out.println("Done writing to XML file");

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
