package UsingData;

import java.util.BitSet;

public class BitSetExample {
    public static void main(String[] args) {
//        set cho 1 chuỗi nhị phân có 10 bit
        BitSet bit1 = new BitSet(15);
        BitSet bit2 = new BitSet(15);
        for(int i = 0; i < 15 ; i++)
        {
            //Các bit có thứ tự là chẵn từ trái qua phải sẽ là bit 1
            if (i % 2 == 0) {
                bit1.set(i);
            }
            if(i % 3 == 0)
            {
                bit2.set(i);
            }
        }
        System.out.println("Thứ tự các vị trí bit trong chuôi bit1 là: "); //101010101010101
        System.out.println(bit1);
        System.out.println("Thứ tự các vị trí bit trong chuôi bit2 là: "); //100100100100100
        System.out.println(bit2);
        System.out.println("Phép tính AND là: ");   //100000100000100
        bit1.and(bit2);
        System.out.println(bit1); //100000100000100

    }
}
