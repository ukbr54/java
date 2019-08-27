package hackerrank_java.string;

import java.util.Arrays;
import java.util.Scanner;

public class SubstringComparison {

    public static String getSmallestAndLargest(String s, int k) {
        String smallest = "";
        String largest = "";
        String[] arr = new String[s.length() - (k-1)];
        for(int i = 0; i < (s.length() - (k-1)); i++){
            arr[i] = s.substring(i,i+k);
        }
        Arrays.sort(arr);
        if(arr.length > 0){
            smallest = arr[0];
            largest = arr[arr.length - 1];
        }
        return smallest + "\n" + largest;
    }



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        int size = sc.nextInt();
        final String smallestAndLargest = getSmallestAndLargest(input, size);
        System.out.println(smallestAndLargest);
    }
}
