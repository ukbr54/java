package com.fancyfrog.example2;

import java.util.concurrent.CompletableFuture;

public class S03_using_thenAccept {

    public static void main(String[] args) {
        final CompletableFuture<Integer> task = startAsyncTask(2);

        sleep(1000);

        task.thenAccept(result -> System.out.println(result + " : " + Thread.currentThread()));
        //when main hits this line, if the task is completed, then the execution of the
        //lambda runs in the main thread. However, when main hits this line, if the task is not
        //completed, then the lambda may run into a different thread. Non-blocking.

        //A stage that is being attached to a resolved or rejected CompletableFuture complete in the
        //calling thread. However, a state that is being attached to a pending CompletableFuture will
        //run eventually when that resolves or rejects in the other thread.

        sleep(3000);
    }

    private static CompletableFuture<Integer> startAsyncTask(int number){
        return CompletableFuture.supplyAsync(() ->{
            if(Math.random() > 0.75){
                System.out.println("taking a slow run this time");
                sleep(2000);
            }
            return number * 2;
        });
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
