package assignment;


import java.util.HashMap;

public class trie {
    public trieNode root;
    trie() {
        root = new trieNode('\0');
    }
    public void add(String word) {
        trieNode currNode = root;
        for (int i = 0; i < word.length(); i++) {
            if (!currNode.neighbors.containsKey(word.charAt(i))) {
                currNode.neighbors.put(word.charAt(i), new trieNode(word.charAt(i)));
            }
            currNode = currNode.neighbors.get(word.charAt(i));
        }
        currNode.endWord = true;
    }
    public boolean containsFullWord(String word) {
        trieNode currNode = root;
        for (int i = 0; i < word.length(); i++) {
            if (!currNode.neighbors.containsKey(word.charAt(i))) {
                return false;
            }
            currNode = currNode.neighbors.get(word.charAt(i));
        }
        return currNode.endWord;
    }
    public boolean isPrefix(String word) {
        trieNode currNode = root;
        for (int i = 0; i < word.length(); i++) {
            if (!currNode.neighbors.containsKey(word.charAt(i))) {
                return false;
            }
            currNode = currNode.neighbors.get(word.charAt(i));
        }
        return true;
    }

//    public static void main(String[] args) {
//        trie testTrie = new trie();
//
//        testTrie.add("aa");
//        testTrie.add("aah");
//        testTrie.add("aahed");
//        testTrie.add("aahing");
//
//        System.out.println(testTrie.isPrefix("aaah"));
//    }
}