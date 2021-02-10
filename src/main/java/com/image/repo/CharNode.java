package com.image.repo;
import java.util.HashMap;
import java.util.Map;

public class CharNode {
    public Map<Character, CharNode> children;
    public boolean terminal;

    public CharNode() {
        this.children = new HashMap<>();
    }
}
