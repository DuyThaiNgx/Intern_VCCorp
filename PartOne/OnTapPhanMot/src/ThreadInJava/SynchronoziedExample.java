package ThreadInJava;

public class SynchronoziedExample extends Thread {
    public static void main(String[] args) {
        Table obj = new Table();
//        SynchronoziedExample t1 = new SynchronoziedExample();
        MyThread1 thread1 = new MyThread1(obj);
        MyThread2 thread2 = new MyThread2(obj);
        thread1.start();
        thread2.start();
    }
}

class Table {

    synchronized void printTable(int n)// method sử dụng synchronized
//    void printTable(int n)  // method không synchronized
    {
        for (int i = 1; i <= 5; i++) {
            System.out.println(n * i);
            try {
                Thread.sleep(400);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}

class MyThread1 extends Thread {
    Table t;

    MyThread1(Table t) {
        this.t = t;
    }

    public void run() {
        t.printTable(10);
    }
}

class MyThread2 extends Thread {
    Table t;

    MyThread2(Table t) {
        this.t = t;
    }

    public void run() {
        t.printTable(100);
    }
}
