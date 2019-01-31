package com.fancyfrog.stackoverflow;

import com.google.common.base.Throwables;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;


public class FallbackStubClient implements StackOverflowClient{

    private final StackOverflowClient target;

    public FallbackStubClient(StackOverflowClient target) {
        this.target = target;
    }

    @Override
    public String mostRecentQuestionAbout(String tag) {
       try{
          return target.mostRecentQuestionAbout(tag);
       }catch (Exception e){
          System.out.println(String.format("Problem retrieving tag %s",tag,e));
          switch (tag){
              case "java":
                  return "How to generate xml report with maven depencency?";
              case "scala":
                  return "Update a timestamp SettingKey in an sbt 0.12 task";
              case "groovy":
                  return "Reusing Grails variables inside Config.groovy";
              case "clojure":
                  return "Merge two comma delimited strings in Clojure";
              default:
                  throw e;
          }
       }
    }

    @Override
    public Document mostRecentQuestionsAbout(String tag) {
        try{
            return target.mostRecentQuestionsAbout(tag);
        }catch (Exception e){
            System.out.println(String.format("Problem retrieving recent question {}", tag, e));
            return loadStubHtmlFromDisk(tag);
        }
    }

    private Document loadStubHtmlFromDisk(String tag){
        try{
           final URL resource = getClass().getResource("/" + tag + "-questions.html");
            String html = IOUtils.toString(resource, "UTF-8");
            return Jsoup.parse(html);
        }catch (IOException e){
            throw Throwables.propagate(e);
        }
    }
}
