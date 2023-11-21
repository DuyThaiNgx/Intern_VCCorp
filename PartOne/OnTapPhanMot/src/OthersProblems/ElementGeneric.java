package OthersProblems;

public class ElementGeneric {

    public static <E> void printArr(E[] str){

        for(E element : str){
            System.out.println(element);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //Khai báo thông qua generic
        Integer[] strInt = {10,23,544,34,634};
        String[] strStr = {"String", "DuyThai", "dtngx"};
//        int[] str1 = new int[15];
//        int[] str = {1,2,6,4,3};
        System.out.println();
        System.out.print("Du");
        printArr(strInt);
        printArr(strStr);
    }
}
