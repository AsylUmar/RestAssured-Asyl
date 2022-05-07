package com.cydeo.day5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersIntro {
    //Hamcrest -> is one of the assertion way to implement in any project
    @DisplayName("Assertion with Numbers")
    @Test
    public void test1() {

        //matchers have 2 overloaded versions
        //first that accepts actual value
        //second that accepts another matchers

        assertThat(5 + 5, is(10));
        assertThat(5 + 5, equalTo(10));
        assertThat(5 + 5, is(equalTo(10)));

        assertThat(5 + 5, not(5));
        assertThat(5 + 5, is(not(5)));
        assertThat(5 + 5, is(not(equalTo(5))));

        //greaterThan()
        //greaterThanOrEqualTo()
        //lessThan()
        //lessThanOrEqualTo()

        assertThat(5 + 5, is(greaterThan(9)));
        assertThat(5 + 5, is(greaterThanOrEqualTo(10)));
        assertThat(5 + 5, is(lessThan(12)));
        assertThat(5 + 5, is(lessThanOrEqualTo(9)));
    }
    @Test
    public void test2(){
        String text = "B25 is learning Hamcrest";

        //checking the equality
        assertThat(text, is("B25 is learning Hamcrest"));
        assertThat(text,equalTo("B25 is learning Hamcrest"));
        assertThat(text,is(equalTo("B25 is learning Hamcrest")));

        //check if the text starts with "B25"
        assertThat(text, startsWith("B25"));
        //case insensitive
        assertThat(text, startsWithIgnoringCase("b25"));
        //ends with
        assertThat(text, endsWith("rest"));

        //check if text contains String learning
        assertThat(text, containsString("learning"));
        //case insensitive
        assertThat(text, containsStringIgnoringCase("LEARNING"));

        String str = " ";

        //check if above srt is blank
        assertThat(str, blankString());
        //check is trimmed srt is empty string
        assertThat(str.trim(),emptyString());
    }

    @DisplayName("Hamcrest for Collection")
    @Test
    public void testCollection(){

        List<Integer> listOfNumbers = Arrays.asList(1,4,5,6,32,54,66,43,12,312,35);

        //check size of the list
        assertThat(listOfNumbers, hasSize(11));

        //check if the list has Item 54
        assertThat(listOfNumbers,hasItem(54));

        //check if the list has items 6, 54, 43
        assertThat(listOfNumbers,hasItems(6,54,43));

        //check if all numbers that are greater than 0
        assertThat(listOfNumbers, everyItem(greaterThan(0)));

    }

}
