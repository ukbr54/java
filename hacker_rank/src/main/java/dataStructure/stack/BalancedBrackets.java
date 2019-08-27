package dataStructure.stack;

import java.util.Scanner;
import java.util.Stack;

/**
 *  Algorithm :
 *   1. Create a stack.
 *   2. while(end of input is not reached)
 *     a) If the character read is not a symbol to be balanced, ignore it.
 *     b) If the character is an opening symbol like {,[,( push it into the stack.
 *     c) If it is a closing symbol like },],) then if the stack is empty report an error. Otherwise pop the stack.
 *     d) If the symbol is popped is not the corresponding symbol, report an erro.
 *  3. AT the end of output, if the stack is not empty report an error.
 *
 *  Time Complexity: O(n)
 *  Space Complexity : O(n)
 */

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
