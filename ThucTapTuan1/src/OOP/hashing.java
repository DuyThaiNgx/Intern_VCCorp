package OOP;

public class hashing {
    public int customHash(int num1, int num2, int num3) {
        int prime = 31; // Chọn một số nguyên tố
        int hash = 17; // Giá trị ban đầu của hash

        // Áp dụng các phép toán số học để kết hợp ba số nguyên thành một giá trị hash
        hash = hash * prime + num1;
        hash = hash * prime + num2;
        hash = hash * prime + num3;

        return hash;
    }

    public static void main(String[] args) {
        hashing s1 = new hashing();

        System.out.println(s1.customHash(1,2,3));
    }
}
