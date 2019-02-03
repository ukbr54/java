package com.fancyfrog.example2;

import java.util.concurrent.CompletableFuture;

public class S07_create_pipe_then_completing {

    public static void process(CompletableFuture<Integer> future){
      future
          .thenApply(result -> result * 2)
          .thenAccept(System.out::println);
    }

    public static void main(String[] args) {

        //empty future object
        CompletableFuture<Integer> future = new CompletableFuture<>();

        //this form the pipeline
        process(future);

        //take the future and complete
        future.complete(2);
    }
}
