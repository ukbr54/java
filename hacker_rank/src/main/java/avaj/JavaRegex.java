package avaj;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class JavaRegex {

    public static void main(String[] args) {
        String ipAddress = "23";
        try{
           String pattern = "\\d+";
            System.out.println(ipAddress.matches(pattern));
        }catch (PatternSyntaxException e){
            System.err.println(e.getDescription());
        }
    }
}
