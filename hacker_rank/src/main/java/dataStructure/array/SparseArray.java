package dataStructure.array;

import java.util.*;

/**
 * Created by Ujjwal Gupta on Aug,2019
 */
public class SparseArray {

    /**
     *  Here the total time is O(NQ)
     * @param strings
     * @param queries
     */
    public static void matchingString(String[] strings,String[] queries){
        Map<String,Integer> map = new LinkedHashMap<>();

        for(int i = 0; i < queries.length; i++){ // n
            String query = queries[i];
            int sum = 0;
            map.put(query,sum);
            for(int j = 0; j < strings.length; j++){  //n
                String string = strings[j];

                if(query.equals(string)){
                    Integer value = map.get(query);
                    map.put(query,++value);
                }
            }
        }
        System.out.println(map);
    }

    /**
     *  O(N + Q)
     * @param strings
     * @param queries
     * @return
     */
    public static int[] matchingStringOptimized(String[] strings,String[] queries){
        int[] result = new int[queries.length];
        Map<String,Integer> map = new HashMap<>();
        for(int i = 0; i < strings.length; i++){
            Integer value = map.get(strings[i]);
            if(map.containsKey(strings[i])){
                map.put(strings[i],++value);
            }else{
                map.put(strings[i],1);
            }
        }
        for(int i = 0; i < queries.length; i++){
            String queryString = queries[i];
            if(map.containsKey(queryString)){
                result[i] = map.get(queryString);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int noOfStrings = sc.nextInt();

        String[] strArr = new String[noOfStrings];
        for(int i = 0; i < noOfStrings; i++){
            strArr[i] = sc.next();
        }

        int noofQueries = sc.nextInt();
        String[] inputs = new String[noofQueries];
        for(int i = 0; i < noofQueries; i++){
            inputs[i] = sc.next();
        }
        //matchingString(strArr,inputs);
        int[] ints = matchingStringOptimized(strArr, inputs);
        System.out.println(Arrays.toString(ints));
    }
}
