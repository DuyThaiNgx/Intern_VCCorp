import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExerciseSeven {
    public static void main(String[] args) {
        String regex1 = "^https?://(www.)?[a-zA-Z0-9-]+(\\\\.[a-z]{2,6})(/[^\\\\s]*)?$";
        String regex = "^https?://(www.)?[a-zA-Z0-9-]+(\\.[a-z]{2,6})(/[^\\s]*)?$";
        String regexCheck = "[(http(s)?):\\/\\/(www\\.)?a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)";

        Pattern pattern = Pattern.compile(regex1);
        String url = "http://tiki.vn/dien-thoai-may-tinh-bang/c1789?src=mega-menu";
        System.out.println(url.matches(regexCheck));
        Matcher matcher = pattern.matcher(url);
//        System.out.println(matcher.matches());
//        if(matcher.matches()){
//            System.out.println("URL is valid");
//        }
//        else{
//            System.out.println("URL is unvalid");
//        }

        String text2 = "2/12/2018";
        String text3 = "12/12/aaaa";
        pattern = Pattern.compile("^\\d{1,2}[-|/]\\d{1,2}[-|/]\\d{4}$");
        System.out.println("\nChuỗi " + text2 + " có định dạng ngày tháng: "
                + pattern.matcher(text2).matches());

        System.out.println("Chuỗi " + text3 + " có định dạng ngày tháng: "
                + pattern.matcher(text3).matches());
        //(pattern.matcher(text3).matches()
        // bằng với Matcher matcher = pattern.matcher(url) sau đó matcher.matches() tại sao lại khác nhau
        //



//
    }
}
