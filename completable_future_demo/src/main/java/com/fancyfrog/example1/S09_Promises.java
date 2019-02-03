package com.fancyfrog.example1;

import com.fancyfrog.example1.utils.AbstractFuturesTest;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.time.Duration;
import java.util.concurrent.*;

public class S09_Promises extends AbstractFuturesTest {

    private static final ScheduledExecutorService pool =
            Executors.newScheduledThreadPool(10,
                                              new ThreadFactoryBuilder()
                                              .setDaemon(true)
                                              .setNameFormat("FutureOps-%d")
                                              .build()
            );

    public static <T>CompletableFuture<T> timeoutAfter(Duration duration){
        final CompletableFuture<T> promise = new CompletableFuture<>();
        pool.schedule(
                () -> promise.completeExceptionally(new TimeoutException()),
                duration.toMillis(),
                TimeUnit.MILLISECONDS
        );
        return promise;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final CompletableFuture<String> future = realLogic();
        final CompletableFuture<String> timeout = timeoutAfter(Duration.ofSeconds(2));

        //this will block the future for 2 second to get computation after that timeout
        //future.get(2,TimeUnit.SECONDS);

        // Here two completable future is running in non blocking manner,so which is complete first gives the result
        final CompletableFuture<String> result =
                future.applyToEither(timeout, str -> str.toUpperCase())
                      .exceptionally((throwable -> "Sorry, Time out, try again later"));
        System.out.println("Final Result: "+result.get());
    }

    //suppose call database
    private static CompletableFuture<String> realLogic(){
        return CompletableFuture.supplyAsync(() ->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Data retreived from database";
        });
    }
}
