package com.fancyfrog.example1;

import com.fancyfrog.example1.stackoverflow.Question;
import com.fancyfrog.example1.utils.AbstractFuturesTest;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

public class S04_FlatMap extends AbstractFuturesTest {

    @Test
    public void thenApplyIsWrong(){
        CompletableFuture<CompletableFuture<Question>> nestedCompletableFuture =
                javaQuestion().thenApply(doc -> findMostInterestingQuestion(doc));
    }

    @Test
    public void thenAccesptIsPoor(){
        javaQuestion().thenAccept(doc ->{
           findMostInterestingQuestion(doc).thenAccept(question -> {
              googleAnswer(question).thenAccept(answer ->{
                  postAnswer(answer).thenAccept(status ->{
                     if(status == 200){
                         System.out.println("Ok");
                     }else{
                         System.out.println(String.format("Wrong status code: %s", status));
                     }
                  });
              }) ;
           });
        });
    }

    @Test
    public void thenCompose(){
        javaQuestion()
                .thenCompose(doc -> findMostInterestingQuestion(doc))
                .thenCompose(question -> googleAnswer(question))
                .thenCompose(answer -> postAnswer(answer))
                .thenAccept(status ->{
                    if(status == 200){
                        System.out.println("Ok");
                    }else{
                        System.out.println(String.format("Wrong status code: %s", status));
                    }
                });
    }

    private CompletableFuture<Document> javaQuestion(){
        return CompletableFuture.supplyAsync(() -> client.mostRecentQuestionsAbout("java"));
    }

    private CompletableFuture<Question> findMostInterestingQuestion(Document document){
        return CompletableFuture.completedFuture(new Question());
    }

    private CompletableFuture<String> googleAnswer(Question q){
        return CompletableFuture.completedFuture("42");
    }

    private CompletableFuture<Integer> postAnswer(String answer){
        return CompletableFuture.completedFuture(200);
    }
}
