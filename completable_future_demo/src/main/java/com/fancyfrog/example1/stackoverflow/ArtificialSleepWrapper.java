package com.fancyfrog.example1.stackoverflow;

import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ArtificialSleepWrapper implements StackOverflowClient{

    private static Logger log = LoggerFactory.getLogger(ArtificialSleepWrapper.class);

    private static final Random RANDOM = new Random();

    private final StackOverflowClient target;

    public ArtificialSleepWrapper(StackOverflowClient target) {
        this.target = target;
    }

    @Override
    public String mostRecentQuestionAbout(String tag) {
        final long start = System.currentTimeMillis();
        final String result = target.mostRecentQuestionAbout(tag);
        artificialSleep(1000 - (System.currentTimeMillis() - start));
        return result;
    }

    @Override
    public Document mostRecentQuestionsAbout(String tag) {
        //artificialSleep(1000);
        final long start = System.currentTimeMillis();
        final Document result = target.mostRecentQuestionsAbout(tag);
        artificialSleep(1000 - (System.currentTimeMillis() - start));
        return result;
    }

    protected static void artificialSleep(long expected){
        try{
            double sleep = expected + RANDOM.nextGaussian() * expected / 2;
            log.debug("Sleep Time: {}",sleep);
            TimeUnit.SECONDS.sleep((long)(sleep));
        }catch (InterruptedException e){
            throw new RuntimeException();
        }
    }
}
