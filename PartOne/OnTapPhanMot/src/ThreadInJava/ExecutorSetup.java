package ThreadInJava;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorSetup {
    public static void main(String[] args) {
        // Tạo Executor với số luồng tối đa là 5
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Thực hiện công việc (nhiệm vụ) bằng ExecutorService
        for (int i = 0; i < 10; i++) {
            Runnable worker = new MyRunnable("Task " + i);
            executor.execute(worker);
        }

        // Kết thúc ExecutorService sau khi hoàn thành các nhiệm vụ
        executor.shutdown();
    }
}

class MyRunnable implements Runnable {
    private String taskName;

    public MyRunnable(String taskName) {
        this.taskName = taskName;
    }

    public void run() {
        System.out.println("Running " + taskName + " in thread: " + Thread.currentThread().getName());
//        System.out.println("    :"+ Thread.currentThread().getName());
    }
}
