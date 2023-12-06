package com.example.ExerciseFour;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CacheTTL<K, V> {

    private final Map<K, CacheItem<V>> cacheMap = new HashMap<>();
    private static long ttl; // Thời gian sống cho từng phần tử (milliseconds)

    public static void main(String[] args) {
        CacheTTL ca = new CacheTTL(3,60000);
        Cache<String,CacheTTL> guavaCache= CacheBuilder.newBuilder().
                maximumSize(100).
                expireAfterAccess(30, TimeUnit.MINUTES).
                build();

        System.out.println(System.currentTimeMillis());
    }
    // n là thời gian sống của một phần tử trong cache (milliseconds)
    // m là thời gian sống của cache (milliseconds)
    public CacheTTL(int n, int m) {

        //Chuyển từ seconds sang milliseconds
        this.ttl = TimeUnit.MILLISECONDS.convert(n, TimeUnit.SECONDS);
        // Sử dụng Timer để định kỳ xóa các phần tử hết hạn khỏi cache
//        System.out.println(TimeUnit.MILLISECONDS.convert(n, TimeUnit.SECONDS));
        java.util.Timer timer = new java.util.Timer(true);
        timer.scheduleAtFixedRate(new TimerTaskCleanup(), 1, ttl);
        /**Thực hiện task của đối tượng trong lớp TimerTaskCleanup cần làm với độ trễ delay trước khi
        bắt đầu lên lịch là 1s với ttl là khoảng thời gian giữa các lần thực hiện công việc (tính bằng mili giây).
         */
    }
    //Hàm lấy giá trị ở trong cache để kiểm tra xem các phần tử còn hiệu lực không
    public V get(K key) {
        CacheItem<V> cacheItem = cacheMap.get(key);
        if (cacheItem != null && cacheItem.isNotExpired()) {
            return cacheItem.getData();
        } else {
            // Nếu phần tử đã hết hạn, loại bỏ nó khỏi cache
            cacheMap.remove(key);
            return null;
        }
    }

    public void put(K key, V value) {
        CacheItem<V> cacheItem = new CacheItem<>(value);
        /**Mỗi 1 phần tử mới được thêm vào cache sẽ được đặt vào cacheMap dưới dạng 1 CacheItem
         * */
        cacheMap.put(key, cacheItem);
    }

    //Trả về toàn bộ cache hiện tại chứa các phần tử hợp lệ trong cache
    public Map<K, V> getMap() {
        Map<K, V> resultMap = new HashMap<>();
        for (Map.Entry<K, CacheItem<V>> entry : cacheMap.entrySet()) {
            if (entry.getValue().isNotExpired()) {
                resultMap.put(entry.getKey(), entry.getValue().getData());
            }
        }
        return resultMap;
    }

    private class TimerTaskCleanup extends java.util.TimerTask {
        @Override
        public void run() {
            // Loại bỏ các phần tử hết hạn khỏi cache
            cacheMap.entrySet().removeIf(entry -> !entry.getValue().isNotExpired());
        }
    }
    // Hàm CacheItem Lưu trữ 1 phần tử trong cache
    private static class CacheItem<T> {
        private final long timestamp;
        //Timestamp là thời điểm phần tử được thêm vào cache
        private final T data;

        public CacheItem(T data) {
            this.timestamp = System.currentTimeMillis();
            this.data = data;
        }

        public T getData() {
            return data;
        }
        //Kiểm tra xem phần từ được cho vào có còn sống hay không, nếu từ timeCUrrent - time lúc vào mà >= ttl(thời gian sống)
        // thì tức là vẫn còn thời hạn trong cache
        public boolean isNotExpired() {
            return (System.currentTimeMillis() - timestamp) >= ttl;
        }
        //Nếu phần tử trong cache hết hạn thì trả về true tức là NotExprired
    }
}