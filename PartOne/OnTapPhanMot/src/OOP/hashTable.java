package OOP;

import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;

public class hashTable {
    public static void main(String[] args) {
        Hashtable<String, String> hashtable = new Hashtable<String, String>();
        // add elements to hashtable
        hashtable.put("Key1", "C++");
        hashtable.put("Key2", "Java");
        hashtable.put("Key3", "PHP");
        hashtable.put("Key4", "Python");
        // show hashtable
        show(hashtable);
        // remove element
        hashtable.remove(2);
        // show hashtable after remove
        System.out.println("After remove:");
        // show hashtable
        show(hashtable);

        //Timf kiem bang bang Bam
        Scanner sc = new Scanner(System.in);
        System.out.println("Moi nhap key: ");
        String keyToSearch = sc.nextLine();
        if (hashtable.containsKey(keyToSearch)) {
            String value = hashtable.get(keyToSearch);
            System.out.println("Giá trị cho " + keyToSearch + " là " + value);
        } else {
            System.out.println(keyToSearch + " Không tồn tại trong bảng băm.");
        }
    }

    public static void show(Hashtable<String, String> hashtable) {
        Set<String> keySet = hashtable.keySet();
        for (String key : keySet) {
            System.out.println(key + " " + hashtable.get(key));
        }
    }
}
