package array;

public class DiagonalDifference {

    public static void main(String[] args) {
        int[][] arr = {{1,2,3},{4,5,6},{7,8,9}};

        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr.length; j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }

        System.out.println(diagonalDifference(arr));
    }

    static int diagonalDifference(int[][] arr) {
        int leftDiagonal = 0;
        int rightDiagonal = 0;

        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr.length; j++){
                if(i == j){
                    leftDiagonal += arr[i][j];
                }
                if(i == arr.length - j - 1){
                    rightDiagonal += arr[i][j];
                }
            }
        }
        return Math.abs(leftDiagonal - rightDiagonal);
    }
}
