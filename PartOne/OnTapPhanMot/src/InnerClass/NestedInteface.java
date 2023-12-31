package InnerClass;

interface Showable {
    void show();

    interface Message {
        void msg();
    }
}

class NestedInterface implements Showable.Message {
    public void msg() {
        System.out.println("Hello nested interface");
    }

    public static void main(String args[]) {
        Showable.Message message = new NestedInterface(); // upcasting
        message.msg();
    }
}