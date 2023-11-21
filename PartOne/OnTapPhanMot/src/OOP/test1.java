package OOP;

import java.util.HashSet;
import java.util.Set;

public class test1 {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        for(int x=0; x<1000;x++){
            for(int y = 0; y < 1000 ; y++)
            {
                set.add(x*3739+y);

            }

        }
        System.out.println(set.size());
    }
}
