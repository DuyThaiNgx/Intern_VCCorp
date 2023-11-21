package InnerClass;
abstract class Person {
    abstract void eat(int a, int b);
    abstract void divine(String str);
}
public class AnonymousInnerClass {
    public static void main(String args[]) {
        Person p = new Person() {
            @Override
            void eat(int a, int b) {
                System.out.println(a+b);
            }


            @Override
            void divine(String str2) {
//                str2 = "Duy thai";
                System.out.println(str2);
            }
        };
    p.eat(10,20);
    p.divine("DuyThaiNguyen");
    }
}