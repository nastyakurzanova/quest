package com.example.quest3;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }




}
class Note{
    public String to;
    public String from;
    public String heading;
    public String body;
    public Note(){
    }
    public Note(String toIn, String fromIn, String headingIn, String body){
        to = toIn;
        from = fromIn;
        heading = headingIn;
        body = bodyIn;
    }
}
