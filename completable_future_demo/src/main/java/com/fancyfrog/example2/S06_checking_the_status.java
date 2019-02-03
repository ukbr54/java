package com.fancyfrog.example2;

import java.util.concurrent.CompletableFuture;

public class S06_checking_the_status {

    public static void main(String[] args) throws InterruptedException {

        final CompletableFuture<Integer> task = createTask();

        Thread.sleep(1000);

        System.out.println(task.isDone());
        System.out.println(task.isCancelled());
        System.out.println(task.isCompletedExceptionally());
    }

    private static CompletableFuture<Integer> createTask(){
        //return CompletableFuture.supplyAsync(() -> 2);
        return CompletableFuture.supplyAsync(() -> {throw new RuntimeException("oops");});
    }
}
