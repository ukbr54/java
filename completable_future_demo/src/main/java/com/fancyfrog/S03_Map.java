package com.fancyfrog;

import com.fancyfrog.utils.AbstractFuturesTest;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class S03_Map extends AbstractFuturesTest {

    @Test
    public void oldSchool() throws ExecutionException, InterruptedException {
        final CompletableFuture<Document> java =
                CompletableFuture.supplyAsync(() -> client.mostRecentQuestionsAbout("java"),service);

        final Document document = java.get(); //blocking
        final Element element = document.select("a.question-hyperlink").get(0);
        final String title = element.text();
        System.out.println(String.format("Found: %s", title));
    }

    /**
     * Callback hell, doesn't compose
     */
    @Test
    public void callbacksCallbacksEverywhere(){
        final CompletableFuture<Document> java =
                CompletableFuture.supplyAsync(() -> client.mostRecentQuestionsAbout("java"),service);
        java.thenAccept(document -> System.out.println("Document: "+document));
    }

    @Test
    public void thenApply() throws ExecutionException, InterruptedException {
        final CompletableFuture<String> titleFuture =
                CompletableFuture.supplyAsync(() -> client.mostRecentQuestionsAbout("java"), service)
                                  .thenApply(doc -> doc.select("a.question-hyperlink").get(0))
                                  .thenApply(Element::text);

        System.out.println(String.format("Title: %s", titleFuture.get()));

    }
}
