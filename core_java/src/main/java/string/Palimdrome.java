package string;

import java.util.List;
import java.util.stream.IntStream;

public class Palimdrome {

    public static void main(String[] args) {
        String value = "madam";
        System.out.println(value.length() / 2);
        System.out.println(isPalindrome(value));
        System.out.println(isPalindromeUsingReverse(value));
        System.out.println(isPalindromeUsingStream(value));
    }
    
    public static boolean isPalindrome(String text){
        String clean = text.replaceAll("\\s+", "").toLowerCase();
        int length = clean.length();
        int forward = 0;
        int backward = length - 1;
        while(forward < backward){
            char forwardChar = clean.charAt(forward++);
            char backwardChar = clean.charAt(backward--);
            if(forwardChar != backwardChar){
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindromeUsingReverse(String text){
        StringBuilder reverse = new StringBuilder();
        String clean = text.replaceAll("\\s+", "").toLowerCase();
        for(int i=clean.length()-1; i>=0; i--){
            reverse.append(clean.charAt(i));
        }

        if(reverse.toString().equals(clean)){
            return true;
        }
        return false;
    }

    public static boolean isPalindromeUsingStream(String text){
        String temp = text.replaceAll("\\s+", "").toLowerCase();
        //noneMatch() method returns true if none of the stream elements matches
        //         the given predicate
        return IntStream.range(0,temp.length() / 2)
                .noneMatch(i -> temp.charAt(i) != temp.charAt(temp.length()- i -1));
    }
}
