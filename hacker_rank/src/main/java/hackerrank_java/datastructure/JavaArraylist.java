package hackerrank_java.datastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JavaArraylist {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<List<Integer>> listOfList = new ArrayList<List<Integer>>();
        int n = sc.nextInt();
        for(int i = 0; i < n; i++){
            List<Integer> list = new ArrayList<>();
            int d = sc.nextInt();
            for(int j = 0; j < d; j++){
                list.add(sc.nextInt());
            }
            listOfList.add(list);
        }

        System.out.println(listOfList);

        int q = sc.nextInt();
        for(int i = 0; i < q; i++){
           int x = sc.nextInt();
           int y = sc.nextInt();
           try{
               final Integer integer = listOfList.get(x - 1).get(y - 1);
               System.out.println(integer);
           }catch (Exception e){
               System.out.println("ERROR!");
           }
        }
    }
}
