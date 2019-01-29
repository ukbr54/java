package com.fancyfrog.stackoverflow;

import org.jsoup.nodes.Document;

import java.io.IOException;

public interface StackOverflowClient {

    String mostRecentQuestionAbout(String tag) throws IOException;
    Document mostRecentQuestionsAbout(String tag) throws IOException;
}
