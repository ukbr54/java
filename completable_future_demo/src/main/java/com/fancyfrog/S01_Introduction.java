package com.fancyfrog;

import com.fancyfrog.utils.AbstractFuturesTest;
import org.junit.Test;

public class S01_Introduction extends AbstractFuturesTest {

    @Test
    public void blockingCall(){
        final String title = client.mostRecentQuestionAbout("java");
        System.out.println(String.format("Most recent Java question: %s", title));
    }
}
