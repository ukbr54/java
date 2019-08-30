package exception;


import java.io.Closeable;
import java.io.IOException;

/**
 * exception from the try block "wins" and the exceptions from the close() method are "suppressed".
 * In fact, you can retrieve these suppressed exceptions by calling the Throwable[] java.lang.Throwable.getSuppressed() method
 * from the exception thrown by the try block.
 */
class Connection implements Closeable{

    @Override
    public void close() throws IOException {
        throw new IOException("Close Exception");
    }
}

public class Test3 {
    public static void main(String[] args) {
        try(Connection con = new Connection()){
           throw new RuntimeException("Runtime Exception");
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}
