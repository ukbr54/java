package hackerrank_java.datastructure;

import java.util.Scanner;

public class OneDJavaArray {

    public static boolean canWin(int leap, int[] game) {
        return canWin(leap,game,0);
    }

    public static boolean canWin(int leap, int[] g,int i) {
        if(i < 0 || g[i] == 1){
            return false;
        }
        if(i + leap >= g.length || i == g.length - 1){
            return true;
        }
        g[i] = 1;
        return canWin(leap, g, i + 1) || canWin(leap, g, i + leap) || canWin(leap, g, i - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        for(int i = 0; i < q; i++){
            int n = sc.nextInt();
            int[] game = new int[n];
            int leap = sc.nextInt();
            for(int j = 0; j < n; j++){
               game[j] = sc.nextInt();
            }
            System.out.println( (canWin(leap, game)) ? "YES" : "NO" );
        }
    }
}
