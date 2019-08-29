package hackerrank_java.advanced;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static java.lang.System.*;

/**
 * Created by Ujjwal Gupta on Aug,2019
 */

/**
 * The next question is what is the range of numbers we need to consider while checking if they are factors? Since,
 * a number is definitely not divisible by any number greater than itself, we can place n as the upper limit. We can further reduce this upper limit by noting that a number has no other factors ( except itself ) greater than sqrt(n).
 */
class Prime{

    public void checkPrime(int ... num){
        String str = "";
        for(int n : num){
            boolean found = true;
            if(3 >= n && n > 1){
                str += n + " ";
            }else{
                out.println(Math.sqrt(n));
                for (int i = 2; i <= Math.sqrt(n); i++) {
                    if (n % i == 0) {
                        found = false;
                        break;
                    }
                }
                if (found && n != 1) {
                    str += n + " ";
                }
            }
        }
        out.println(str);
    }
}

public class PrimeChecker {

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
//            int n1 = Integer.parseInt(br.readLine());
//            int n2 = Integer.parseInt(br.readLine());
//            int n3 = Integer.parseInt(br.readLine());
//            int n4 = Integer.parseInt(br.readLine());
//            int n5 = Integer.parseInt(br.readLine());
            Prime ob = new Prime();
            ob.checkPrime(2);
            ob.checkPrime(2, 1);
            ob.checkPrime(2, 1, 3);
            ob.checkPrime(2, 1, 3, 4, 5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
