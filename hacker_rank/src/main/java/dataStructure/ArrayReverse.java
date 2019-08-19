package dataStructure;

/**
 * Created by Ujjwal Gupta on Aug,2019
 */

import java.util.Arrays;
import java.util.Scanner;

/**
 *  1. Swap the first element with the last one.
 *  2. Do the same for others.
 */
public class ArrayReverse {

    static int[] reverse(int[] arr){
        for(int i = 0; i < arr.length / 2; i++){
            int temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i -1] = temp;
        }
        return arr;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of array");
        int size = sc.nextInt();
        int [] arr = new int[size];
        System.out.println("Enter the elements in the array");
        for(int i = 0; i < size; i++){
            arr[i] = sc.nextInt();
        }
        System.out.println(Arrays.toString(reverse(arr)));
    }
}
