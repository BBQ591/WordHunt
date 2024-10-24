package assignment;

import java.util.*;

public class stringIterator<String> implements java.util.Iterator<String> {
    public Stack<trieNode> word;
    stringIterator(trie words) {
        word = new Stack<>();
        //setting up first word
        word.add(words.root);
//        word.add(bigTrie.root.neighbors.get('a'));
//        word.add(bigTrie.root.neighbors.get('a'));
    }
    public boolean extensions(trieNode searching, boolean isFirst) {
//        System.out.println("hello");
//        System.out.println(searching);
        if (word.get(word.size()-1).endWord && !isFirst) {
            return true;
        }
        for (int i = (int) 'a'; i <= (int) 'z'; i++) {
            if (searching.neighbors.containsKey((char) i)) {
                word.add(searching.neighbors.get((char) i));
                return extensions(word.get(word.size() - 1), false);
            }
        }
        return false;
    }

    public boolean hasNext() {
        if (word.size() == 1) {
            return true;
        }
        int index = word.size()-2;
        while (index >= 0) {
            for (int i = ((int) word.get(index+1).currLetter)+1; i <= (int) 'z'; i++) {
                if (word.get(index).neighbors.containsKey((char) i)) {
                    return true;
                }
            }
            index -= 1;
        }
        return false;
    }

    public String builderString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < word.size(); i++) {
            builder.append(word.get(i).currLetter);
        }
        return (String) builder.toString();
    }
    public String next() {

//        if (true) {
//            System.err.println("No more words");
//        }
//        System.out.println((String) word.toString());
        //there are two cases here: if you extend and if you change the last letter
        //extension starts here
        if (extensions(word.get(word.size()-1), true)) {
            return builderString();
        }
//        System.out.println("here");
        //change last letter starts here
        char lastLetter;
        while (!word.isEmpty()) {
            lastLetter = word.pop().currLetter;
            for (int i = (int)lastLetter+1; i <= (char) 'z'; i++) {
                if (word.get(word.size()-1).neighbors.containsKey((char) i)) {
                    word.add(word.get(word.size()-1).neighbors.get((char) i));
                    extensions(word.get(word.size()-1), false);
                    return builderString();
                }
            }
        }
//        System.err.println("No more words");
        throw new RuntimeException("No more words");
    }

}
