package UsingData;

import java.util.Stack;

public class StackNonRecur {
    public static int calculateFactorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Số n không được âm");
        }

        Stack<Integer> stack = new Stack<>();
        int result = 1;

        for (int i = 1; i <= n; i++) {
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int current = stack.pop();
            result *= current;
        }

        return result;
    }

    public static void main(String[] args) {
        int n = 5;
        int factorial = calculateFactorial(n);
        System.out.println("Giai thừa của " + n + " là " + factorial);
    }
}
