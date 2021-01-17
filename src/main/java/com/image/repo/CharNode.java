package com.image.repo;
import java.util.HashMap;

public class CharNode {
    public HashMap<Character, CharNode> children = new HashMap<Character, CharNode>();
    public boolean terminal;
}
