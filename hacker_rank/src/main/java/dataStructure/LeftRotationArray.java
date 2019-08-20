package dataStructure;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Ujjwal Gupta on Aug,2019
 */
public class LeftRotationArray {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of array");
        int size = sc.nextInt();
        int[] arr = new int[size];
        System.out.println("Enter the elements of array: ");
        for(int i = 0; i < size; i++){
            arr[i] = sc.nextInt();
        }

        System.out.println("Enter the number of left rotation");
        int d = sc.nextInt();

        int[] rotArray = new int[arr.length];
        for(int oldIndex = 0; oldIndex < arr.length; oldIndex++){
            int newIndex = (oldIndex + arr.length - d) % arr.length;
            rotArray[newIndex] = arr[oldIndex];
        }

        System.out.println(Arrays.toString(rotArray).toString()
                .replaceAll(",","").replace("[","").replace("]",""));
    }
}
