package ColletionJava;

import java.util.TreeMap;

class Student implements Comparable<Student> {
    private String name;
    private int rollNumber;

    public Student(String name, int rollNumber) {
        this.name = name;
        this.rollNumber = rollNumber;
    }

    @Override
    public int compareTo(Student otherStudent) {
        // Sắp xếp theo số thứ tự (roll number)
        //Sắp xếp các đối tượng thứ tự theo key là rollNumber
        return Integer.compare(this.rollNumber, otherStudent.rollNumber);
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber;
    }
}

public class CompareToExample {
    public static void main(String[] args) {
        TreeMap<Student, String> studentMap = new TreeMap<>();
        //Khi set 1 treemap như này thí sẽ không biết được sẽ so sánh key như nào
        //So sánh với key là 1 đối tượng kiểu Student, vale là Grade, trong
        // 1 đối tượng thì so sánh đối tượng bằng key rollNumber để sắp xếp
        studentMap.put(new Student("Alice", 101), "Grade A");
        studentMap.put(new Student("Bob", 102), "Grade B");
        studentMap.put(new Student("Carol", 100), "Grade C");

        for (Student student : studentMap.keySet()) {
            System.out.println(student + " - " + studentMap.get(student));
        }
    }
}
