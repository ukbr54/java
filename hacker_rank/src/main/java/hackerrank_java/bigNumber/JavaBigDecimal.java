package hackerrank_java.bigNumber;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Ujjwal Gupta on Aug,2019
 */
public class JavaBigDecimal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<BigDecimal> numbers = new ArrayList<>();
        String[] s = new String[n];

        for(int i=0; i<n; i++){
            s[i] = sc.next();
        }

        Arrays.sort(s,0,n, Collections.reverseOrder((String s1,String s2) -> {
            BigDecimal a = new BigDecimal(s1);
            BigDecimal b = new BigDecimal(s2);
            return a.compareTo(b);
        }));

        for(int i=0; i<s.length; i++){
            System.out.println(s[i]);
        }
    }
}
