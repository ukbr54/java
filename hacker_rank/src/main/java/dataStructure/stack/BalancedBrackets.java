package dataStructure.stack;

import java.util.Scanner;
import java.util.Stack;

public class BalancedBrackets {

    static String isBalanced(String s) {
        Stack<Character> stack = new Stack<>();
        if(s == null || s.length() == 0){
           return "YES";
        }else{
           for(int i = 0; i < s.length(); i++){
               if(s.charAt(i) == ')'){
                   if(!stack.isEmpty() && stack.peek() == '('){
                       stack.pop();
                   }else{
                       return "NO";
                   }
               }else if(s.charAt(i) == ']'){
                   if(!stack.isEmpty() && stack.peek() == '['){
                       stack.pop();
                   }else{
                       return "NO";
                   }
               }else if(s.charAt(i) == '}'){
                   if(!stack.isEmpty() && stack.peek() == '{'){
                       stack.pop();
                   }else{
                       return "NO";
                   }
               }else{
                   stack.push(s.charAt(i));
               }
           }
           return stack.isEmpty() ? "YES" : "NO";
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for(int i = 0; i < n; i++){
            String s = sc.nextLine();
            System.out.println(isBalanced(s));
        }
    }

}
