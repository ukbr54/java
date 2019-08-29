package hackerrank_java.bigNumber;

import java.math.BigInteger;

/**
 * Created by Ujjwal Gupta on Aug,2019
 */

/**
 * The isProbablePrime() method of Java BigInteger class is used to determine if the given number is prime or not.
 * For certainty =1, this method returns true if this BigInteger is prime and false if this BigInteger is composite.
 */
public class PrimeTest {

    public static void main(String[] args) {
        BigInteger num1 = new BigInteger("13");
        boolean prime = num1.isProbablePrime(1);
        System.out.println(prime == true ? "Prime" : "Composite");
    }
}
