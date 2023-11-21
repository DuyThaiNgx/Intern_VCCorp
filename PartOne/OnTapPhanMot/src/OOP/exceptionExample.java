package OOP;

public class exceptionExample {
    public static void main(String[] args) {
        try {
            int result = 10 / 0; // Sẽ ném ArithmeticException
        } catch (ArithmeticException e) {
            System.out.println("Lỗi chia cho 0: " + e.getMessage());
        }
        System.out.println("----------------------------Da khoi lenh----------------------------");
        try {
            int a[] = new int[5];
            a[5] = 30 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Catch 1 found");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("catch 2 found");
        } catch (Exception e) {
            System.out.println("common task completed");
        }

        System.out.println("Complete");
        System.out.println("-------------------------------Finally-------------------------------");

        try {
            int check = 25/1;
            System.out.println(check);
        }catch (ArithmeticException e){
            System.out.println("Lỗi chia cho 0");
        }
        finally {
            System.out.println("Lệnh finally luôn được thực thi");
        }
    }
}
