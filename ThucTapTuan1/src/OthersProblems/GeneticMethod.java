package OthersProblems;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class checkCount{
    /**
     *
     * @param collection
     * @param items
     * @return
     * @param <T>
     */
    public static <T> int count(Collection <T> collection, T items){
        int count = 0;
        for(T item : collection){
            if(items.equals(item)) count++;
        }
        return count;
    }
}
public class GeneticMethod {


    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("Duy");
        list.add("DuyThai");
        list.add("Duy");
        list.add("Ddty");
        list.add("Nguyen");
        System.out.println("Check: "+ checkCount.count(list, "Duy"));


    }
}
