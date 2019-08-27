package hackerrank_java.datastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class JavaList {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < N; i++){
            list.add(sc.nextInt());
        }
        int Q = sc.nextInt();
        for(int i = 0; i < Q; i++){
            String queryType = sc.next();
            if("Insert".equals(queryType)){
                int index = sc.nextInt();
                int data = sc.nextInt();
                list.add(index,data);
            }else if("Delete".equals(queryType)){
                int index = sc.nextInt();
                list.remove(index);
            }
        }
        for(Integer i : list){
            System.out.print(i + " ");
        }
    }
}
