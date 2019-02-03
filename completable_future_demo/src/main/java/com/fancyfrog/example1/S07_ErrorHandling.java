package com.fancyfrog.example1;

import com.fancyfrog.example1.utils.AbstractFuturesTest;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class S07_ErrorHandling extends AbstractFuturesTest {

    @Test
    public void exceptionsShortCircuitFuture() {
        final CompletableFuture<String> exception = createException();

        //this part of code is not executed if the exception is generated in the future
        exception.thenApply(s -> {
            System.out.println(s);
            return s;
        });
    }

    @Test
    public void handleExceptions() throws ExecutionException, InterruptedException {
        final CompletableFuture<String> questions = questions("php");

        final CompletableFuture<String> recovered = questions.handle((result, throwable) -> {
            if (throwable != null) {
                return "No PHP today due to: " + throwable;
            } else {
                return result.toUpperCase();
            }
        });

        System.out.println(recovered.get());
    }

    @Test
    public void shouldHandleExceptionally() throws ExecutionException, InterruptedException {
        final CompletableFuture<String> questions = questions("php");

        final CompletableFuture<String> recovered = questions
                .exceptionally(throwable -> "Sorry, try again later");

        System.out.println("Done: "+recovered.get());
    }

    private CompletableFuture<String> createException(){
        int i = 10/0;
        return CompletableFuture.completedFuture("Hello World");
    }
}
