package algorithms.extra;

/**
 * Created by Ujjwal Gupta on Aug,2019
 */

import java.util.Arrays;
import java.util.Scanner;

/**
 *  - A normal algorithm takes O(n * m) times to perform m number of queries to find range sum on n size array.
 *  - Prefix sum algorithm takes O(n) time to perform m number of queries to find range sum on n size array.
 *
 *   It is  a simple yet powerful technique that allows to perform fast calculation on the sum of elements in a given
 *   range ( called contiguous segment of array).
 */
public class PrefixSum {

    static int[] prefixSumArray(int[] arr){
        for(int i = 1; i < arr.length; i++){
            arr[i] = arr[i] + arr[i - 1];
        }
        return arr;
    }

    static int getSumBetweenRange(int i, int j,int[] arr){
        int sum = 0;
        if(i < 1){
            sum = arr[j];
        }else{
            sum = arr[j] - arr[i - 1];
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of array");
        int size = sc.nextInt();

        int[] arr = new int[size];
        for(int i = 0; i < size; i++){
            arr[i] = sc.nextInt();
        }
        
        int[] prefixSumArray = prefixSumArray(arr);

        System.out.println("Insert the range to calculate the sum between range");
        int i = sc.nextInt();
        int j = sc.nextInt();
        System.out.println("Sum: "+getSumBetweenRange(i,j,arr));
    }
}
