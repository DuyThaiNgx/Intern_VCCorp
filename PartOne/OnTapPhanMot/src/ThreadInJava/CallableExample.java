package ThreadInJava;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CallableExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        List<Future<Integer>> resultList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            int threadNumber = i;
            Callable<Integer> task = new MyCallable(i + 1);
            Future<Integer> result = executor.submit(task);
            resultList.add(result);
        }

        // Lấy kết quả từ các luồng sau khi chúng hoàn thành
        for (Future<Integer> future : resultList) {
            try {
                int result = future.get(); // Lấy kết quả
                System.out.println("Result from thread: " + result);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
    }
}

class MyCallable implements Callable<Integer> {
    private final int number;

    public MyCallable(int number) {
        this.number = number;
    }

    public Integer call() {
        // Thực hiện công việc ở đây, return kết quả
        return number * 2;
    }
}
