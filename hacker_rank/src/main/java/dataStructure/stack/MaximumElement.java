package dataStructure.stack;

import java.util.Scanner;
import java.util.Stack;

public class MaximumElement {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        Stack<Integer> mainStack = new Stack<>();
        Stack<Integer> maxStack = new Stack<>();
        maxStack.push(Integer.MIN_VALUE);

        for(int i = 0; i < N; i++){
            int queryType = sc.nextInt();
            switch (queryType){
                case 1:
                    int item = sc.nextInt();
                    mainStack.push(item);
                    int maxSoFar = maxStack.peek();
                    maxStack.push(Math.max(maxSoFar,item));
                    break;
                case 2:
                    mainStack.pop();
                    maxStack.pop();
                    break;
                case 3:
                    System.out.println(maxStack.peek());
                    break;
            }
        }
    }
}
