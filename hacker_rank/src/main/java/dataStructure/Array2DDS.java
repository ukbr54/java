package dataStructure;

import java.util.Scanner;

/**
 * Created by Ujjwal Gupta on Aug,2019
 */
public class Array2DDS {

    static void hourglasses(int[][] arr){
        int maxSum = Integer.MIN_VALUE;
        for(int row = 0; row < 6; row++){
            for(int col = 0; col < 6; col++){
                if((row + 2) < 6 && (col + 2) < 6){
                    int sum = arr[row][col] + arr[row][col + 1] + arr[row][col + 2] + arr[row + 1][col + 1]
                            + arr[row + 2][col] + arr[row + 2][col + 1] + arr[row + 2][col + 2];

                    if(sum > maxSum) maxSum = sum;
                }
            }
        }
        System.out.println(maxSum);
    }

    public static void main(String[] args) {
        int[][] tempArr = {
                            {1,1,1,0,0,0},
                            {0,1,0,0,0,0},
                            {1,1,1,0,0,0},
                            {0,0,2,4,4,0},
                            {0,0,0,2,0,0},
                            {0,0,1,2,4,0}
                          };
        hourglasses(tempArr);
    }
}
