package hackerrank_java.bigNumber;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by Ujjwal Gupta on Aug,2019
 */
public class JavaBigInteger {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BigInteger num1 = sc.nextBigInteger();
        BigInteger num2 = sc.nextBigInteger();


        System.out.println("Sum: "+num1.add(num2));
        System.out.println("Mul: "+num1.multiply(num2));
    }
}
