package ThreadInJava;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleThreadEx {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            // Thực hiện nhiệm vụ ở đây
            System.out.println("Task is executed at: " + System.currentTimeMillis());
        };

        // Lập lịch chạy nhiệm vụ mỗi 5 giây, bắt đầu sau x giây (ở đây x = 0)
        //Ở phương thức scheduleAtFixedRate(), khoảng period được hiểu là độ trễ giữa thời điểm bắt đầu thực thi
        // task lần trước cho tới khi bắt đầu thực thi task lần tiếp theo.
//        executor.scheduleAtFixedRate(task, 1, 5, TimeUnit.SECONDS);
        // withFixedDelay: khoảng period được hiểu là độ trễ giữa thời điểm kết thúc thực thi task
        // lần trước cho tới khi bắt đầu thực thi task lần tiếp theo.
        executor.scheduleWithFixedDelay(task, 1, 5, TimeUnit.SECONDS);
        Thread.sleep(10000);
        executor.shutdown();

    }
}

