package XMLExample;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class FileXML {
    public static final String xmlFilePath = "src/XMLExample/testXML.xml";
    public static void main(String argv[]) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();

        Element root = document.createElement("company");
        document.appendChild(root);

        Element employee1 = createEmployeeElement(document, "1", "Nguyen", "Hai", "deft", "95");
        root.appendChild(employee1);
        Element employee2 = createEmployeeElement(document, "2", "Tran", "Van", "smart", "90");
        root.appendChild(employee2);

        // Tạo đối tượng employee 3
        Element employee3 = createEmployeeElement(document, "3", "Le", "Thi", "bright", "88");
        root.appendChild(employee3);
        // Ghi vào file XML
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File(xmlFilePath));
        transformer.transform(domSource, streamResult);

        System.out.println("Done creating XML File");

//        try {
//            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
//            Document document = documentBuilder.newDocument();
//            // root element
//            Element root = document.createElement("company");
//            document.appendChild(root);
//            // employee element
//            Element employee = document.createElement("employee");
//            root.appendChild(employee);
//            // set an attribute to staff element
//            Attr attr = document.createAttribute("id");
//            attr.setValue("10");
//            employee.setAttributeNode(attr);
//            //you can also use staff.setAttribute("id", "1") for this
//            // firstname element
//            Element firstName = document.createElement("firstname");
//            firstName.appendChild(document.createTextNode("Nguyen"));
//            employee.appendChild(firstName);
//            // lastname element
//            Element lastname = document.createElement("lastname");
//            lastname.appendChild(document.createTextNode("Hai"));
//            employee.appendChild(lastname);
//            // email element
//            Element email = document.createElement("nickname");
//            email.appendChild(document.createTextNode("deft"));
//            employee.appendChild(email);
//            // department elements
//            Element department = document.createElement("marks");
//            department.appendChild(document.createTextNode("95"));
//            employee.appendChild(department);
//
//            TransformerFactory transformerFactory = TransformerFactory.newInstance();
//            Transformer transformer = transformerFactory.newTransformer();
//            DOMSource domSource = new DOMSource(document);
//            StreamResult streamResult = new StreamResult(new File(xmlFilePath));
//            transformer.transform(domSource, streamResult);
//            System.out.println("Done creating XML File");
//        } catch (ParserConfigurationException pce) {
//            pce.printStackTrace();
//        } catch (TransformerException tfe) {
//            tfe.printStackTrace();
//        }
    }
    private static Element createEmployeeElement(Document document, String id, String firstName, String lastName, String nickname, String marks) {
        // Tạo phần tử employee
        Element employee = document.createElement("employee");

        // Tạo và thiết lập thuộc tính id
        Attr attr = document.createAttribute("id");
        attr.setValue(id);
        employee.setAttributeNode(attr);

        // Tạo các phần tử con cho mỗi employee
        Element firstNameElement = document.createElement("firstname");
        firstNameElement.appendChild(document.createTextNode(firstName));
        employee.appendChild(firstNameElement);

        Element lastNameElement = document.createElement("lastname");
        lastNameElement.appendChild(document.createTextNode(lastName));
        employee.appendChild(lastNameElement);

        Element nicknameElement = document.createElement("nickname");
        nicknameElement.appendChild(document.createTextNode(nickname));
        employee.appendChild(nicknameElement);

        Element marksElement = document.createElement("marks");
        marksElement.appendChild(document.createTextNode(marks));
        employee.appendChild(marksElement);

        return employee;
    }
}
