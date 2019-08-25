package dataStructure.array;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by Ujjwal Gupta on Aug,2019
 */
public class ArrayManipulation {

    private static  void arrayManipulation(int n,int[][] queries){
        long[] result = new long[n + 1];
        for (int i = 0; i < queries.length; i++){ //No of queries O(m)
            int a = queries[i][0];
            int b = queries[i][1];
            int k = queries[i][2];

            for(int j = a ; j < b; j++){
                result[j] = result [j] + k;  // Adding the element taking O(n)
            }
        }
        System.out.println("Max: "+ Arrays.stream(result).max().getAsLong()); // find the maximum element in the array O(n)
        //So the time complexity is O(nm) + O(n) = O(nm)
    }

    private static long arrayManipulationUsingPrefixSum(int n,int[][] queries){
        long[] result = new long[n + 2];
        for (int i = 0; i < queries.length; i++){
            int a = queries[i][0];
            int b = queries[i][1];
            int k = queries[i][2];

            result[a] += k;
            result[b + 1] -= k;
        }
        long max = getMax(result);
        return max;
    }

    private static long getMax(long[] inputArray){
        long max = Long.MIN_VALUE;
        long sum = 0;
        for(int i = 0; i < inputArray.length; i++){
            sum += inputArray[i];
            max = Math.max(max,sum);
        }
        return max;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 5;
        int m = 3;
        int[][] queries = new int[m][3];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < queries[i].length; j++){
                queries[i][j] = sc.nextInt();
            }
        }
        long sum = arrayManipulationUsingPrefixSum(n, queries);
        System.out.println(sum);
    }
}
