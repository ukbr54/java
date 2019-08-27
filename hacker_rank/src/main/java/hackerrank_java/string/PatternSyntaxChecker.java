package hackerrank_java.string;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class PatternSyntaxChecker {

    public static void main(String[] args) {
        String[]  arr = { "([A-Z])(.+)" , "[AZ[a-z](a-z)" ,"batcatpat(nat" };
        for(int i = 0; i < arr.length; i++){
            try{
                final Pattern compile = Pattern.compile(arr[i]);
                System.out.println("Valid");
            }catch (PatternSyntaxException e){
                System.out.println("Invalid");
            }
        }
    }
}
