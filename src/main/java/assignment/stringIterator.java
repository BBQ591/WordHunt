package assignment;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.Deque;
import java.util.ArrayDeque;
public class stringIterator<String> implements java.util.Iterator<String> {
    public StringBuilder currWord;
    public trie bigTrie;
    public Stack<Character> newWord;
    public int index;
    public boolean dfs(trieNode node) {
        if (node.endWord == true && index >= currWord.length()) {
            return true;
        }
        index += 1;
        int startAscii = 97;
        if (index < this.currWord.length()) {
            startAscii = (int) this.currWord.charAt(index);
        }
        for (int i = startAscii; i <= 122; i++) {
            if (!node.neighbors.containsKey((char) i)) {
                continue;
            }
            this.newWord.push((char) i);
            if (dfs(node.neighbors.get((char) i))) {
                return true;
            }
            this.newWord.pop();
        }
        return false;


    }

    stringIterator(trie words) {
        this.bigTrie = words;
        this.currWord = new StringBuilder();
//        this.currWord.append("a");
//        this.currWord.append("a");
    }


    public boolean hasNext() {
        this.newWord = new Stack<>();
        this.index = -1;
        return dfs(this.bigTrie.root);
    }


    public String next() {
        this.newWord = new Stack<>();
        this.index = -1;
        if (!dfs(this.bigTrie.root)) {
            System.err.println("No more words");
        }
        this.currWord = new StringBuilder();
        for (int i = 0; i < this.newWord.size(); i++) {
            currWord.append(this.newWord.get(i));
        }
        return (String) currWord.toString();
    }

}
