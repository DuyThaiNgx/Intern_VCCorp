package OOP;

public class string {
    public static void main(String[] args) {
        String str = "Nguyen Duy Thai";
        String str2 = new String("Nguyen Duy Thai");
//        Nối chuỗi
        str2 = str2.concat(" IT LTU K65");
        System.out.println(str.substring(0,6));
        System.out.println(str);
        System.out.println(str2);
    }
}
