import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ExerciseOne {
    public static void main(String[] args) {
        Set<Integer> randomSetOne = randomSet(2000000);
        Set<Integer> randomSetTwo = randomSet(2000000);
//        System.out.println("Set 1: "+randomSetOne);
//        System.out.println("Set 2: "+randomSetTwo);
        //Intersection Set
        long startExecuteIntersection = System.nanoTime();
        intersectionSet(randomSetOne, randomSetTwo);
        long endExecuteIntersection = System.nanoTime();
        long interTime = TimeUnit.NANOSECONDS.toMillis(endExecuteIntersection - startExecuteIntersection);
        //Union Set
        long startExecuteUnion = System.nanoTime();
        unionSet(randomSetOne, randomSetTwo);
        long endExecuteUnion = System.nanoTime();
        long unionTime = TimeUnit.NANOSECONDS.toMillis(endExecuteUnion - startExecuteUnion);
        System.out.println("Time to exectue intersection Set is: "+interTime+ " milliseconds");
        System.out.println("Time to exectue union Set is: "+unionTime+ " milliseconds");


//        Random random = new Random();
//        while(randomSetOne.size() < 2000000){
//            int randomNumber = random.nextInt(); // Số ngẫu nhiên
//            randomSetOne.add(randomNumber);
//        }
//        System.out.println(randomSetOne.size());
//        while(randomSetOne.size() < 2000000){
//            int randomNumber = random.nextInt(); // Số ngẫu nhiên
//            randomSetTwo.add(randomNumber);
//        }
//        System.out.println(randomSetOne.size());
    }
    public static Set<Integer> randomSet(int size){
        Set<Integer> randomSet = new HashSet<>();
        Random random = new Random();
        while(randomSet.size() < size){
            int randomNumber = random.nextInt(30000000); // Số ngẫu nhiên
            randomSet.add(randomNumber);
        }
        return randomSet;
    }
    public static void intersectionSet(Set<Integer> setOne, Set<Integer> setTwo){
        Set<Integer> intersectionSet = new HashSet<>(setOne);
        intersectionSet.retainAll(setTwo);
//        System.out.println("Intersection Set: " + intersectionSet);
        System.out.println("Number of duplicate elements is: "+intersectionSet.size());
    }
    public static void unionSet(Set<Integer> setOne, Set<Integer> setTwo){
        Set<Integer> unionSet = new HashSet<>(setOne);
        unionSet.addAll(setTwo);
//        System.out.println("Union Set: " + unionSet);
        System.out.println("Number of union elements of 2 Set is: "+unionSet.size());
    }
}
