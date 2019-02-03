package com.fancyfrog.example2;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Supplier;

public class S05_using_thenAccept_apply_and_run {

    private static ForkJoinPool fjp;

    public static void main(String[] args) {
        fjp = new ForkJoinPool(100);

        final CompletableFuture<Integer> task = startAsyncTask(2);

        sleep(100);

        task
          .thenApply(result -> result * 2.2)
          .thenAccept(result -> System.out.println(result + " : " +Thread.currentThread()))
          .thenRun(() -> System.out.println("Let's celebrate"));

        sleep(3000);
        fjp.shutdown();
    }

    private static CompletableFuture<Integer> startAsyncTask(int number){
        Supplier<Integer> doubleValue = () ->{
           System.out.println("running in thread "+Thread.currentThread());
           if(Math.random() > 0.75){
               System.out.println("taking a slow run this time");
               sleep(2000);
           }

           return number * 2;
        };

        if(Math.random() > 0.5){
           System.out.println("Using custom pool");
           return CompletableFuture.supplyAsync(doubleValue);
        }else{
            System.out.println("Using common pool");
            return CompletableFuture.supplyAsync(doubleValue);
        }
    }

    private static boolean sleep(int ms){
        try{
            Thread.sleep(ms);
            return true;
        }catch (InterruptedException e){
            return false;
        }
    }
}
