package com.fancyfrog;

import com.fancyfrog.utils.AbstractFuturesTest;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class S06_AllAny extends AbstractFuturesTest {

    @Test
    public void allOf(){
        final CompletableFuture<String> java = questions("java");
        final CompletableFuture<String> scala = questions("scala");
        final CompletableFuture<String> clojure = questions("clojure");
        final CompletableFuture<String> groovy = questions("groovy");

        final CompletableFuture<Void> future = CompletableFuture.allOf(java, scala, clojure, groovy);

        future.thenRun(() ->{
            try {
                System.out.println("Java: "+java.get());
                System.out.println("Scala: "+scala.get());
                System.out.println("Clojure: "+clojure.get());
                System.out.println("Groovy: "+groovy.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void anyOf(){
        final CompletableFuture<String> java = questions("java");
        final CompletableFuture<String> scala = questions("scala");
        final CompletableFuture<String> clojure = questions("clojure");
        final CompletableFuture<String> groovy = questions("groovy");

        final CompletableFuture<Object> anyFuture = CompletableFuture.anyOf(java, scala, clojure, groovy);
        anyFuture.thenAccept((Object result) ->{
            System.out.println("Any Future result: "+(String)result);
        });
    }
}
