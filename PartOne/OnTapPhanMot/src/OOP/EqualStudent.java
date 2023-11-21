package OOP;

public class EqualStudent {
    public static void main(String[] args) {
        Student student1 = new Student("123", "Cong", "cong@gmail.com", 22);
        Student student2 = new Student("123", "Cong", "cong@gmail.com", 22);
        Student student3 = new Student("456", "Dung", "dung@gmail.com", 18);

        System.out.println("student1 == student2: " + (student1 == student2));
        System.out.println("student1.equals(student2): "
                + (student1.equals(student2)));
        System.out.println("student2.equals(student3): "
                + (student2.equals(student3)));
    }
}
