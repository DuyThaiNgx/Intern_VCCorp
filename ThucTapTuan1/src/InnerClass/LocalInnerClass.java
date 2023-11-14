package InnerClass;

public class LocalInnerClass {
    /**
     * Một lớp được tạo ra bên trong một phương thức được gọi là local inner class.
     * Nếu bạn muốn gọi các phương thức của lớp được khai báo bên trong một phương thức,
     * bạn phải tạo ra thể hiện của lớp này bên trong phương thức chứa nó.
     */

    private int data = 30;// instance variable

    void display() {
        class Local {
             int lo = 10;
            void msg() {
                System.out.println(data);
                System.out.println("Local ");
                lo = lo + 10;
                System.out.println(lo);
            }
        }
        Local l = new Local();
        l.msg();
    }

    public static void main(String args[]) {
        LocalInnerClass obj = new LocalInnerClass();
        obj.display();
    }
}
