package LambdaEx;

public class LambdaExample {
    interface Op{
        public int add(int a, int b);

    }
    public static void main(String[] args) {
        Op op = (a,b) -> {
            return a+b;
        };
        System.out.println(op.add(10,20));

        //Không sử dụng return
        Op op2 = (a,b) -> (a+b);
        System.out.println(op2.add(30,50));
        //Tạo thiết thread với lambda
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread runnable 1");
            }
        };
        Runnable r2 = () -> {
            System.out.println("Thread runnable 2");
        };
        r1.run();
        r2.run();
    }



}
