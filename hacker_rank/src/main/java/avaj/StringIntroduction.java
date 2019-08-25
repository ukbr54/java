package avaj;

import java.util.Scanner;

/**
 * Given two strings of lowercase English letters, A and B, perform the following operations:
 *
 * Sum the lengths of A and B.
 * Determine if A is lexicographically larger than B(i.e.: does B come before A in the dictionary?).
 * Capitalize the first letter in A and B and print them on a single line, separated by a space.
 */
public class StringIntroduction {

    static int lengthOfString(String A, String B){
        return A.length() + B.length();
    }

    static String compareTwoString(String A,String B){
        //the value 0 if the argument string is equal to this string; a value less than 0
        // if this string is lexicographically less than the string argument; and a value greater than 0
        // if this string is lexicographically greater than the string argument.
        if(A.compareTo(B) > 0){
            return "Yes";
        }else{
            return "No";
        }
    }

    static String capitalizeFirstLetter(String s){
        return s.substring(0,1).toUpperCase() + s.substring(1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String A = sc.next();
        String B = sc.next();
        System.out.println("Length of " + A + " and " + B +" is: "+(lengthOfString(A,B)));
        System.out.println("if A is lexicographically larger than B "+(compareTwoString(A,B)));
        System.out.println("A : "+capitalizeFirstLetter(A) + " B: " +capitalizeFirstLetter(B));
    }
}
