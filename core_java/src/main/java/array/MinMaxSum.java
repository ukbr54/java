package array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given five positive integers, find the minimum and maximum values that can be calculated by summing exactly four of the five
 * integers. Then print the respective minimum and maximum values as a single line of two space-separated long integers.
 *
 * For example,arr=[1,3,5,7,9] . Our minimum sum is  1+3+5+7+9 = 16 and our maximum sum is 3+5+7+9=24. We would print
 *
 *
 *
 * Solution:
 * We can minimize the sum by excluding the largest element from the sum.
 * We can maximize the sum by excluding the smallest element from the sum.
 */
public class MinMaxSum {
    public static void main(String[] args) {
        int[] arr = {1,3,5,7,9};
        long min = Long.MAX_VALUE;
        long max = 0;
        long sum = 0;
        for(int i=0; i<arr.length; i++){
           long curr = arr[i];
           if(max < curr){
               max = curr;
               System.out.println("Pass: "+i+" max value:"+max);
           }
           if(min > curr){
               min = curr;
               System.out.println("Pass: "+i+" min value:"+min);
           }
           sum += curr;
        }

        long minSum = sum - min;
        long maxSum = sum - max;
        System.out.println(minSum + " " + maxSum);
    }
}
