package com.fancyfrog.example2;

import java.util.concurrent.CompletableFuture;

public class S08_handling_exception {

    public static void process(CompletableFuture<Integer> future){
        future
                .exceptionally(throwable -> handle(throwable))
                .thenApply(result -> result * 2)
                .thenApply(result -> result + 1)
                .thenAccept(System.out::println);
    }

    private static Integer handle(Throwable throwable) {
        System.out.println(throwable);
        return 100;
    }

    public static void main(String[] args) {

        CompletableFuture<Integer> future = new CompletableFuture<>();
        process(future);

        //when no exception is created
        //future.complete(2);

        //when exception is generated
        future.completeExceptionally(new RuntimeException("Something went wrong!"));
    }
}
