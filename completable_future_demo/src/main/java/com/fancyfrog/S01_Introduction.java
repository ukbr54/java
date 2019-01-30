package com.fancyfrog;

import com.fancyfrog.utils.AbstractFuturesTest;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class S01_Introduction extends AbstractFuturesTest {

    @Test
    public void blockingCall(){
        final String title = client.mostRecentQuestionAbout("java");
        System.out.println(String.format("Most recent Java question: %s", title));
    }

    @Test
    public void executorService() throws ExecutionException, InterruptedException {
        final Callable<String> task = () -> client.mostRecentQuestionAbout("java");
        Future<String> javaQuestionFuture = service.submit(task);
        //blocking operation
        final String javaQuestion = javaQuestionFuture.get();
        System.out.println(String.format("Found : %s", javaQuestion));
    }
}
