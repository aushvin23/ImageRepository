package com.image.repo;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AutoCompleteTest {

    @Test
    public void testWordDoesNotExist() {
        AutoComplete wordBank = new AutoComplete();
        String word = "test";
        assertTrue(!wordBank.searchWord(word));
    }

    @Test
    public void testWordExists() {
        AutoComplete wordBank = new AutoComplete();
        String word = "test";
        wordBank.insertion(word);

        assertTrue(wordBank.searchWord(word));
    }

    @Test
    public void testEmptyString() {
        AutoComplete wordBank = new AutoComplete();
        String word = "";
        wordBank.insertion(word);

        assertTrue(!wordBank.searchWord(word));
    }

    @Test 
    public void testNullString() {
        AutoComplete wordBank = new AutoComplete();
        String word = null;
        wordBank.insertion(null);
        
        assertTrue(!wordBank.searchWord(word));
    }

    @Test 
    public void testDeleteString() {
        AutoComplete wordBank = new AutoComplete();
        String word = "test";
        wordBank.insertion(word);
        wordBank.deleteWord(word);
        assertTrue(!wordBank.searchWord(word));
    }

    @Test
    public void testAutoCompleteSearch() {
        AutoComplete wordBank = new AutoComplete();
        wordBank.insertion("hello world");
        wordBank.insertion("hello saturn");
        wordBank.insertion("hello moon");
        wordBank.insertion("hello neptune");
        wordBank.insertion("hello venus");

        List<String> actualOutput = wordBank.autoCompleteSearch("hello");
        List<String> expectedOutput = Arrays.asList("hello saturn", "hello venus", "hello world", "hello moon", "hello neptune");

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testPrefixDoesNotAutoComplete() {
        AutoComplete wordBank = new AutoComplete();
        wordBank.insertion("hello world");
        wordBank.insertion("hello saturn");
        wordBank.insertion("hello moon");

        List<String> actualOutput = wordBank.autoCompleteSearch("mooon");
        List<String> expectedOutput = new ArrayList<>();

        assertEquals(expectedOutput, actualOutput);
    }
}



