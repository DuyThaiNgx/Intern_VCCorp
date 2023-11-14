import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexSyntax {
    public static void main(String[] args) {
        String text1 = "Hello java regex 2-12-2018, hello world 12/12/2018";
        Pattern pattern = Pattern.compile("\\d{1,2}[-|/]\\d{1,2}[-|/]\\d{4}");
        Matcher matcher = pattern.matcher(text1);
        System.out.println(text1);
//        while (matcher.find()) {
//            System.out.println(text1.substring(17,26));
//            System.out.println(matcher.start());
//            System.out.println(matcher.end());
//        }
        if(matcher.find()){
            System.out.println(matcher.start());
            System.out.println(matcher.end());
            System.out.println("Fit 1: "+ text1.substring(17,26));
            System.out.println(Pattern.matches("[abcde]", "b"));
            String text2 = "Hellossss";
            String text3 = "20182";
            System.out.println(text3.matches("\\d{4}"));
//            System.out.println(text2.matches("\\w+s{1}"));

        }
    }
}
