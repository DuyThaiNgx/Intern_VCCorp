package InnerClass;

public class StaticNestedClass {
    /**
     * Một lớp static được tạo bên trong một lớp được gọi là lớp static lồng hay static nested class.
     * Nó không thể truy cập các thành viên và phương thức non-static. Nó có thể được truy cập bởi tên lớp bên ngoài.
     * Nó có thể truy cập các thành viên dữ liệu tĩnh (static data members) của lớp ngoài bao gồm cả private.
     * Static nested class không thể truy cập thành viên dữ liệu hoặc phương thức non-static (instance).
     **/

//    private int data = 30;
    static int data = 40;

    static class Inner {
        void msg() {
            System.out.println("data is " + data);
        }
    }

    // Khi muốn truy cập vào 1 biến non static, chúng ta phải thông qua 1 đối tượng của lớp đó
    public static void main(String args[]) {
        StaticNestedClass.Inner obj = new StaticNestedClass.Inner();
        System.out.println(data);
        StaticNestedClass obj2 = new StaticNestedClass();
        System.out.println(obj2.data);
        obj.msg();
//        StaticNestedClass.Inner.msg();
    }
}
