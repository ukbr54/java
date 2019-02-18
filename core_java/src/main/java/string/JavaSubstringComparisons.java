package string;


import java.util.Arrays;
import java.util.Scanner;

public class JavaSubstringComparisons {

    public static String getSmallestAndLargest(String s,int k){
        String smallest = "";
        String largest = "";
        String[] arr = new String[s.length() - (k-1)];
        for(int i = 0; i < s.length() - (k-1);i++){
            String substring = s.substring(i, k+i);
            arr[i] = substring;
        }
        String[] sort = sort(arr);
        if(sort.length > 0){
            smallest = sort[0];
            largest = sort[sort.length - 1];
        }
        return smallest + "\n" + largest;
    }

    public static String[] sort(String[] arr){
        int length = arr.length;
        for(int i=0; i<length-1; i++){
            for(int j=0; j<length-i-1; j++){
                if(arr[j].compareTo(arr[j+1]) > 0){
                    String temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter String");
        String s = sc.next();
        System.out.println("Enter substring length");
        int k = sc.nextInt();
        sc.close();

        System.out.println(getSmallestAndLargest(s, k));
    }
}
