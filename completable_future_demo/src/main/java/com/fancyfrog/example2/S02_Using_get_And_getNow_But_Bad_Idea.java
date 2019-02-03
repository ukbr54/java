package com.fancyfrog.example2;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class S02_Using_get_And_getNow_But_Bad_Idea {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println("In start of main " + Thread.currentThread());

        final CompletableFuture<Integer> resultFuture = startAsyncTask(2);

        System.out.println("At the end of main " + Thread.currentThread());

        sleep(1000);

        System.out.println(resultFuture.getNow(0)); //bad idea, blocking call and why bother.
        //getNow is a little bit better than get, but they both are bad.

        System.out.println(resultFuture.get()); //bad idea, blocking call and why bother.
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
