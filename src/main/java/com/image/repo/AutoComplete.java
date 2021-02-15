package com.image.repo;
import java.util.List;
import java.util.ArrayList;

/*
  * @desc this class will hold methods for autocomplete functionality
  * methods include insertion(word), deleteWord(word), searchWord(word), autoCompleteSearch(prefix)
*/
public class AutoComplete {
    private CharNode root;
    /*
      * @desc inserts word into data structure
      * @param string word - the word to be inserted
      * @return void */
    public void insertion(String word) {
        if (word == null || word.length() == 0) return;
        CharNode currNode = this.root;
        for (char c: word.toCharArray()) {
            if (!currNode.children.containsKey(c)) {
                currNode.children.put(c, new CharNode());
            }
            currNode = currNode.children.get(c);
        }
        currNode.terminal = true;
    }

    /*
      * @desc deletes word from data structure
      * @param string word - the word to be deleted
      * @return void */
    public void deleteWord(String word) {
        if (word == null || word.length() == 0 || !this.searchWord(word)) return;
        CharNode currNode = this.root;
        for (char c: word.toCharArray()) {
            currNode = currNode.children.get(c);
        }
        currNode.terminal = false;
    }

    /*
      * @desc checks if word exists in data structure
      * @param string word - the word of interest to check if exists
      * @return boolean - true or false */
    public boolean searchWord(String word) {
        if (word == null || word.length() == 0) return false;
        CharNode node = this.root;
        for (char c: word.toCharArray()) {
            if (node.children.containsKey(c)) {
                node = node.children.get(c);
            } else {
                node = null;
                break;
            }
        }
        return node != null && node.terminal;
    }

    /*
      * @desc get a list of names with the same prefix
      * @param string prefix - prefix that is used to search  
      * @return List of Type String - List of Autocomplete searches from prefix */
    public List<String> autoCompleteSearch(String prefix) {
        CharNode currNode = this.root;
        for (char c: prefix.toCharArray()) {
            if (!currNode.children.containsKey(c)) {
                return new ArrayList<>();
            }
            currNode = currNode.children.get(c);
        }
        return autoCompleteTraversal(currNode, prefix);
    }

    /*
      * @desc recursive DFS traversal helper function to autoCompleteSearch
      * @param CharNode currNode - Node of last prefix 
      * @param String prefix - prefix that is used to search in data structure  
      * @return List of Type String - List of Autocomplete searches from prefix */
    private List<String> autoCompleteTraversal(CharNode currNode, String prefix) {
        List<String> list = new ArrayList<String>();
        //base case
        if (currNode.terminal == true) {
            list.add(prefix);
        }
        //recurrence relation
        for (char c: currNode.children.keySet()) {
            //dfs traversal through data structure
            List<String> sublist = autoCompleteTraversal(currNode.children.get(c), prefix+Character.toString(c));
            list.addAll(sublist);
        }
        return list;
    }

    public AutoComplete() {
        this.root = new CharNode();        
    }
}
