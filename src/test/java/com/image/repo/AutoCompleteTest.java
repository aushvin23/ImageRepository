package com.image.repo;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

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

        ArrayList<String> autoComplete = wordBank.autoCompleteSearch("hello");

        assertTrue(autoComplete.contains("hello world"));
        assertTrue(autoComplete.contains("hello saturn"));
        assertTrue(autoComplete.contains("hello moon"));
        assertTrue(autoComplete.contains("hello neptune"));
        assertTrue(autoComplete.contains("hello venus"));
    }

    @Test
    public void testPrefixDoesNotAutoComplete() {
        AutoComplete wordBank = new AutoComplete();
        wordBank.insertion("hello world");
        wordBank.insertion("hello saturn");
        wordBank.insertion("hello moon");

        ArrayList<String> autoComplete = wordBank.autoCompleteSearch("mooon");
        assertTrue(autoComplete.size() == 0);
    }






}



