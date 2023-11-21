package Stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class StreamExample {
    public static void main(String[] args) {
        List<String> data = Arrays.asList("Java", "C#", "C++", "PHP", "Javascript");

//        data.stream() //
//                .skip(2) //
//                .limit(2) //
//                .forEach(System.out::print); // C#C++PHP
        data.stream() //
                .sorted(Comparator.comparingInt(String::length)) //
                .forEach(System.out::println);
    }

}
