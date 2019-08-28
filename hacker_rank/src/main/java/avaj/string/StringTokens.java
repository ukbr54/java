package avaj;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class StringTokens {

    public static void main(String[] args) {
        //He is a very very good boy, isn't he?
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        s = s.trim();
        final String delim = "[, '@_.?!]+";
        if(s.length() == 0){
            System.out.println("0");
            return;
        }else{
            final String[] strings = s.split(delim);
            System.out.println(strings.length);
            for(int i = 0; i < strings.length; i++){
                System.out.println(strings[i]);
            }
        }
        final Pattern compile = Pattern.compile(s);
        scan.close();
    }
}
