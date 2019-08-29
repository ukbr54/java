package bigdecimal;

import java.math.BigDecimal;

/**
 * Created by Ujjwal Gupta on Aug,2019
 */
/**
 * The CompareTo method works in the same way as natural ordering. Number a is just greater than others due to a different constructor.
 *
 */
public class Demo1 {

    public static void main(String[] args) {
        BigDecimal a = new BigDecimal(0.2d);
        BigDecimal b = new BigDecimal(String.valueOf(0.2d));
        BigDecimal c = new BigDecimal("0.2");
        BigDecimal d = new BigDecimal("0.20");

        System.out.println("a: "+a);
        System.out.println("b: "+b);
        System.out.println("c: "+c);
        System.out.println("d: "+d);


        System.out.println(a.equals(b));    // false
        System.out.println(a.equals(c));    // false
        System.out.println(a.equals(d));    // false
        System.out.println(b.equals(c));    // true
        System.out.println(b.equals(d));    // false
        System.out.println(c.equals(d));    // false
        System.out.println(a.compareTo(b)); // 1
        System.out.println(a.compareTo(c)); // 1
        System.out.println(a.compareTo(d)); // 1
        System.out.println(b.compareTo(c)); // 0
        System.out.println(b.compareTo(d)); // 0
        System.out.println(c.compareTo(d)); //0
    }
}
