package UsingData;

import java.util.Iterator;
import java.util.Vector;

public class VectorExample {
    public static void main(String[] args) {
        Vector <String> vec = new Vector<>();
        vec.add("Java");
        vec.add("PHP");
        vec.add("Python");
        vec.add(1, "C++");
        System.out.println("Vec: "+vec);

        Vector <String> vec1 = new Vector<>();
        vec1.add("NodeJS");
        vec1.addAll(vec);
        System.out.println("Vec1 line with Vec: "+vec1);
        Iterator<String> iterate = vec1.iterator();
        System.out.println(iterate.next());
        vec.remove("Java");
        System.out.println("Vec after remove Java element: "+vec);
        vec.remove(0);
        System.out.println("Vec after remove 0 element: "+vec);
        System.out.println(vec1.capacity());
        System.out.println(vec1.contains("PHP1"));
        vec.clear();
        System.out.println("Vec after remove all: "+vec);
        System.out.println(vec1.get(1));
        System.out.println(vec1.indexOf("Python"));



    }

}
