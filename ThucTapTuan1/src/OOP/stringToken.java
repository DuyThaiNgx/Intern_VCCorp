package OOP;

import java.util.StringTokenizer;

public class stringToken {
    public static void main(String[] args) {
        //Dấu phân tách mặc định là khoẳng trắng (space).
        // Nếu bạn không khai báo dấu phân tách khi khởi tạo lớp StringTokenizer, thì mặc định sẽ là một hoặc nhiều khoảng trắng.
//        StringTokenizer str1 = new StringTokenizer("nguyễn duy thái hà    đông hà nội");
//        System.out.println(str1.countTokens());
//        while (str1.hasMoreTokens()) {
//            System.out.println(str1.nextToken());
//        }
        //Chỉ định dấu phân cách. Dấu phân cách được chỉ định trong ví dụ này là dấu trừ (-).
        StringTokenizer str2 = new StringTokenizer("Nguyen duy   7--Thai--------test1-test2", "-");
        System.out.println("Tổng số token str2 - là: " +str2.countTokens());
        while (str2.hasMoreTokens()) {
            System.out.println(str2.nextToken());
        }


        //Chỉ định tham số thứ 3 (returnValue) là false thì dấu phân cách không được tính là các phần tử token.
        //Như ví dụ dưới đây thì dấu - sẽ ko dc tính là 1 token
        StringTokenizer str3 = new StringTokenizer("Nguyen duy   7--Thai--------test1-test3", "-",false);
        System.out.println("Tổng số token str3 - là: " +str3.countTokens());
        while (str3.hasMoreTokens()) {
            System.out.println(str3.nextToken());
        }


        //Chỉ định tham số thứ 3 (returnValue) là true thì dấu phân cách được tính là các phần tử token.
        //Như ví dụ này thì dấu - sẽ dc tonhs là 1 token
        StringTokenizer str4 = new StringTokenizer("Nguyen duy   7--Thai---test1-test3", "-",true);
        System.out.println("Tổng số token str4 - là: " +str4.countTokens());
        while (str4.hasMoreTokens()) {
            System.out.println(str4.nextToken());
        }

        StringTokenizer st = new StringTokenizer("Toi-ten-,la-VietTut", "-,", false);
        System.out.println("Tổng số token: " + st.countTokens());
        // in chuỗi token dựa trên dấu phân cách
        System.out.println("Chuỗi token: " + st.nextToken("-,"));
        System.out.println("Chuỗi token kế tiếp: " + st.nextToken("e"));
    }
}
