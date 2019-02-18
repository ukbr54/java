package sorting;

import java.util.Arrays;
import java.util.Scanner;

public class BubbleSort {

    public static void main(String[] args) {
        int [] arr = {1,4,2,5,8,9};
        bubbleSort(arr);
    }

    public static void bubbleSort(int[] arr){
        int length = arr.length;
        for(int i=0; i<length-1; i++){
            for(int j=0; j<length-i-1; j++){
                if(arr[j] > arr[j+1]){
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
