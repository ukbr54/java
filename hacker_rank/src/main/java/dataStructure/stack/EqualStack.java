package dataStructure.stack;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Ujjwal Gupta on Aug,2019
 */
public class EqualStack {

    static int equalStacks(int[] h1,int[] h2,int[] h3){
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        Stack<Integer> stack3 = new Stack<>();

        int st1TotalHeight = 0,st2TotalHeight = 0,st3TotalHeight = 0;

        for(int i = h1.length - 1; i >= 0; i--){
            st1TotalHeight += h1[i];
            stack1.push(st1TotalHeight);
        }

        for(int i = h2.length - 1; i >= 0; i--){
            st2TotalHeight += h2[i];
            stack2.push(st2TotalHeight);
        }

        for(int i = h3.length - 1; i >= 0; i--){
            st3TotalHeight += h3[i];
            stack3.push(st3TotalHeight);
        }

        while(true){
            if(stack1.isEmpty() || stack2.isEmpty() || stack3.isEmpty())
                return 0;

            st1TotalHeight = stack1.peek();
            st2TotalHeight = stack2.peek();
            st3TotalHeight = stack3.peek();

            if(st1TotalHeight == st2TotalHeight && st2TotalHeight == st3TotalHeight){
                return st1TotalHeight;
            }

            if(st1TotalHeight > st2TotalHeight && st1TotalHeight > st3TotalHeight)
                stack1.pop();
            else if(st2TotalHeight > st1TotalHeight && st2TotalHeight > st3TotalHeight)
                stack2.pop();
            else if(st3TotalHeight > st1TotalHeight && stack3.peek() > st2TotalHeight)
                stack3.pop();
        }
    }

    public static void main(String[] args) {
//        int[] h1 = {3,2,1,1,1};
//        int[] h2 = {4,3,2};
//        int[] h3 = {1,1,4,1};
//        int value = equalStacks(h1, h2, h3);
//        System.out.println("Result: "+value);

        Scanner sc = new Scanner(System.in);
        int n1 = sc.nextInt();
        int n2 = sc.nextInt();
        int n3 = sc.nextInt();

        int[] h1 = new int[n1];
        for(int i = 0; i < n1; i++){
            h1[i] = sc.nextInt();
        }

        int[] h2 = new int[n2];
        for(int i = 0; i < n2; i++){
            h2[i] = sc.nextInt();
        }

        int[] h3 = new int[n3];
        for(int i = 0; i < n3; i++){
            h3[i] = sc.nextInt();
        }

        int result = equalStacks(h1, h2, h3);
        System.out.println("Result: "+result);
    }
}
