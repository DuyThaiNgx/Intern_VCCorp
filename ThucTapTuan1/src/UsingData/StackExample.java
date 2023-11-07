package UsingData;

import java.util.Stack;

public class StackExample {
    static void pushing(Stack st, int a){
        st.push(a);
        System.out.println("Push " +a);
        System.out.println("Stack after push "+a +" is: "+st);
    }

    static void poping(Stack st, int a){

    }

    public static void main(String[] args) {
        Stack st1 = new Stack();
        pushing(st1, 20);
        pushing(st1, 25);
        pushing(st1, 23);
        pushing(st1, 230);
        pushing(st1, 222);
        st1.pop();
        System.out.println(st1);
        //Trả về phần tử trên cùng của Stack, nhưng không gỡ bỏ nó
        System.out.println("Peek of stack: "+st1.peek());
        //Getindex lấy vị trừ từ 0 tính từ đáy stack
        System.out.println(st1.elementAt(2)+ "  "+ st1.get(0));
        //Search trả về vị trí tính từ đỉnh của stack, bắt đầu từ 1
        System.out.println("Position of 23 is: "+ st1.search(23));
        System.out.println("Position of 230: "+st1.search(230));

        Stack<String> animals= new Stack<>();

        // Add elements to Stack
        animals.push("Dog");
        animals.push("Horse");
        animals.push("Cat");
        System.out.println("Stack: " + animals);

        // Search an element
        int position = animals.search("Dog");
        System.out.println("Position of Horse: " + position);
    }
}
