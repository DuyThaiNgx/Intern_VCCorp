package ThreadInJava;

import java.util.concurrent.ConcurrentHashMap;

public class concurrentHashMapEx {
    //    public static void main(String[] args) {
//        ConcurrentHashMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();
//
//        // Tạo và chạy các luồng thêm dữ liệu vào concurrentMap
//        for (int i = 0; i < 5; i++) {
//            final int threadNumber = i;
//            Thread thread = new Thread(() -> {
//                for (int j = 0; j < 1000; j++) {
//                    String key = "Key-" + threadNumber + "-" + j;
//                    concurrentMap.put(key, j);
//                }
//            });
//            thread.start();
//        }
//
//        // Chờ tất cả các luồng kết thúc
//        Thread[] threads = new Thread[Thread.activeCount()];
//        Thread.enumerate(threads);
//        for (Thread thread : threads) {
//            if (thread != null && !Thread.currentThread().equals(thread)) {
//                try {
//                    thread.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        // In ra kích thước của concurrentMap sau khi tất cả các luồng đã kết thúc
//        System.out.println("Size of ConcurrentHashMap: " + concurrentMap.size());
//    }
    public static void main(String[] args) {
        ConcurrentHashMap<Integer, String> concurrentMap = new ConcurrentHashMap<>();

        // Thêm dữ liệu vào concurrentMap từ các luồng
        for (int i = 0; i < 5; i++) {
            final int threadNumber = i;
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    concurrentMap.put((threadNumber * 100) + j, "Value-" + threadNumber + "-" + j);
                    System.out.println("check " + j);

                }
            });
            thread.start();
        }

        System.out.println("check");

        // Chờ tất cả các luồng kết thúc
        Thread[] threads = new Thread[Thread.activeCount()];
        Thread.enumerate(threads);
        for (Thread thread : threads) {
            if (thread != null && !Thread.currentThread().equals(thread)) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
//        thread.?
        }

        // In ra kích thước của concurrentMap sau khi tất cả các luồng đã kết thúc
        System.out.println("Size of ConcurrentHashMap: " + concurrentMap.size());
    }
}

