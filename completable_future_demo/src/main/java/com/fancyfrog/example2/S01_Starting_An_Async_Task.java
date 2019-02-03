package com.fancyfrog.example2;

import java.util.concurrent.CompletableFuture;

public class S01_Starting_An_Async_Task {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("In Start Of Main "+Thread.currentThread());

        startAsyncTask();//this is non-blocking

        System.out.println("At the end of main "+Thread.currentThread());

        Thread.sleep(1000);
    }

    private static void startAsyncTask(){
        CompletableFuture.runAsync(() ->
                System.out.println("running a little task " + Thread.currentThread()));
    }
}
