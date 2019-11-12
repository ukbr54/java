package com.fancyfrog.example2;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class S07_create_pipe_then_completing {

    public static void process(CompletableFuture<Integer> future){
      future
          .thenApply(result -> result * 2)
          .thenAccept(System.out::println);
    }

    public static void main(String[] args) {

        //empty future object
        //CompletableFuture<Integer> future = new CompletableFuture<>();

        //this form the pipeline
        //process(future);

        //take the future and complete
        //future.complete(2);
        thenCombineAsyncExample();
    }

    static void thenCombineAsyncExample() {
        String original = "Message";
        CompletableFuture<String> cf = CompletableFuture.completedFuture(original)
                .thenApplyAsync(s -> {
                    System.out.println(Thread.currentThread().getName()+ " ---------------   ");
                    return delayedUpperCase(s);
                })
                .thenCombine(CompletableFuture.completedFuture(original)
                                .thenApplyAsync(s ->{
                                    System.out.println(Thread.currentThread().getName()+ " ---------------   ");
                                    return delayedLowerCase(s);
                                }), (s1, s2) -> s1 + s2);
        System.out.println(cf.join());
    }

    private static String delayedUpperCase(String s) {
        randomSleep();
        return s.toUpperCase();
    }

    private static String delayedLowerCase(String s) {
        randomSleep();
        return s.toLowerCase();
    }

    private static void randomSleep() {
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            // ...
        }
    }
}
