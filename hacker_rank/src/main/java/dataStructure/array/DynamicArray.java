package dataStructure.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Ujjwal Gupta on Aug,2019
 */

public class DynamicArray {

//    List<Integer> seq = new ArrayList<>();
//    List<List<Integer>> seqList = new ArrayList<List<Integer>>();
//    int lastAnswer = 0;
//
//    /**
//     * Create a list, seqList, of N empty sequences, where each sequence is indexed from 0 to N-1.
//     * The elements within each of the N sequences also use 0-indexing.
//     */
//    public DynamicArray(int N){
//        for(int i = 0; i < N; i++){
//            seq = new ArrayList<>();
//            seqList.add(seq);
//        }
//    }
//
//    private void append(int x, int y, int N) {
//       int rowIndex = ((x ^ this.lastAnswer) % N);
//        List<Integer> seq = this.seqList.get(rowIndex);
//        seq.add(y);
//    }
//
//    private void printValue(int x, int y, int N) {
//        int rowIndex = ((x ^ this.lastAnswer) % N);
//        List<Integer> seq = this.seqList.get(rowIndex);
//        int colIndex = y % seq.size();
//        lastAnswer = seq.get(colIndex);
//        System.out.println(lastAnswer);
//    }

    List<Integer> seq = new ArrayList<>();
    List<List<Integer>> seqList = new ArrayList<List<Integer>>();
    int lastAnswer = 0;

    public static List<Integer> dynamicArray(int n, List<List<Integer>> queries) {
        DynamicArray da = new DynamicArray();
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < n; i++){
            da.seq = new ArrayList<>();
            da.seqList.add(da.seq);
        }

        for (List<Integer> list : queries) {
            int queryType = list.get(0);
            int x = list.get(1);
            int y = list.get(2);

            if(queryType == 1){
                int rowIndex = ((x ^ da.lastAnswer) % n);
                da.seq = da.seqList.get(rowIndex); // points to list of integer.
                da.seq.add(y);
            }else{
                int rowIndex = ((x ^ da.lastAnswer) % n);
                da.seq = da.seqList.get(rowIndex);
                int colIndex = y % da.seq.size();
                da.lastAnswer = da.seq.get(colIndex);
                result.add(da.lastAnswer);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 2;
        List<List<Integer>> queries = new ArrayList<List<Integer>>();
        List<Integer> query1 = Arrays.asList(1,0,5);
        List<Integer> query2 = Arrays.asList(1,1,7);
        List<Integer> query3 = Arrays.asList(1,0,3);
        List<Integer> query4 = Arrays.asList(2,1,0);
        List<Integer> query5 = Arrays.asList(2,1,1);

        queries.addAll(Arrays.asList(query1,query2,query3,query4,query5));

        List<Integer> integers = dynamicArray(n, queries);
        System.out.println(integers);

//        Scanner sc = new Scanner(System.in);
//        int N = sc.nextInt();
//        int Q = sc.nextInt();
//        //DynamicArray dynamicArray = new DynamicArray(N);
//        for(int i = 0; i < Q; i++){
//            int queryType = sc.nextInt();
//            int x = sc.nextInt();
//            int y = sc.nextInt();
////            if(queryType == 1){
////                dynamicArray.append(x,y,N);
////            }else{
////                dynamicArray.printValue(x,y,N);
////            }
//        }
    }
}
