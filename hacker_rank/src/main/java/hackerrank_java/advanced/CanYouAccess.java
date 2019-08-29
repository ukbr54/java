package hackerrank_java.advanced;

import java.util.Scanner;

/**
 * Created by Ujjwal Gupta on Aug,2019
 */
public class CanYouAccess {

    public static void main(String[] args) {
        try{
            Scanner sc = new Scanner(System.in);
            int num = sc.nextInt();

            Object o;

            CanYouAccess.Inner a = new CanYouAccess.Inner();

            o = a.new Private();

            System.out.println(num +" is " + ((CanYouAccess.Inner.Private)o).powerof2(num));

            System.out.println("An instance of class: " + o.getClass().getCanonicalName() + " has been created");
        }catch (Exception e){

        }
    }

    static class Inner{
        private class Private{
            private String powerof2(int num){
                return ((num&num-1)==0)?"power of 2":"not a power of 2";
            }
        }
    }//end of Inner
}
