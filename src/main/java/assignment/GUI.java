package assignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GUI extends JFrame {

    public static void main(String[] args) {

        // Create the frame
        JFrame frame = new JFrame("Boggle Setup");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        // Create a CardLayout to manage both screens
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        // === First Screen (Input Panel) ===
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));

        JLabel sizeLabel = new JLabel("Enter board size:");
        JTextField sizeField = new JTextField();

        JLabel playersLabel = new JLabel("Enter number of players:");
        JTextField playersField = new JTextField();

        JButton enterButton = new JButton("Enter");

        inputPanel.add(sizeLabel);
        inputPanel.add(sizeField);
        inputPanel.add(playersLabel);
        inputPanel.add(playersField);
        inputPanel.add(new JLabel()); // Empty space
        inputPanel.add(enterButton);

        // === Second Screen (Game Board Panel) ===
        JPanel gamePanel = new JPanel();

        // Create a GameDictionary and GameManager for game logic
        GameDictionary gameDictionary = new GameDictionary();
        GameManager gameManager = new GameManager();

        // Add both panels to the main panel (CardLayout)
        mainPanel.add(inputPanel, "Input Screen");
        mainPanel.add(gamePanel, "Game Screen");

        // ActionListener for "Enter" button
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get input values
                String sizeText = sizeField.getText();
                String playersText = playersField.getText();

                // Validate input (ensure they are valid integers)
                try {
                    int boardSize = Integer.parseInt(sizeText);
                    int numberOfPlayers = Integer.parseInt(playersText);

                    // Start a new game based on the input
                    try {
                        gameManager.newGame(boardSize, numberOfPlayers, "cubes.txt", gameDictionary);
                    } catch (IOException ioException) {
                        System.err.println(ioException.getMessage());
                    }

                    // Create the game board dynamically
                    gamePanel.removeAll();
                    gamePanel.setLayout(new GridLayout(boardSize, boardSize));

                    for (int i = 0; i < gameManager.getBoard().length; i++) {
                        for (int j = 0; j < gameManager.getBoard().length; j++) {
                            JButton button = new JButton(Character.toString(gameManager.getBoard()[i][j]));

                            button.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println(button.getText() + " clicked!");
                                }
                            });

                            gamePanel.add(button);
                        }
                    }

                    // Refresh the game panel and switch to the game screen
                    gamePanel.revalidate();
                    gamePanel.repaint();
                    cardLayout.show(mainPanel, "Game Screen");

                } catch (NumberFormatException ex) {
                    // If input is not a valid integer, show an error message
                    JOptionPane.showMessageDialog(frame, "Please enter valid integers for board size and number of players.");
                }
            }
        });

        // Add the main panel to the frame and set the initial screen
        frame.add(mainPanel);
        cardLayout.show(mainPanel, "Input Screen");

        // Make the window visible
        frame.setVisible(true);
    }
}

