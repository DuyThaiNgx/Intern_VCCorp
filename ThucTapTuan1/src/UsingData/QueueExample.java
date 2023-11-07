package UsingData;

import javax.jws.soap.SOAPBinding;
import java.util.LinkedList;
import java.util.Queue;


public class QueueExample {
    public static void main(String[] args) {
        Queue qe = new LinkedList<>();
        qe.add(21);
        qe.add(2);
        qe.add(3);
        qe.add(4);
        qe.add(5);
        System.out.println(qe);
//        System.out.println(qe.remove());
        System.out.println(qe.peek());
        System.out.println("Queue after peek(): "+qe);
        System.out.println("Queue poll: " + qe.poll());
        System.out.println("Queue after poll(): "+qe);
        qe.remove();
        System.out.println("Queue after remove:"+qe);


    }
}
