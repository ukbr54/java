package com.fancyfrog.example1;

import com.fancyfrog.example1.utils.AbstractFuturesTest;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/*
  This class demonstrate the creation of CompletableFuture
 */
public class S02_Creating extends AbstractFuturesTest {

    /**
     * Already completed future
     */
    @Test
    public void completed() throws ExecutionException, InterruptedException {
        final CompletableFuture<Integer> answer = CompletableFuture.completedFuture(42);
        final Integer fortyTwo = answer.get();
    }

    /**
     * Built-in thread pool
     */
    @Test
    public void supplyAsync() throws ExecutionException, InterruptedException {
        final CompletableFuture<String> java =
                CompletableFuture.supplyAsync(() -> client.mostRecentQuestionAbout("java"));
        System.out.println(String.format("Found: %s",java.get()));
    }

    /**
     * Custom thread pool, equivalent (*) to submit()
     */
    @Test
    public void supplyAsyncWithCustomExecutor() throws ExecutionException, InterruptedException {
        CompletableFuture<String> java = CompletableFuture.supplyAsync(() -> client.mostRecentQuestionAbout("java"), service);
        System.out.println(String.format("Found: %s", java.get()));
    }
}
