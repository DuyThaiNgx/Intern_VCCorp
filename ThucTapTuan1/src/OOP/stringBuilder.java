package OOP;

public class stringBuilder {
    public static void main(String[] args) {
        StringBuilder str = new StringBuilder("This is a String");
        StringBuilder str2 = new StringBuilder("String 2");
        StringBuilder str3 = new StringBuilder("iahT yuD");
        //Phương thức append() của lớp StringBuilder nối thêm tham số vào cuối chuỗi.
        System.out.println(str.append(" writen in Java"));
//        Lúc này str đã được thay đổi sau khi thêm chuỗi, không như String là giữ nguyên String ban đầu
        System.out.println(str);
        //Phương thức insert() của lớp StringBuilder chèn chuỗi vào chuỗi này từ vị trí quy định.
        System.out.println(str.insert(9, " JavaHello"));
//        Phương thức replace() của lớp StringBuilder thay thế chuỗi bằng chuỗi khác từ vị trị bắt đầu và kết thúc được quy định.
        System.out.println(str2.replace(1, 6, "tronger"));
        System.out.println(str2);
//        Phương thức replace() của lớp StringBuilder xóa chuỗi từ vị trị bắt đầu và kết thúc được quy định
        //0,2 là xóa kí tự 0 và 1
        System.out.println(str2.delete(0,2));
        //Phương thức reverse() của lớp StringBuilder đảo ngược chuỗi hiện tại.
        System.out.println("Reverse: "+str3.reverse());
        //Phương thức capacity() của lớp StringBuilder trả về dung lượng của bộ nhớ đệm. Dung lượng mặc định của bộ nhớ đệm là 16.
        // Nếu số lượng ký tự của chuỗi tăng lên thì dung lượng được tính theo công thức (dung lượng cũ*2)+2.
        StringBuilder sb = new StringBuilder();
        System.out.println(sb.capacity());//mặc định là 16
        //Khi khởi tạo 1 stringbuilder từ 1 chuỗi rỗng thì capacity sẽ trả về length
        sb.append("Hello Viet Nam, VC CORP, VNCS2, NguyenDuyThai");
        System.out.println(sb.length());
        System.out.println(sb.capacity());//
        sb.append("java is my favourite language");
//
        System.out.println(sb.capacity());//đến đây là (16*2)+2=34 i.e (dung lượng cũ*2)+2
        //-----------------ensureCapacity
        System.out.println("Ensurecapacity CHeck");
        StringBuilder sb2 = new StringBuilder();
        System.out.println(sb2.capacity());//mặc định là 16
        sb2.append("Hello");
        System.out.println(sb2.capacity());//đến đây là 16
        sb2.append("java is my favourite language");
        System.out.println(sb2.capacity());//đến đây là (16*2)+2=34 i.e (dung lượng cũ*2)+2
        sb2.ensureCapacity(10);//đến đây không có sự thay đổi
        System.out.println(sb2.capacity());//đến đây là 34
        sb2.ensureCapacity(50);//đến đây là (34*2)+2
        System.out.println(sb2.capacity());//đến đây là 70

        //char charAt(int index): được sử dụng trả về ký tự tại vị trí quy định.

        System.out.println(sb2.charAt(29));
    }
}
