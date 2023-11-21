package ThreadInJava;

public class RunableThread implements Runnable {
    public void run() {

        long timestamp = System.currentTimeMillis();
        System.out.printf("[%s]Checking timestamp%n", Thread.currentThread().getName());
        System.out.printf("[%s]My timestamp: %d%n", Thread.currentThread().getName(), timestamp);

    }

    public static void main(String[] args) {

        Thread thread1 = new Thread(new RunableThread());
        Thread thread2 = new Thread(new RunableThread());
        Thread thread3 = new Thread(new RunableThread());

        thread1.start();
        thread2.start();
        thread3.start();

    }
}
