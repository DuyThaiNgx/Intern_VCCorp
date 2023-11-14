package OthersProblems;

public class GenericEx{


    public static  <E,V> void  add(E e, V v){
        System.out.println(Integer.parseInt(e.toString())+ Integer.parseInt(v.toString()));
    }

    public static void main(String[] args) {
//        GenericEx<Integer, Integer> example = new GenericEx<>();
        add(3,4);

    }
}
