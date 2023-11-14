package InnerClass;

public class InnerClassExample {
    private int data1 = 120;
    class Inner{
        void add(int a, int b) {
            System.out.println("add: " + (a + b));
            System.out.println("Number from Outer class: "+data1);
        }
    }

    public static void main(String[] args) {
        InnerClassExample obj1 = new InnerClassExample();
        InnerClassExample.Inner inner = obj1.new Inner();
        System.out.println(obj1.data1);
        inner.add(10,20);
    }
}
