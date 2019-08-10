package algorithms;


import java.util.Scanner;

/**
 *  Observe that its base and height are both equal to , and the image is drawn using # symbols and spaces.
 *  The last line is not preceded by any spaces.
 *
 * Write a program that prints a staircase of size .
 *          #
 *         ##
 *        ###
 *       ####
 */
public class Staircase {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Number of Lines");
        int n = sc.nextInt();
        for(int R=1; R<=n; R++){ // Number Of Lines
            for(int S=1; S<n-R+1; S++){ // For Spaces
                System.out.print(" ");
            }
            for(int C=1; C<=R ;C++){
                System.out.print("#");
            }
            System.out.println();
        }
    }
}
