package assignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUI extends JFrame{
    public static void main(String[] args) {
        // Create the main window (JFrame)
        JFrame frame = new JFrame("4x4 Button Grid");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        // Create a panel with a 4x4 GridLayout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4)); // 4 rows, 4 columns

        // Add 16 buttons to the panel
        for (int i = 1; i <= 16; i++) {
            JButton button = new JButton("Button " + i);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Print the text of the clicked button
                    System.out.println(button.getText() + " clicked!");
                }
            });

            panel.add(button);
        }

        // Add the panel to the frame
        frame.add(panel);

        // Make the window visible
        frame.setVisible(true);
    }
}
