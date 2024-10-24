package assignment;

import java.io.IOException;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
public class GameDictionary implements BoggleDictionary{
    public trie bigTrie = new trie();
    public stringIterator<String> iterator;
    GameDictionary() {
        iterator = new stringIterator<>(bigTrie);
    }

    public static void main(String[] args) {
        GameDictionary gd = new GameDictionary();
        String currentDirectory = System.getProperty("user.dir");

        // Print the current directory
        System.out.println("Current Directory: " + currentDirectory);
        try {
//            System.out.println("hello");
            gd.loadDictionary("./words.txt");
            int index = 0;
            while (gd.iterator().hasNext()) {
                index += 1;
                System.out.println(gd.iterator().next());
            }
            System.out.println(index);


        }
        catch (IOException e) {
            System.out.println("WTF");
        }

    }


    public void loadDictionary(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        String currLine = reader.readLine();

        while (!(currLine == null) && !(currLine.isEmpty())) {
//            System.out.println(currLine);
            bigTrie.add(currLine);
            currLine = reader.readLine();
        }

        reader.close();
    }

    public boolean isPrefix(String prefix){
        return bigTrie.isPrefix(prefix);
    }

    public boolean contains(String name){
        return bigTrie.containsFullWord(name);
    }

    public stringIterator<String> iterator() {
        return iterator;
    }


}