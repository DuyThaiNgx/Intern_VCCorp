package OOP;

import java.util.LinkedList;

public class linkedList {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<String>();
        // thêm các phần tử vào list
        list.add("Java");
        list.add("C++");
        list.add("PHP");
        list.add("Java");
        list.add(" ngu");
        list.addFirst("DuyThais");
        list.addLast("atleast");


        // hiển thị các phần tử của list
        System.out.println("Các phần tử có trong list là: ");
        System.out.println(list);
        System.out.printf(list.getLast() + "   " +list.size());
    }
}
