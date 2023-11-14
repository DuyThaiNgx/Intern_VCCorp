package ThreadInJava;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {
    private static int count = 0;
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        // Tạo và chạy các luồng
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                performTask();
                //Sau khi thực hiện xong thì hàm performTask sẽ giải phóng 1 luồng đang chạy
            });
            //Cho luồng mới chạy
            thread.start();
        }
    }

    private static void performTask() {
        lock.lock(); // Bắt đầu locking

        try {
            for (int i = 0; i < 1000; i++) {
                count++;
            }
            System.out.println("Count: " + count);
        } finally {
            lock.unlock(); // Giải phóng lock
        }
    }
}
