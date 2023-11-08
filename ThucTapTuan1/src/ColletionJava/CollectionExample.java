package ColletionJava;

import java.awt.*;
import java.util.*;

public class CollectionExample {
    public static void main(String[] args) {
        HashSet<String> setA = new HashSet<String>();
        setA.add("Java");
        setA.add("Python");
        setA.add("Java");
        setA.add("PHP");
        System.out.println("Số phần tử của setA: " + setA.size()); // CHỉ tính những phần tử không trùng lặp
        System.out.println("Các phần tử của setA: " + setA);
        System.out.println("setA có chứa Java không? " + setA.contains("Java"));
        System.out.println("setA có chứa C++ không? " + setA.contains("C++"));


        LinkedHashSet<String> linkedSet = new LinkedHashSet<>();
        linkedSet.add("Duy Thai");
        linkedSet.add("Dtngx");
        linkedSet.add("Seri");
        linkedSet.add("Anh");
        System.out.println("LinkedHashSet: "+linkedSet);


        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("Duy Thai");
        treeSet.add("Dtngx");
        treeSet.add("Seri");
        treeSet.add("Anh");
        System.out.println("TreeSet: " +treeSet);

        HashMap<Integer,String> hashMap1 = new HashMap<>();
        hashMap1.put(3, "Thai");
        hashMap1.put(4, "Thai");
        hashMap1.put(1, "Thai Nguyen");
        hashMap1.put(0, "Thai dtngx");
        hashMap1.put(10, "Nov8th");
        hashMap1.put(2, "dpa");
        System.out.println("HashMap: "+hashMap1);

        TreeMap<Integer,String > treeMap = new TreeMap<>();
        treeMap.put(3, "Thai");
        treeMap.put(4, "Thai");
        treeMap.put(1, "Thai Nguyen");
        treeMap.put(0, "Thai dtngx");
        treeMap.put(10, "Nov8th");
        treeMap.put(2, "dpa");
        System.out.println("TreeMap: "+treeMap);

        LinkedHashMap<Integer, String> linkedHashMap= new LinkedHashMap<>();
        linkedHashMap.put(3, "Thai");
        linkedHashMap.put(4, "Thai");
        linkedHashMap.put(1, "Thai Nguyen");
        linkedHashMap.put(0, "Thai dtngx");
        linkedHashMap.put(10, "Nov8th");
        linkedHashMap.put(2, "dpa");
        System.out.println("LinkedHashMap: "+linkedHashMap);
    }

}
