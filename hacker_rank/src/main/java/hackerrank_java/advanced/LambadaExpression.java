package hackerrank_java.advanced;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Ujjwal Gupta on Aug,2019
 */

@FunctionalInterface
interface PerformOperation {
    boolean check(int a);
}

class MyMath{

    public static boolean checker(PerformOperation p, int num) {
        return p.check(num);
    }

    public PerformOperation isOdd(){
      return n -> ((n & 1) == 1);
    }

    public PerformOperation isPalindrome(){
        return n -> {
              String org = n + "";
            String newString = new StringBuffer(org).reverse().toString();
            return org.equals(newString);
        };
    }

    public PerformOperation isPrime(){
        return n -> {
            if(n < 2){
                return false;
            }else{
                for(int i=2; i<Math.sqrt(n); i++){
                    if(n % i == 0){
                        return false;
                    }
                }
                return true;
            }
        };
    }
}
public class LambadaExpression {

    public static void main(String[] args) throws IOException {
        MyMath ob = new MyMath();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        PerformOperation op;
        boolean ret = false;
        String ans = null;
        while (T-- > 0) {
            int ch = Integer.parseInt(br.readLine());
            int num = Integer.parseInt(br.readLine());
            if (ch == 1) {
                op = ob.isOdd();
                ret = ob.checker(op, num);
                ans = (ret) ? "ODD" : "EVEN";
            } else if (ch == 2) {
                op = ob.isPrime();
                ret = ob.checker(op, num);
                ans = (ret) ? "PRIME" : "COMPOSITE";
            } else if (ch == 3) {
                op = ob.isPalindrome();
                ret = ob.checker(op, num);
                ans = (ret) ? "PALINDROME" : "NOT PALINDROME";
            }
            System.out.println(ans);
        }
    }
}
