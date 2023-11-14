package ThreadInJava;

public class CreateThread extends Thread {
//    Tạo thread thông thường
    public void run(){
        System.out.println("Thread is running.");
    }

    public static void main(String[] args) {
        CreateThread t1 = new CreateThread();
        t1.start();
    }


}
