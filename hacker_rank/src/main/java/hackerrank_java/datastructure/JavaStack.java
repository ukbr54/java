package hackerrank_java.datastructure;

import java.util.Scanner;
import java.util.Stack;

public class JavaStack{
    public static boolean isBalanced(String input){
        Stack<Character> stack = new Stack<>();
        if(input == null || input.length() == 0){
            return true;
        }else{
            for(int i = 0; i < input.length(); i++){
                if(input.charAt(i) == ')'){
                    if(!stack.isEmpty() && stack.peek() == '('){
                        stack.pop();
                    }else{
                        return false;
                    }
                }else if(input.charAt(i) == '}'){
                    if(!stack.isEmpty() && stack.peek() == '{'){
                        stack.pop();
                    }else{
                        return false;
                    }
                }else if(input.charAt(i) == ']'){
                    if(!stack.isEmpty() && stack.peek() == '['){
                        stack.pop();
                    }else{
                        return false;
                    }
                }else{
                    stack.push(input.charAt(i));
                }
            }

            if(stack.isEmpty()){
                return true;
            }else{
                return false;
            }
        }

    }

    public static void main(String []argh)
    {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String input=sc.next();
            System.out.println(isBalanced(input));
        }
    }
}