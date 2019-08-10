package algorithms;

import java.util.Scanner;

/**
 * Given five positive integers, find the minimum and maximum values that can be calculated by summing exactly four of the five integers.
 * Then print the respective minimum and maximum values as a single line of two space-separated long integers.
 *
 * For example,arr = [1,3,5,7,9] . Our minimum sum is 1 + 3 + 5 + 7 = 16  and our maximum sum is 3 + 5 + 7 + 9 = 24.
 */
public class MinMaxSum {

    private static void minMaxSum(int[] arr){
        long min = Long.MAX_VALUE;
        long max = 0;
        long sum = 0;

        for(int i=0; i<arr.length; i++ ){
            long curr = arr[i];
            if(curr > max){
                max = curr;
            }
            if(min > curr){
                min = curr;
            }
            sum = sum + curr;
        }
        long minSum = sum - min;
        long maxSum = sum - max;

        System.out.println("Maximum: " + maxSum + " Minimum: "+minSum);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of array: ");
        int size = sc.nextInt();
        int[] arr = new int[size];
        System.out.println("Insert the "+size+ " element in the array");
        for(int i=0; i<size; i++){
            arr[i] = sc.nextInt();
        }
        minMaxSum(arr);
    }
}
