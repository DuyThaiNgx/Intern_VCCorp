package OOP;

class Person {
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

    class main{
        public static void main(String[] args) {
            Person p1 = new Person();
            p1.setAge(21);
            p1.setName("Thái");
            System.out.printf("Tên: " + p1.getName()+ "\nTuổi: "+p1.getAge());
        }
    }

