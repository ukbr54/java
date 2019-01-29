package com.fancyfrog.utils;

import com.fancyfrog.stackoverflow.*;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

import java.util.concurrent.*;

public class AbstractFuturesTest {

    protected final ExecutorService service = Executors.newFixedThreadPool(10,threadFactory("Custom"));

    @Rule
    public TestName testName = new TestName();

    protected ThreadFactory threadFactory(String nameFormat){
        return new ThreadFactoryBuilder().setNameFormat(nameFormat + "-%d").build();
    }

     protected StackOverflowClient client = new FallbackStubClient(
             new InjectErrorsWrapper(new LoggingWrapper(new ArtificialSleepWrapper(new HttpStackOverflowClient())),"php"));

    @Before
    public void logTestStart() {
        System.out.println(String.format("Starting: %s", testName.getMethodName()));
    }

    @After
    public void stopPool() throws InterruptedException {
        service.shutdown();
        service.awaitTermination(10, TimeUnit.SECONDS);
    }

    protected CompletableFuture<String> questions(String tag) {
        return CompletableFuture.supplyAsync(() ->
                        client.mostRecentQuestionAbout(tag),
                service);
    }


}
