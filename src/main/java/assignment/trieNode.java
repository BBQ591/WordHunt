package assignment;


import java.util.HashMap;

public class trieNode {
    public HashMap<Character, trieNode> neighbors;
    public boolean endWord = false;
    public char currLetter;
    trieNode(char currLetter) {
        this.neighbors = new HashMap<>();
        this.currLetter = currLetter;
    }
}