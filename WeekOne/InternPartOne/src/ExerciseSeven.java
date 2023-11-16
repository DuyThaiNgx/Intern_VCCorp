import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExerciseSeven {
    public static void main(String[] args) {
        String regex = "^(https?://)?(www\\.)?[a-zA-Z0-9-]+(\\.[a-zA-Z]{2,6}){1}(/[^\\s]*)?$";
        Pattern pattern = Pattern.compile(regex);
        String url = "https://www.facebook.com/";
        Matcher matcher = pattern.matcher(url);
        if(matcher.matches()){
            System.out.println("URL is valid");
        }
        else{
            System.out.println("URL is unvalid");
        }
    }
}
