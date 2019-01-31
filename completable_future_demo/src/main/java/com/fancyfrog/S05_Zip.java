package com.fancyfrog;

import com.fancyfrog.utils.AbstractFuturesTest;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class S05_Zip extends AbstractFuturesTest {

    @Test
    public void thenCombine(){
        final CompletableFuture<String> java = questions("java");
        final CompletableFuture<String> scala = questions("scala");

        final CompletableFuture<Integer> both =
                java.thenCombine(scala, (String javaTitle, String scalaTitle) -> javaTitle.length() + scalaTitle.length());
        both.thenAccept(System.out::println);
    }

    @Test
    public void applyToEither() throws ExecutionException, InterruptedException {
        final CompletableFuture<String> java = questions("java");
        final CompletableFuture<String> scala = questions("scala");

        final CompletableFuture<String> firstDone =
                java.applyToEither(scala, title -> title.toUpperCase());

        System.out.println(firstDone.get());
    }
}
