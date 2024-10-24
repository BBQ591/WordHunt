//package assignment;
//import java.awt.Point;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.List;
//import java.io.IOException;
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.util.Random;
//
//
//public class GameManager implements BoggleGame{
//
//    public SearchTactic searchTactic = SEARCH_DEFAULT;
//    public char[][] board;
//    public ArrayList<ArrayList<Character>> cubes;
//    public BoggleDictionary dict;
//    public int numPlayers;
//
//    public void newGame(int size, int numPlayers, String cubeFile, BoggleDictionary dict) throws IOException {
//        this.board = new char[size][size];
//        this.dict = dict;
//        this.numPlayers = numPlayers;
//
//        BufferedReader cubeReader = new BufferedReader(new FileReader(cubeFile));
//
//        String currLine = cubeReader.readLine();
//
//        while (!(currLine == null) && !(currLine.isEmpty())) {
//            ArrayList<Character> temp = new ArrayList<>();
//            for(char ch : currLine.toCharArray()) {
//                temp.add(ch);
//            }
//            cubes.add(temp);
//            temp.clear();
//            currLine = cubeReader.readLine();
//        }
//
//        cubeReader.close();
//
//        Collections.shuffle(cubes);
//        Random rand = new Random();
//        int count = 0;
//
//        for(int i = 0; i < board.length; i++) {
//            for(int j = 0; j < board[i].length; j++) {
//                board[i][j] = cubes.get(count).get(rand.nextInt(cubes.size()));
//                count++;
//            }
//        }
//
//
//
//    }
//
//    public char[][] getBoard() {
//        return board;
//    }
//
//    public int addWord(String word, int player) {
//
//    }
//
//    public List<Point> getLastAddedWord(){
//
//    }
//
//    public void setGame(char[][] board){
//
//    }
//
//    public Collection<String> getAllWords(){
//
//    }
//
//    public void setSearchTactic(SearchTactic tactic){
//        this.searchTactic = tactic;
//    }
//
//    public int[] getScores(){
//
//    }
//
//}
