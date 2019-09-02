package exception;

/**
 * finally block will always execute weather the catch block containing the return statement.
 */
public class Test2 {

    protected static int m1(){
        try{
            throw new RuntimeException();
        }catch (RuntimeException e){
            return 1;
        }finally {
            return 2;
        }
    }

    public static void main(String[] args) {
        System.out.println(m1());
    }
}
