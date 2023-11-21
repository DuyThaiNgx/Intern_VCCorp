package UsingData;

import java.util.Scanner;

public class EnumExample {
    enum Month {
        Jan(1), Feb(2), Mar(3), Apr(4), May(5), Jun(6), Jul(7),
        Aug(8), Sep(9), Oct(10), Nov(11), Dec(12);
        private int value;

        private Month(int value) {
            this.value = value;
        }

        public static Month getNameByValue(int value){
            for(Month m : Month.values())
            {
                if(m.value == value){
                    System.out.println(m + " stands for " + m.value);
                }
            }
            return null;
        }

        public static void main(String[] args) {
            Month month = Month.Sep;
            System.out.println(month);
            for(Month m: Month.values()){
                System.out.println(m+ " " +m.value);
            }
            Scanner sc = new Scanner(System.in);
//            do {
//                int keyboard = Integer.parseInt(sc.nextLine());
//                getNameByValue(keyboard);
//            }while(Integer.parseInt(sc.nextLine()) != 0);

            int keyboard = Integer.parseInt(sc.nextLine());
            getNameByValue(keyboard);
            while (keyboard != 0){
                keyboard = Integer.parseInt(sc.nextLine());
                getNameByValue(keyboard);
            }

        }
    }
}
