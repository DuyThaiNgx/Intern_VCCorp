package OOP;

public class AbstractExample {
    abstract class Shape {
        protected String name;
        Shape(String n) { name = n; }
        public String getName() { return name; }
        public abstract float calculateArea();
    }
    public class Circle extends Shape {
        private int radius;
        Circle(String n, int r){
            super(n);
            radius = r;
        }
        public float calculateArea() {
            float area=(float) (3.14 * radius * radius);
            return area;
        }


    }

}

//class mainAbstract{
//    public static void main(String[] args) {
//        Circle c1 =  new Circle();
//        c1.getRadius();
//        Shape c2 = new Sha
//    }
//}
