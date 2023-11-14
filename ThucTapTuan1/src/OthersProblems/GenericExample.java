package OthersProblems;

public class GenericExample <K,V> {
    private K keyEx;
    private V valueEx;

    public GenericExample(K keyEx, V valueEx) {
        this.keyEx = keyEx;
        this.valueEx= valueEx;
    }

    public K getKey() {
        return keyEx;
    }

    public void setKey(K key) {
        this.keyEx = key;
    }

    public V getValue() {
        return valueEx;
    }

    public void setValue(V value) {
        this.valueEx = value;
    }

    public static void main(String[] args) {
        GenericExample<String, String> ex = new GenericExample<>("ngu", "DUy Thai");
            String name = ex.getValue();
            String id = ex.getKey();
//            name = "String";
//            id = "ngu";
        System.out.println(name +"     "+ id);
    }


}
