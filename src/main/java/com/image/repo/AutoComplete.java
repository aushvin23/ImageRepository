package com.image.repo;

import java.util.ArrayList;

public class AutoComplete {
    private CharNode root;

    //inserts word into data structure 
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

    public void deleteWord(String word) {
        if (word == null || word.length() == 0 || !this.searchWord(word)) return;
        CharNode currNode = this.root;
        for (char c: word.toCharArray()) {
            currNode = currNode.children.get(c);
        }
        currNode.terminal = false;
    }

    public ArrayList<String> autoCompleteSearch(String prefix) {
        CharNode currNode = this.root;
        ArrayList<String> searchList = new ArrayList<String>();
        for (char c: prefix.toCharArray()) {
            if (!currNode.children.containsKey(c)) {
                return searchList;
            }
            currNode = currNode.children.get(c);
        }
        //currNode is currently the next node after prefix 
        return autoCompleteTraversal(currNode, prefix);
    }

    private ArrayList<String> autoCompleteTraversal(CharNode currNode, String prefix) {
        ArrayList<String> list = new ArrayList<String>();
        if (currNode.terminal == true) {
            list.add(prefix);
        }
        for (char c: currNode.children.keySet()) {
            ArrayList<String> sublist = autoCompleteTraversal(currNode.children.get(c), prefix+Character.toString(c));
            list.addAll(sublist);
        }
        return list;
    }

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
        return node != null && node.terminal == true;
    }

    public AutoComplete() {
        this.root = new CharNode();        
    }
}
