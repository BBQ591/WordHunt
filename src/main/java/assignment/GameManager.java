package assignment;
import java.awt.Point;
import java.util.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;


public class GameManager implements BoggleGame{


    public SearchTactic searchTactic = SEARCH_DEFAULT;
    public char[][] board;
    public ArrayList<ArrayList<Character>> cubes = new ArrayList<>();
    public GameDictionary dict;
    public int[] scores;
    public ArrayList<HashSet<String>> words = new ArrayList<>();
    public String lastWord;

    public static void main(String[] args){
        GameManager gm = new GameManager();
        GameDictionary gd = new GameDictionary();

        try {
            gm.newGame(4, 2, "cubes.txt", gd);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(Arrays.deepToString(gm.getBoard()));

    }

    public void newGame(int size, int numPlayers, String cubeFile, BoggleDictionary dict) throws IOException {

        this.board = new char[size][size];
        this.dict = (GameDictionary) dict;
        this.scores = new int[numPlayers];

        BufferedReader cubeReader = new BufferedReader(new FileReader(cubeFile));

        String currLine = cubeReader.readLine();

        while (!(currLine == null) && !(currLine.isEmpty())) {
            ArrayList<Character> temp = new ArrayList<>();
            for(char ch : currLine.toCharArray()) {
                temp.add(ch);
            }
            this.cubes.add(temp);
            currLine = cubeReader.readLine();
        }

        cubeReader.close();

        if(this.cubes.size() < size*size){
            System.err.println("Not enough cubes to create a game with given dimensions");
        }

        Collections.shuffle(this.cubes);
        Random rand = new Random();
        int count = 0;

        for(int i = 0; i < this.board.length; i++) {
            for(int j = 0; j < this.board[i].length; j++) {
                this.board[i][j] = this.cubes.get(count).get(rand.nextInt(this.cubes.get(count).size()));
                count++;
            }
        }


    }

    public char[][] getBoard() {
        return this.board;
    }



    public int addWord(String word, int player) {
        if(this.words.get(player).contains(word) || word.length() < 4 || dict.contains(word)) {
            return 0;
        }
        else {
            this.words.get(player).add(word);
            int score = word.length() - 3;
            this.scores[player] += score;
            this.lastWord = word;
            return score;
        }
    }

    public List<Point> getLastAddedWord(){return new ArrayList<>();
    }

    public void setGame(char[][] board){
        if(board.length != board[0].length || this.board.length != board.length){
            System.err.println("Invalid board dimensions");
        }

        for(int i = 0; i < this.board.length; i++) {
            for(int j = 0; j < this.board[i].length; j++) {
                this.board[i][j] = board[i][j];
            }
        }

        Arrays.fill(this.scores, 0);
    }

    public String builderString(Stack<Character> word) {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < word.size(); i++) {
            builder.append(word.get(i));
        }
        return (String) builder.toString();
    }

    public void BoardDriven(int x, int y, trieNode node, HashSet<String> allWords, Stack<Character> currWord, HashSet<Point> visited) {
        if (x < 0 || x >= this.board[0].length || y < 0 || y >= this.board.length || visited.contains(new Point(x, y))) {
            return;
        }
        if (node.endWord) {
            allWords.add(builderString(currWord));
        }
        visited.add(new Point(x, y));
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (node.neighbors.containsKey(this.board[y+j][x+i])) {
                    currWord.push(this.board[y+j][x+i]);
                    BoardDriven(x+i, y+j, node.neighbors.get(this.board[y+j][x+i]), allWords, currWord, visited);
                    currWord.pop();
                }
            }
        }
        visited.remove(new Point(x, y));
    }
    public boolean finding(int x, int y, String word, int index, HashSet<Point> visited) {
        if (index >= word.length()) {
            return true;
        }
        if (x < 0 || x >= this.board[0].length || y < 0 || y >= this.board.length || visited.contains(new Point(x, y)) || this.board[y][x] != word.charAt(index)) {
            return false;
        }
        visited.add(new Point(x, y));
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (finding(x+i, y+j, word, index+1, visited)) {
                    return true;
                }
            }
        }
        visited.remove(new Point(x, y));
        return false;
    }
    public boolean dictionaryDriven(String word) {
        for (int i = 0; i < word.length(); i++) {
            for (int j = 0; j < word.length(); j++) {
                if (finding(j, i, word, 0, new HashSet<>())) {
                    return true;
                }
            }
        }
        return false;
    }
    public Collection<String> getAllWords(){
        Collection<String> finalWords = new ArrayList<>();
        if (this.searchTactic == SEARCH_DEFAULT) {
            HashSet<String> currWords = new HashSet<>();
            for (int i = 0; i < this.board.length; i++) {
                for (int j = 0; j < this.board[i].length; j++) {
                    BoardDriven(j, i, dict.bigTrie.root.neighbors.get(this.board[i][j]), currWords, new Stack<>(), new HashSet<>());

                }
            }
            for (String word: currWords) {
                finalWords.add(word);
            }
        }
        else {
            String curr;
            while (dict.iterator().hasNext()) {
                curr = dict.iterator().next();
                if (dictionaryDriven(curr)) {
                    finalWords.add(curr);
                }
            }
        }
        return finalWords;
    }

    public void setSearchTactic(SearchTactic tactic){
        this.searchTactic = tactic;
    }

    public int[] getScores(){
        return this.scores;
    }

}
